package com.example.dellc.qq.presenter;

import com.example.dellc.qq.model.ContactItem;

import java.util.List;

/**
 * Created by dellc on 2017/9/16.
 */

public interface ContactPersenter {
    void loadContacts();

    List<ContactItem> getContacts();

    void refreshContacts();

    void deleteFreind(String userName);
}
