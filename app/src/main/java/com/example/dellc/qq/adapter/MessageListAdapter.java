package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by dellc on 2017/10/5.
 */

public class MessageListAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<EMMessage> mMessage;

    public MessageListAdapter(Context context, List<EMMessage> messages){
        this.mContext=context;
        this.mMessage=messages;

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

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
