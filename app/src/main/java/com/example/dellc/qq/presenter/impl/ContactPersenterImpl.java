package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.ContactPersenter;
import com.example.dellc.qq.view.ContactView;

/**
 * Created by dellc on 2017/9/16.
 */

public class ContactPersenterImpl  implements ContactPersenter{
    private ContactView mContactView;

    public ContactPersenterImpl(ContactView contactView){
        mContactView=contactView;
    }
    @Override
    public void loadContacts() {

    }
}
