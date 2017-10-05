package com.example.dellc.qq.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.SearchResultAdapter;
import com.example.dellc.qq.presenter.AddFriendPersenter;
import com.example.dellc.qq.presenter.impl.AddFreindPersenterImp;
import com.example.dellc.qq.view.AddFreindView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dellc on 2017/9/19.
 */
public class AddFriendsActivity extends BaseActivity implements AddFreindView {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.search)
    Button mSearch;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.keyword)
    EditText mKeyword;
    @BindView(R.id.empty)
    TextView mEmpty;

    private AddFriendPersenter mAddFriendPersenter;
    private SearchResultAdapter mSearchResultAdapter;


    @Override
    public int getLayoutResID() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void init() {
        super.init();
        mAddFriendPersenter = new AddFreindPersenterImp(this);
        mTitle.setText(getString(R.string.add_friend));
        mKeyword.setOnEditorActionListener(mOnEditorActionListener);
        //初始化RecyclerView
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);//recyclerView条目的大小自适应
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultAdapter=new SearchResultAdapter(this,mAddFriendPersenter.getSearchResult());
        mRecyclerView.setAdapter(mSearchResultAdapter);

    }


    @OnClick(R.id.search)
    public void onClick() {
        search();
    }

    private void search() {
        showProgress(getString(R.string.searching));
        hideKeyboard();
        String keyword = mKeyword.getText().toString().trim();
        mAddFriendPersenter.searchFreind(keyword);
    }

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search();
                return true;
            }

            return false;
        }
    };


    @Override
    public void onSearchSuccess() {
        hideProgress();
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
        toast(getString(R.string.search_success));

    }

    @Override
    public void onSearchFailed() {
        hideProgress();
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
        toast(getString(R.string.search_failed));
        mSearchResultAdapter.notifyDataSetChanged();//通知view层刷新结果列表
    }

    @Override
    public void onSearchEmpty() {
        hideProgress();
        mRecyclerView.setVisibility(View.GONE);
        mSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddFreindFailed() {
        toast(getString(R.string.send_add_freind_request_failed));
    }

    @Override
    public void onAddFreindSuccess() {
        toast(getString(R.string.send_add_freind_request_success));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddFriendPersenter.destroy();
    }
}
