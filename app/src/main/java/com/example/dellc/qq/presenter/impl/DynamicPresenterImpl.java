package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.DynamicPresenter;
import com.example.dellc.qq.view.DynamicView;

/**
 * Created by dellc on 2017/9/16.
 */

public class DynamicPresenterImpl implements DynamicPresenter {
    public static final String TAG="DynamicPresenterImpl";

    private DynamicView mDynamicView;

    public DynamicPresenterImpl(DynamicView dynamicView){
        mDynamicView=dynamicView;
    }
    @Override
    public void logout() {

    }
}
