package com.example.dellc.qq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dellc.qq.model.SearchResultItem;
import com.example.dellc.qq.widget.SearchResultItemView;

import java.util.List;

/**
 * Created by dellc on 2017/9/30.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultItemViewHolder> {
    private Context mContext;
    private List<SearchResultItem> mSearchResultItems;

    public SearchResultAdapter(Context context, List<SearchResultItem> items){
        this.mContext=context;
        this.mSearchResultItems=items;
    }

    @Override
    public SearchResultItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchResultItemViewHolder(new SearchResultItemView(mContext));
    }

    @Override
    public void onBindViewHolder(SearchResultItemViewHolder holder, int position) {
        holder.mSearchResultItemView.bindView(mSearchResultItems.get(position));
    }



    @Override
    public int getItemCount() {
        return mSearchResultItems.size();
    }

    public class SearchResultItemViewHolder extends RecyclerView.ViewHolder{
        private SearchResultItemView mSearchResultItemView;

        public SearchResultItemViewHolder(SearchResultItemView itemView) {
            super(itemView);
            mSearchResultItemView=itemView;
        }
    }

}
