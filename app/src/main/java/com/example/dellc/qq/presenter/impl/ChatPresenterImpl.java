package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.adapter.EMCallBackAdapter;
import com.example.dellc.qq.presenter.ChatPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ChatView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

/**
 * Created by dellc on 2017/10/5.
 */

public class ChatPresenterImpl implements ChatPersenter {
    private ChatView mChatView;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
    }

    @Override
    public void sendMessage(final String userName, final String msg) {
        mChatView.onStartSendMessage();

        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //创建一个文本消息，content为消息内容，toChat为对方用户或者群聊id
                EMMessage emMessage=EMMessage.createTxtSendMessage(msg,userName);
                emMessage.setMessageStatusCallback(mEMCallBackAdapter);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(emMessage);
            }
        });
    }

    private EMCallBackAdapter mEMCallBackAdapter=new EMCallBackAdapter(){
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
