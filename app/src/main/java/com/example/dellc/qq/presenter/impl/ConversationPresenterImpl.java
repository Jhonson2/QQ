package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.ConversationPersenter;
import com.example.dellc.qq.view.ConversationView;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationPresenterImpl implements ConversationPersenter {

    public ConversationView mConversationView;

    public ConversationPresenterImpl(ConversationView conversationView){
        mConversationView=conversationView;
    }

    @Override
    public void loadConversation() {

    }
}
