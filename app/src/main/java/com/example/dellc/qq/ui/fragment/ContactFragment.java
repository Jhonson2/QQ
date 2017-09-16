package com.example.dellc.qq.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dellc.qq.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dellc on 2017/9/13.
 */

public class ContactFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    public int getLayoutID() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init() {
        super.init();
        mTitle.setText(getString(R.string.contact));
        mAdd.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.add)
    public void onClick() {
    }
}
