package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.adapter.EMCallBackAdapter;
import com.example.dellc.qq.presenter.ChatPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ChatView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dellc on 2017/10/5.
 */

public class ChatPresenterImpl implements ChatPersenter {
    private ChatView mChatView;
    private List<EMMessage> mMessage;

    private static final int DEFAULT_PAGE_SIZE = 20;//默认加载20条数据

    private boolean canLoadMoreMessage = true;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
        mMessage = new ArrayList<EMMessage>();
    }

    @Override
    public void sendMessage(final String userName, final String msg) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //创建一个文本消息，content为消息内容，toChat为对方用户或者群聊id
                EMMessage emMessage = EMMessage.createTxtSendMessage(msg, userName);
                emMessage.setMessageStatusCallback(mEMCallBackAdapter);

                //点击发送后，立即添加到聊天的数据集合中
                mMessage.add(emMessage);

                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onStartSendMessage();
                    }
                });
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(emMessage);
            }
        });
    }

    @Override
    public List<EMMessage> getMessage() {

        return mMessage;
    }

    @Override
    public void loadMessage(final String userName) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {

                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userName);
                if (conversation != null) {
                    List<EMMessage> messages = conversation.getAllMessages();//获取此会话的所有消息
                    mMessage.addAll(messages); //添加到数据集合
                    //指定会话消息未读数清零
                   // conversation.markAllMessagesAsRead();
                }

                //通知View层加载聊天记录成功
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onLoadMessageSuccess();
                    }
                });

                //指定会话消息未读数清零
                conversation.markAllMessagesAsRead();
            }
        });


    }


    /**
     * 加载更多历史信息
     */
    @Override
    public void loadMoreMessage(final String userName) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (canLoadMoreMessage) {
                    EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userName);
                    //SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
                    //获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，
                    // APP中无需再次把获取到的messages添加到会话中
                    String startMsgId = mMessage.get(0).getMsgId();//获取第一条数据的id
                    final List<EMMessage> messages = conversation.loadMoreMsgFromDB(startMsgId, DEFAULT_PAGE_SIZE);

                    mMessage.addAll(0, messages);//将更多数据加入数据集合

                    //如果数据不足20条时
                    if (messages.size() < DEFAULT_PAGE_SIZE) {
                        canLoadMoreMessage = false;
                    }

                    //然后跳转到View层加载数据
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mChatView.onLoadMoreMessageSuccess(messages.size());
                        }
                    });
                } else {
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mChatView.onNoMoreData();
                        }
                    });
                }
            }
        });
    }


    private EMCallBackAdapter mEMCallBackAdapter = new EMCallBackAdapter() {
        @Override
        public void onSuccess() {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageSuccess();
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageFailed();
                }
            });
        }
    };
}


