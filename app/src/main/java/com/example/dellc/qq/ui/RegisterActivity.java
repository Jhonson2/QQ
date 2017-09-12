package com.example.dellc.qq.ui;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dellc.qq.BaseActivity;
import com.example.dellc.qq.R;
import com.example.dellc.qq.presenter.RegisterPersenter;
import com.example.dellc.qq.presenter.impl.RegisterPersenterImpl;
import com.example.dellc.qq.view.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dellc on 2017/9/11.
 */
public class RegisterActivity extends BaseActivity implements RegisterView {
    public static final String TAG = "RegisterActivity";

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirm_password)
    EditText mConfirmPassword;
    @BindView(R.id.register)
    Button mRegister;

    private RegisterPersenter mRegisterPersenter;


    @Override
    public int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        super.init();
        mRegisterPersenter = new RegisterPersenterImpl(this);

        //监听软键盘go时候注册
        mConfirmPassword.setOnEditorActionListener(mOnEditorActionListener);

    }


    @OnClick(R.id.register)
    public void onClick() {
        //注册
        register();
    }

    private void register() {
        String userName=mUserName.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        String confirmPassword=mConfirmPassword.getText().toString().trim();

      /*  BmobUser bmobUser=new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(password);
        bmobUser.signUp(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }


            }
        });*/

        mRegisterPersenter.register(userName,password,confirmPassword);
    }

    /*
        * 监听软键盘go时候注册
        * */
    private TextView.OnEditorActionListener mOnEditorActionListener=new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId== EditorInfo.IME_ACTION_GO){
                register();
                return true;
            }
            return false;
        }
    };


    @Override
    public void onUserNameError() {
        mUserName.setError(getString(R.string.user_name_error));

    }

    @Override
    public void onPasswordError() {
        mPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void onConfirmPasswordError() {
        mConfirmPassword.setError(getString(R.string.confirm_password_error));
    }

    @Override
    public void onStarRegister() {

    }
}
