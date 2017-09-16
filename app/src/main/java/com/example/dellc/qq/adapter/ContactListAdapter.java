package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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


    public  ContactListAdapter(Context context, List<ContactItem> items){
        mContext=context;
        mContactItems=items;
    }


    @Override
    public ContactListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ContactListItemViewHolder(new ContactItemView(mContext));
    }

    @Override
    public void onBindViewHolder(ContactListItemViewHolder holder, int position) {
        holder.mContactItemView.bindView(mContactItems.get(position));
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
}
