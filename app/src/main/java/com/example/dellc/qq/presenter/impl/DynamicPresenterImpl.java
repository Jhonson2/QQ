package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.adapter.EMCallBackAdapter;
import com.example.dellc.qq.presenter.DynamicPresenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.DynamicView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by dellc on 2017/9/16.
 */

public class DynamicPresenterImpl implements DynamicPresenter {
    public static final String TAG = "DynamicPresenterImpl";

    private DynamicView mDynamicView;

    public DynamicPresenterImpl(DynamicView dynamicView) {
        mDynamicView = dynamicView;
    }

    @Override
    public void logout() {
        mDynamicView.onStartLogout();
        EMClient.getInstance().logout(true, mEMCallBackAdapter);
    }

    private EMCallBackAdapter mEMCallBackAdapter = new EMCallBackAdapter() {

        @Override
        public void onSuccess() {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDynamicView.onLogoutSuccess();
                }
            });
        }


        @Override
        public void onError(int i, String s) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDynamicView.onLogoutFailed();
                }
            });
        }
    };
}

