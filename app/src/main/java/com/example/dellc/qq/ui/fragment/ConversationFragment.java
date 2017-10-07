package com.example.dellc.qq.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.ConversationListAdapter;
import com.example.dellc.qq.presenter.ConversationPersenter;
import com.example.dellc.qq.presenter.impl.ConversationPresenterImpl;
import com.example.dellc.qq.view.ConversationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dellc on 2017/9/13.
 */

public class ConversationFragment extends BaseFragment implements ConversationView{
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ConversationPersenter mConversationPersenter;
    private ConversationListAdapter mConversationListAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void init() {
        super.init();
        mConversationPersenter=new ConversationPresenterImpl(this);
        mTitle.setText(getString(R.string.conversation));
        initRececylerView();
        mConversationPersenter.loadConversation();
    }

    private void initRececylerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mConversationListAdapter=new ConversationListAdapter(getContext(),null);
        mRecyclerView.setAdapter(mConversationListAdapter);
    }

    /**
     * 加载信息（会话）成功
     */
    @Override
    public void onLoadConversationSuccess() {
        toast(getString(R.string.load_conversation_success));
    }
}
