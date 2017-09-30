package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.dellc.qq.R;
import com.example.dellc.qq.model.SearchResultItem;

/**
 * 搜索好友列表的item的模块化
 * Created by dellc on 2017/9/30.
 */

public class SearchResultItemView extends RelativeLayout {
    public SearchResultItemView(Context context) {
        this(context,null);
    }

    public SearchResultItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_search_result,this);
    }
}
