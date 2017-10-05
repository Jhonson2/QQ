package com.example.dellc.qq.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.ContactListAdapter;
import com.example.dellc.qq.presenter.ContactPersenter;
import com.example.dellc.qq.presenter.impl.ContactPersenterImpl;
import com.example.dellc.qq.ui.activity.AddFriendsActivity;
import com.example.dellc.qq.view.ContactView;
import com.example.dellc.qq.widget.SlideBar;
import com.hyphenate.EMClientListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dellc on 2017/9/13.
 */

public class ContactFragment extends BaseFragment implements ContactView {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshayout;
    @BindView(R.id.slide_bar)
    SlideBar mSlideBar;

    @BindView(R.id.first_letter)
    TextView mFirstLetter;

    private ContactPersenter mContactPersenter;
    private ContactListAdapter mContactListAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init() {
        super.init();
        mContactPersenter = new ContactPersenterImpl(this);
        mTitle.setText(getString(R.string.contact));
        mAdd.setVisibility(View.VISIBLE);
        //初始化SlideBar()
        initSlideBar();
        //初始化initRecycleView
        initRecycleView();
        //初始化刷新小圆圈
        initSwipeRefreshLayout();

        //加载联系人数据
        mContactPersenter.loadContacts();

        EMClient.getInstance().contactManager().setContactListener(mEMContactListener);
    }

    private void initSlideBar() {
        mSlideBar.setOnSlideChangeListener(mOnSlideChangeListener);
    }

    private void initSwipeRefreshLayout() {
        //配置圈的颜色变化
        mSwipeRefreshayout.setColorSchemeResources(R.color.qq_bule, R.color.qq_red);
        mSwipeRefreshayout.setOnRefreshListener(mOnRefreshListener);
    }

    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mContactListAdapter = new ContactListAdapter(getContext(), mContactPersenter.getContacts());
        mRecyclerView.setAdapter(mContactListAdapter);
    }

    @OnClick(R.id.add)
    public void onClick() {
        goTo(AddFriendsActivity.class, false);
    }

    @Override
    public void onLoadContactsSuccess() {
        toast(getString(R.string.load_contacts_success));
        //恢复swiperrefreshlayout,隐藏圆圈
        mSwipeRefreshayout.setRefreshing(false);
        //刷新列表
        mContactListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadContactsFailed() {
        toast(getString(R.string.load_contacts_failed));
    }

    //再次刷新联系人列表
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener =
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mContactPersenter.refreshContacts();

                }
            };

    private SlideBar.onSlideChangeListener mOnSlideChangeListener =
            new SlideBar.onSlideChangeListener() {
                @Override
                public void onSildeChange(int index, String firstLetter) {
                    Log.d(TAG, "onSildeChange" + firstLetter);
                    //显示悬浮文本
                    mFirstLetter.setVisibility(View.VISIBLE);
                    mFirstLetter.setText(firstLetter);

                    //找出首字符的mFirstLetter联系人第一个
                    for (int i = 0; i < mContactPersenter.getContacts().size(); i++) {
                        if (firstLetter.equals(mContactPersenter.getContacts().get(i).getFristLetter())) {
                            mRecyclerView.scrollToPosition(i);
                            break;
                        }
                    }
                }

                @Override
                public void onSlidingFinish() {
                    mFirstLetter.setVisibility(View.GONE);//隐藏中间绿色背景的首字符
                }
            };
    //监听好友状态事件
    private EMContactListener mEMContactListener = new EMContactListener() {

        /**
         * 增加联系人时回调此方法
         *
         * @param username
         */
        @Override
        public void onContactAdded(String username) {
            mContactPersenter.refreshContacts();//增加好友后，自动刷新好友列表
            Log.d(TAG, "onContactAdded" + username);
        }

        /**
         * 被删除时回调此方法
         * @param username
         */
        @Override
        public void onContactDeleted(String username) {
            mContactPersenter.refreshContacts();//增加好友后，自动刷新好友列表
            Log.d(TAG, "onContactDeleted" + username);
        }


        /**
         *  收到好友邀请
         *   当接受好友请求时，默认统一添加好友 options.setAcceptInvitationAlways(true);
         * @param username
         * @param reason
         */
        @Override
        public void onContactInvited(String username, String reason) {
            /*
            *注意：如果要手动同意需要在初始化SDK时调用 opptions.setAcceptInvitationAlways(false); 。
             */
            // EMClient.getInstance().contactManager().acceptInvitation(username);//同意好友请求
            // EMClient.getInstance().contactManager().declineInvitation(username);//拒绝好友请求

        }

        /**
         * 好友请求被同意
         * @param username
         */
        @Override
        public void onFriendRequestAccepted(String username) {
            Log.d(TAG, "onFriendRequestAccepted" + username);
            // EMClient.getInstance().contactManager().acceptInvitation(username);//同意好友请求

        }

        /**
         *  好友请求被拒绝
         * @param username
         */
        @Override
        public void onFriendRequestDeclined(String username) {
            Log.d(TAG, "onFriendRequestDeclined" + username);
            // EMClient.getInstance().contactManager().declineInvitation(username);//拒绝好友请求
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().contactManager().removeContactListener(mEMContactListener);
    }
}


