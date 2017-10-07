package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.ConversationPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ConversationView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationPresenterImpl implements ConversationPersenter {

    public ConversationView mConversationView;

    private List<EMConversation> mEMConversation;

    public ConversationPresenterImpl(ConversationView conversationView){
        mEMConversation=new ArrayList<EMConversation>();
        mConversationView=conversationView;
    }

    @Override
    public void loadConversation() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                Map<String, EMConversation> conversations = EMClient.getInstance()
                        .chatManager().getAllConversations();
                mEMConversation.addAll(conversations.values());

                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mConversationView.onLoadConversationSuccess();

                    }
                });
            }
        });
    }
}
