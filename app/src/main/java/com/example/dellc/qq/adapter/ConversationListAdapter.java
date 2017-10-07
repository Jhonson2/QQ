package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dellc.qq.widget.ConversationItemView;
import com.hyphenate.chat.EMConversation;

import java.util.List;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ConversationItemViewHolder> {
    private Context mContext;
    private List<EMConversation> mEMConversation;

    public ConversationListAdapter(Context context, List<EMConversation> emConversations) {
        this.mContext=context;
        this.mEMConversation = emConversations;
    }

    @Override
    public ConversationItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConversationItemViewHolder(new ConversationItemView(mContext));
    }

    @Override
    public void onBindViewHolder(ConversationItemViewHolder holder, int position) {
        holder.mConversationItemView.bindView(mEMConversation.get(position));
    }



    @Override
    public int getItemCount() {
        return mEMConversation.size();
    }

    public class ConversationItemViewHolder extends RecyclerView.ViewHolder{
        private ConversationItemView mConversationItemView;


        public ConversationItemViewHolder(ConversationItemView itemView) {
            super(itemView);
            this.mConversationItemView=itemView;
        }
    }
}
