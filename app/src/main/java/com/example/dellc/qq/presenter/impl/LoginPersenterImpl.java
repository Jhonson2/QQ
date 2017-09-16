package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.adapter.EMCallBackAdapter;
import com.example.dellc.qq.presenter.LoginPersenter;
import com.example.dellc.qq.utils.StringUtils;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.LoginView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;


/**
 * Created by dellc on 2017/9/12.
 */

public class LoginPersenterImpl implements LoginPersenter {
    public static final String TAG = "LoginPersenterImpl";

    private LoginView mLoginView;

    public LoginPersenterImpl(LoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void login(String userName, String password) {
        if (StringUtils.isValidUserName(userName)) {
            if (StringUtils.isValidPassword(password)) {
                mLoginView.onStartLogin();
                EMClient.getInstance().login(userName, password, mEMACallback);
            } else {
                mLoginView.onPasswordError();
            }
        } else {
            mLoginView.onUserNameError();
        }
    }

    private EMCallBack mEMACallback = new EMCallBackAdapter() {
        @Override
        public void onSuccess() {
            //通知view登录成功
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoginView.onLoginSuccess();
                }
            });

        }

        @Override
        public void onError(int i, String s) {
            //通知view登录失败
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoginView.onLoginFailed();
                }
            });

        }


    };
}
