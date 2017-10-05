package com.example.dellc.qq.view;

/**
 * Created by dellc on 2017/9/16.
 */

public interface ContactView {
    void onLoadContactsSuccess();

    void onLoadContactsFailed();

    void onDeleteFriendFailed();

    void onDeleteFriendSuccess();
}
