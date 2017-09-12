package com.example.dellc.qq.view;

/**
 * Created by dellc on 2017/9/12.
 */

public interface LoginView {

    void onLoginSuccess();

    void onLoginFailed();

    void onPasswordError();

    void onUserNameError();

    void onStartLogin();
}
