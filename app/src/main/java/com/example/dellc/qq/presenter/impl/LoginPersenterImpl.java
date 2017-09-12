package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.LoginPersenter;
import com.example.dellc.qq.view.LoginView;

/**
 * Created by dellc on 2017/9/12.
 */

public class LoginPersenterImpl implements LoginPersenter {
    public static final String TAG="LoginPersenterImpl";

    private LoginView mLoginView;

    public LoginPersenterImpl(LoginView loginView){
        mLoginView=loginView;
    }
    @Override
    public void login(String userName, String password) {

    }
}
