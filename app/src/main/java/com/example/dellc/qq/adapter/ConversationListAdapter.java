package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hyphenate.chat.EMConversation;

import java.util.List;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<EMConversation> mEMConversation;

    public ConversationListAdapter(Context context, List<EMConversation> emConversations) {
        this.mContext=context;
        this.mEMConversation = emConversations;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
