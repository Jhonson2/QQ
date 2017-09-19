package com.example.dellc.qq.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.dellc.qq.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dellc on 2017/9/19.
 */
public class AddFriendsActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void init() {
        super.init();
        mTitle.setText(getString(R.string.add_friends));
    }

}
