package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.RegisterPersenter;
import com.example.dellc.qq.view.RegisterView;

/**
 * Created by dellc on 2017/9/12.
 */

public class RegisterPersenterImpl  implements RegisterPersenter{
    public static final String TAG="RegisterPersenterImpl";

    public RegisterView mRegisterView;

    public RegisterPersenterImpl(RegisterView registerView){
        mRegisterView=registerView;
    }

    @Override
    public void register(String userName, String password, String confirmPassword) {

    }
}
