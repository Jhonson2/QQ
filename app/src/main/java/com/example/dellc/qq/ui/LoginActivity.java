package com.example.dellc.qq.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dellc.qq.BaseActivity;
import com.example.dellc.qq.MainActivity;
import com.example.dellc.qq.R;
import com.example.dellc.qq.presenter.LoginPersenter;
import com.example.dellc.qq.presenter.impl.LoginPersenterImpl;
import com.example.dellc.qq.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dellc on 2017/9/10.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    public static final String TAG = "LoginActivity";
    private final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 0;

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.new_user)
    TextView mNewUser;

    private LoginPersenter mLoginPersenter;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_login;

    }

    @Override
    protected void init() {
        super.init();
        mLoginPersenter = new LoginPersenterImpl(this);
        mPassword.setOnEditorActionListener(mOnEditorActionListener);
    }

    @OnClick({R.id.login, R.id.new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                if (checkIfHasWriteExternalStoragePermission()) {
                    login();
                } else {
                    applyPermission();
                }
                break;
            case R.id.new_user:
                goTo(RegisterActivity.class);
                break;
        }
    }

    /*
    * android6.0后，申请权限
    * */
    private void applyPermission() {
        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permission, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    login();
                }
                break;
        }
    }

    /*检查是否有写磁盘权限
    * */
    private boolean checkIfHasWriteExternalStoragePermission() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private EditText.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                login();
                return true;
            }

            return false;
        }
    };

    private void login() {
        //hideKeyboard();
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        mLoginPersenter.login(userName, password);
    }

    @Override
    public void onLoginSuccess() {
        hideProgress();
        goTo(MainActivity.class);
    }

    @Override
    public void onLoginFailed() {
        hideProgress();
        toast(getString(R.string.login_failed));
    }

    @Override
    public void onUserNameError() {
        mUserName.setError(getString(R.string.user_name_error));

    }

    @Override
    public void onPasswordError() {
        mPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void onStartLogin() {
        showProgress(getString(R.string.logining));

    }
}
