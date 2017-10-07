package com.example.dellc.qq.view;

/**
 * Created by dellc on 2017/10/5.
 */

public interface ChatView {
    void onStartSendMessage();

    void onSendMessageSuccess();

    void onSendMessageFailed();

    void onLoadMessageSuccess();

    void onLoadMoreMessageSuccess(int size);

    void onNoMoreData();
}
