package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.ContactPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ContactView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

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
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //没有异常，加载成功
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    //加载成功，通知View层

                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onLoadContactsSuccess();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //加载失败，通知View层
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onLoadContactsFailed();
                        }
                    });
                }
            }
        });

    }
}
