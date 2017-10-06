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
                List<EMMessage> messages = conversation.getAllMessages();//获取此会话的所有消息
                mMessage.addAll(messages); //添加到数据集合

                //通知View层加载聊天记录成功
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onLoadMessageSuccess();
                    }
                });
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
