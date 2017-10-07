package com.example.dellc.qq.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.ConversationListAdapter;
import com.example.dellc.qq.adapter.EMMessageListenerAdapter;
import com.example.dellc.qq.presenter.ConversationPersenter;
import com.example.dellc.qq.presenter.impl.ConversationPresenterImpl;
import com.example.dellc.qq.view.ConversationView;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;

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
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListener);
        mConversationPersenter.loadConversation();
    }

    private void initRececylerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mConversationListAdapter=new ConversationListAdapter(getContext(),mConversationPersenter.getConversations());
        mRecyclerView.setAdapter(mConversationListAdapter);
    }

    /**
     * 加载信息（会话）成功
     */
    @Override
    public void onLoadConversationSuccess() {
        toast(getString(R.string.load_conversation_success));
        mConversationListAdapter.notifyDataSetChanged();
    }

    /**
     * 发送新的信息的监听
     */
    private EMMessageListenerAdapter mEMMessageListener=new EMMessageListenerAdapter() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //重新加载会话数据
            mConversationPersenter.loadConversation();

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListener);
    }
}
