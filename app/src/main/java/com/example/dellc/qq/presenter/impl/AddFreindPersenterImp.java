package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.model.User;
import com.example.dellc.qq.presenter.AddFriendPersenter;
import com.example.dellc.qq.view.AddFreindView;
import com.hyphenate.chat.EMClient;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dellc on 2017/9/30.
 */

public class AddFreindPersenterImp implements AddFriendPersenter {

    public AddFreindView mAddFreindView;

    public AddFreindPersenterImp(AddFreindView addFreindView) {
        mAddFreindView = addFreindView;
    }

    @Override
    public void searchFreind(String keyword) {
        //使用Bmob查询
        BmobQuery<User> query = new BmobQuery<>();
        //添加查询条件（需要付费）
        //模糊查询：除了自己只外
        query.addWhereContains("username", keyword).
                addWhereNotEqualTo("username", EMClient.getInstance().getCurrentUser());

        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {

                if (e == null) {
                    if (list.size() == 0) {
                        mAddFreindView.onSearchEmpty();
                    } else {
                        //异常为空，搜索成功
                        mAddFreindView.onSearchSuccess();
                    }
                } else {
                    // //异常存在，搜索失败
                    mAddFreindView.onSearchFailed();
                }
            }
        });
    }
}
