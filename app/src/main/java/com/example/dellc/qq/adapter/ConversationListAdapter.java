package com.example.dellc.qq.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dellc.qq.app.Constant;
import com.example.dellc.qq.ui.activity.ChatActivity;
import com.example.dellc.qq.widget.ConversationItemView;
import com.hyphenate.chat.EMConversation;

import java.util.List;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ConversationItemViewHolder> {
    private Context mContext;
    private List<EMConversation> mEMConversations;

    public ConversationListAdapter(Context context, List<EMConversation> emConversations) {
        this.mContext=context;
        this.mEMConversations = emConversations;
    }

    @Override
    public ConversationItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConversationItemViewHolder(new ConversationItemView(mContext));
    }

    @Override
    public void onBindViewHolder(ConversationItemViewHolder holder, final int position) {
        holder.mConversationItemView.bindView(mEMConversations.get(position));
        //设计Converstatiomitem长点击事件
        holder.mConversationItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到聊天界面
                Intent intent=new Intent(mContext, ChatActivity.class);
                intent.putExtra(Constant.Extra.USER_NAME,mEMConversations.get(position).conversationId());
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mEMConversations.size();
    }

    public class ConversationItemViewHolder extends RecyclerView.ViewHolder{
        private ConversationItemView mConversationItemView;


        public ConversationItemViewHolder(ConversationItemView itemView) {
            super(itemView);
            this.mConversationItemView=itemView;
        }
    }
}
