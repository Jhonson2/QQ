package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.SpalshPersenter;
import com.example.dellc.qq.view.SpalshView;

/**
 * SpalshPresenterImpl层的实现类
 */

public class SpalshPresenterImpl implements SpalshPersenter {
   public static final String TAG="SpalshPresenterImpl";

    private SpalshView mSpalshView;

    //创建构造方法引用spalshView
    public SpalshPresenterImpl(SpalshView spalshView){
        mSpalshView=spalshView;
    }

    @Override
    public void checkLoginStatus() {
        if(isLoginedIn()){
            //通知view层——已经是登陆状态
            mSpalshView.onLoginIn();
        }else{
            //通知view层——没登陆状态
            mSpalshView.onNotLogin();
        }

    }

    //暂时写没有登录
    private boolean isLoginedIn() {
        return false;
    }
}
