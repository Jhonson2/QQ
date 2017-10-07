package com.example.dellc.qq.ui.activity;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.EMMessageListenerAdapter;
import com.example.dellc.qq.factory.FragmentFactory;
import com.example.dellc.qq.utils.ThreadUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

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
        mFragmentManager = getSupportFragmentManager();

        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);

        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListenerAdapter);
        updateUnreadCount();
    }

    private void updateUnreadCount() {
        BottomBarTab tabWithId = mBottomBar.getTabWithId(R.id.conversation);
        //从环信中，获取未读消息的总数
        int unreadMsgsCount = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        tabWithId.setBadgeCount(unreadMsgsCount);
    }

    //tab的事件监听
    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, FragmentFactory.getInstance().getFragment(tabId));
            fragmentTransaction.commit();
        }
    };

    /**
     * 未读信息tab的监听
     */
    private EMMessageListenerAdapter mEMMessageListenerAdapter = new EMMessageListenerAdapter() {

        @Override
        public void onMessageReceived(List<EMMessage> list) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateUnreadCount();
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListenerAdapter);
    }
}
