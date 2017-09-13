package com.example.dellc.qq.presenter.impl;

import android.widget.Toast;

import com.example.dellc.qq.app.Constant;
import com.example.dellc.qq.model.User;
import com.example.dellc.qq.presenter.RegisterPersenter;
import com.example.dellc.qq.ui.RegisterActivity;
import com.example.dellc.qq.utils.StringUtils;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.RegisterView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.example.dellc.qq.app.Constant.ErroeCode.USER_ALREAND_EXIST;

/**
 * Created by dellc on 2017/9/12.
 */

public class RegisterPersenterImpl implements RegisterPersenter {
    public static final String TAG = "RegisterPersenterImpl";

    public RegisterView mRegisterView;

    public RegisterPersenterImpl(RegisterView registerView) {
        mRegisterView = registerView;
    }

    @Override
    public void register(String userName, String password, String confirmPassword) {
        //检查用户名
        if (StringUtils.isValidUserName(userName)) {
            //检查密码
            if (StringUtils.isValidPassword(password)) {
                //检查密码和确认密码
                if (password.equals(confirmPassword)) {
                    //通知view开始注册
                    mRegisterView.onStarRegister();
                    registerBmob(userName, password);

                } else {
                    //通知确认密码错误
                    mRegisterView.onConfirmPasswordError();
                }

            } else {
                //通知用户名不合法
                mRegisterView.onPasswordError();
            }

        } else {
            //通知用户名不合法
            mRegisterView.onUserNameError();
        }
    }

    private void registerBmob(final String userName, final String password) {
        User user = new User(userName, password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    //注册成功
                    //注册环信
                    registerEaseMob(userName, password);
                } else {
                    e.printStackTrace();
                    //注册失败
                    //通知View层注册失败
                    //如果用户名已经存在，通知View层提示
                    if (e.getErrorCode() == Constant.ErroeCode.USER_ALREAND_EXIST) {
                        mRegisterView.onUserNameExist();
                    } else {
                        mRegisterView.onRegisterFailed();
                    }
                }
            }
        });

    }

    private void registerEaseMob(final String userName, final String password) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(userName, password);//同步方法
                    //注册成功，通知view层更新
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterView.onRegisterSuccess();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //注册失败，通知view层更新
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterView.onRegisterFailed();
                        }
                    });
                }
            }
        });

    }

}
