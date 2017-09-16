package com.example.dellc.qq.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dellc on 2017/9/13.
 */

public class DynamicFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.logout)
    Button mTogout;


    @Override
    public int getLayoutID() {
        return R.layout.fragment_dynamic;
    }


    @Override
    protected void init() {
        super.init();
        mTitle.setText(getString(R.string.dynamic));

        String logout=String.format(getString(R.string.logout),
                EMClient.getInstance().getCurrentUser());
        mTogout.setText(logout);
    }

    @OnClick(R.id.logout)
    public void onClick() {
    }
}
