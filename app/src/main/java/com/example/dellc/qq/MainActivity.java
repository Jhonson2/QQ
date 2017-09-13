package com.example.dellc.qq;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);
        BottomBarTab tabWithId=mBottomBar.getTabWithId(R.id.conversation);
        tabWithId.setBadgeCount(10);
    }

    //tab的事件监听
    private OnTabSelectListener mOnTabSelectListener=new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {

        }
    };


}
