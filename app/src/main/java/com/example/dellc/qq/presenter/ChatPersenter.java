package com.example.dellc.qq.presenter;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by dellc on 2017/10/5.
 */

public interface ChatPersenter {
    void sendMessage(String userName,String msg);

    List<EMMessage> getMessage();

    void loadMessage(String userName);
}
