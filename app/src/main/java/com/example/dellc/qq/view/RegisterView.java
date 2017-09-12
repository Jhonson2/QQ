package com.example.dellc.qq.view;

/**
 * Created by dellc on 2017/9/12.
 */

public interface RegisterView {
    void onUserNameError();

    void onPasswordError();

    void onConfirmPasswordError();

    void onStarRegister();

    void onRegisterFailed();

    void onRegisterSuccess();

}
