package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.ChatPersenter;
import com.example.dellc.qq.view.ChatView;

/**
 * Created by dellc on 2017/10/5.
 */

public class ChatPresenterImpl implements ChatPersenter {
    private ChatView mChatView;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
    }

    @Override
    public void sendMessage(String userName, String msg) {

    }
}
