package com.example.dellc.qq.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dellc.qq.BaseActivity;
import com.example.dellc.qq.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dellc on 2017/9/10.
 */
public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.new_user)
    TextView mNewUser;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_login;
    }



    @OnClick({R.id.login, R.id.new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                break;
            case R.id.new_user:
              goTo(RegisterActivity.class);
                break;
        }
    }
}
