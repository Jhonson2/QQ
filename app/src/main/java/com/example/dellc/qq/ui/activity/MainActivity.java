package com.example.dellc.qq.ui.activity;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.dellc.qq.R;
import com.example.dellc.qq.factory.FragmentFactory;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private FragmentManager mFragmentManager;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        mFragmentManager=getSupportFragmentManager();

        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);
        BottomBarTab tabWithId=mBottomBar.getTabWithId(R.id.conversation);
        tabWithId.setBadgeCount(10);
    }

    //tab的事件监听
    private OnTabSelectListener mOnTabSelectListener=new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
           FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, FragmentFactory.getInstance().getFragment(tabId));
            fragmentTransaction.commit();
        }
    };


}
