package com.example.dellc.qq.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dellc.qq.R;
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

    private AddFriendPersenter mAddFriendPersenter;



    @Override
    public int getLayoutResID() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void init() {
        super.init();
        mAddFriendPersenter=new AddFreindPersenterImp(this);
        mTitle.setText(getString(R.string.add_friends));
        mKeyword.setOnEditorActionListener(mOnEditorActionListener);
    }


    @OnClick(R.id.search)
    public void onClick() {
        search();

    }

    private void search() {
        String keyword=mKeyword.getText().toString().trim();
        mAddFriendPersenter.searchFreind(keyword);
    }

    private TextView.OnEditorActionListener mOnEditorActionListener=new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId== EditorInfo.IME_ACTION_SEARCH){
                search();
                return true;
            }

            return false;
        }
    };


}
