package com.example.dellc.qq.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.ContactListAdapter;
import com.example.dellc.qq.presenter.ContactPersenter;
import com.example.dellc.qq.presenter.impl.ContactPersenterImpl;
import com.example.dellc.qq.view.ContactView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dellc on 2017/9/13.
 */

public class ContactFragment extends BaseFragment implements ContactView{

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ContactPersenter mContactPersenter;
    private ContactListAdapter mContactListAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init() {
        super.init();
        mContactPersenter=new ContactPersenterImpl(this);
        mTitle.setText(getString(R.string.contact));
        mAdd.setVisibility(View.VISIBLE);
        
        initRecycleView();

        //加载联系人数据
        mContactPersenter.loadContacts();
    }

    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mContactListAdapter=new ContactListAdapter(getContext(),mContactPersenter.getContacts());
        mRecyclerView.setAdapter(mContactListAdapter);
    }

    @OnClick(R.id.add)
    public void onClick() {
    }

    @Override
    public void onLoadContactsSuccess() {
        toast(getString(R.string.load_contacts_success));
        //刷新列表
        mContactListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadContactsFailed() {
        toast(getString(R.string.load_contacts_failed));
    }
}