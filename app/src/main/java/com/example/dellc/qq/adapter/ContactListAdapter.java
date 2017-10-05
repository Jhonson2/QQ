package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.example.dellc.qq.model.ContactItem;
import com.example.dellc.qq.widget.ContactItemView;
import java.util.List;

/**
 * Created by dellc on 2017/9/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListItemViewHolder>{
    private Context mContext;
    private List<ContactItem> mContactItems;
    private OnItemClickListener mOnItemClickListener;


    public  ContactListAdapter(Context context, List<ContactItem> items){
        mContext=context;
        mContactItems=items;
    }


    @Override
    public ContactListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactListItemViewHolder(new ContactItemView(mContext));
    }

    @Override
    public void onBindViewHolder(ContactListItemViewHolder holder, final int position) {
        holder.mContactItemView.bindView(mContactItems.get(position));
        //设计item的长按或者是点击事件
        holder.mContactItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mOnItemClickListener!=null){
                   mOnItemClickListener.onClick(mContactItems.get(position).getUserName());
               }
            }
        });

        holder.mContactItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onLongClick(mContactItems.get(position).getUserName());
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContactItems.size();
    }

    public class  ContactListItemViewHolder extends RecyclerView.ViewHolder {
      private ContactItemView mContactItemView;

        public ContactListItemViewHolder(ContactItemView itemView) {
            super(itemView);
            mContactItemView=itemView;
        }
    }

    //定义点击和长按接口
    public  interface OnItemClickListener{
        void onClick(String userName);

        void onLongClick(String userName);
    }

    public void setItemClickListener(OnItemClickListener l){
        mOnItemClickListener=l;
    }




}
