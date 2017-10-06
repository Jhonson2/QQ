package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dellc.qq.widget.ReceiveMessageItemView;
import com.example.dellc.qq.widget.SendMessageItemView;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by dellc on 2017/10/5.
 */

public class MessageListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<EMMessage> mMessage;

    private static final int ITEM_TYPE_SEND = 0;
    private static final int ITEM_TYPE_RECEIVE = 1;

    public MessageListAdapter(Context context, List<EMMessage> messages) {
        this.mContext = context;
        this.mMessage = messages;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_SEND) {
            return new SendMessageItemViewHolder(new SendMessageItemView(mContext));
        } else {
            return new ReceiveMessageItemViewHolder(new ReceiveMessageItemView(mContext));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //绑定ViewHolder刷新数据
        //显示时间戳
        boolean showTimestamp = false;
        //如果是第一条消息或者时间间隔大于30s,显示时间戳
        if (position == 0 || shouldShowTimestamp(position)) {
            showTimestamp = true;
        }
        //发送消息
        if (holder instanceof SendMessageItemViewHolder) {
            ((SendMessageItemViewHolder) holder).mSendMessageItemView.bindView(mMessage.get(position), showTimestamp);
            //接收消息
        } else {
            ((ReceiveMessageItemViewHolder) holder).mReceiveMessageItemView.bindView(mMessage.get(position),showTimestamp);
        }
    }

    private boolean shouldShowTimestamp(int position) {
        long currentTimestamp = mMessage.get(position).getMsgTime();//获取当前消息的时间戳
        long preTimestamp = mMessage.get(position - 1).getMsgTime();//获取上一条消息的时间戳

        return currentTimestamp - preTimestamp > 3000;
        //如果时间间隔大于30s 的
 /*       if(currentTimestamp-preTimestamp > 120000){
            return true;
        }
        return false;
   */

    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    @Override
    public int getItemViewType(int position) {
        //返回消息的类型
        return mMessage.get(position).direct() == EMMessage.Direct.SEND ? ITEM_TYPE_SEND : ITEM_TYPE_RECEIVE;
    }

    public void addNewMessage(EMMessage emMessage) {
        mMessage.add(emMessage);
        notifyDataSetChanged();
    }

    //发送消息的ViewHolder
    public class SendMessageItemViewHolder extends RecyclerView.ViewHolder {

        public SendMessageItemView mSendMessageItemView;

        public SendMessageItemViewHolder(SendMessageItemView itemView) {
            super(itemView);
            mSendMessageItemView = itemView;

        }
    }

    //接收消息的ViewHolder
    public class ReceiveMessageItemViewHolder extends RecyclerView.ViewHolder {

        public ReceiveMessageItemView mReceiveMessageItemView;

        public ReceiveMessageItemViewHolder(ReceiveMessageItemView itemView) {
            super(itemView);
            mReceiveMessageItemView = itemView;

        }
    }
}

