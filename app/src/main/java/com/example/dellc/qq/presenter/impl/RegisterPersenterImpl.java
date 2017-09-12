package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.RegisterPersenter;
import com.example.dellc.qq.utils.StringUtils;
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
       //检查用户名
        if(StringUtils.isValidUserName(userName)){
            //检查密码
            if(StringUtils.isValidPassword(password)){
                //检查密码和确认密码
                if(password.equals(confirmPassword)){
                    //通知view开始注册
                    mRegisterView.onStarRegister();

                }else{
                    //通知确认密码错误
                    mRegisterView.onConfirmPasswordError();
                }

            }else {
                //通知用户名不合法
                mRegisterView.onPasswordError();
            }

        }else {
            //通知用户名不合法
            mRegisterView.onUserNameError();
        }
    }
}
