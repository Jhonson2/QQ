package com.example.dellc.qq;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.dellc.qq.ui.RegisterActivity;

import butterknife.ButterKnife;

/**创建基类
 * Created by dellc on 2017/9/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        init();
    }

    //初始化
    protected void init() {

    }

    public abstract int getLayoutResID();

    protected void goTo(Class activity){
        Intent intent=new Intent(this,activity);
        startActivity(intent);
        finish();

    }
}
