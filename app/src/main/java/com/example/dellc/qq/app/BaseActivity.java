package com.example.dellc.qq.app;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

/**创建基类
 * Created by dellc on 2017/9/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResID());

        init();
    }

    //初始化
    private void init() {

    }

    public abstract int getLayoutResID();
}
