package com.example.dellc.qq.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;

import com.example.dellc.qq.MainActivity;
import com.example.dellc.qq.R;
import com.example.dellc.qq.app.BaseActivity;
import com.example.dellc.qq.presenter.SpalshPersenter;
import com.example.dellc.qq.presenter.impl.SpalshPresenterImpl;
import com.example.dellc.qq.view.SpalshView;

/**
 * Created by dellc on 2017/9/10.
 */

public class SplashActivity extends BaseActivity implements SpalshView{
    public static final String TAG="SplashActivity";
    private static  final int DELAY_TIME=2000;

    private SpalshPersenter mSpalshPersenter;
    private Handler mHandler=new Handler();

    @Override
    public int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(){
        super.init();
        mSpalshPersenter=new SpalshPresenterImpl(this);

        //检查登录状态
      mSpalshPersenter.checkLoginStatus();

    }

    @Override
    public void onLoginIn() {
        //登录后，跳转主界面
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onNotLogin() {
        //没有登录，延迟2秒后跳转到登录界面
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);


            }
        },DELAY_TIME);
    }
}
