package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.AddFriendPersenter;
import com.example.dellc.qq.view.AddFreindView;

/**
 * Created by dellc on 2017/9/30.
 */

public class AddFreindPersenterImp implements AddFriendPersenter {

    public AddFreindView mAddFreindView;

    public AddFreindPersenterImp(AddFreindView addFreindView){
        mAddFreindView=addFreindView;
    }
    @Override
    public void searchFreind(String keyword) {

    }
}
