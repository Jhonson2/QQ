package com.example.dellc.qq.presenter;

import com.hyphenate.chat.EMConversation;

import java.util.List;

/**
 * Created by dellc on 2017/10/7.
 */

public interface ConversationPersenter {
    void loadConversation();

    List<EMConversation> getConversations();
}
