package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.model.SearchResultItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索好友列表的item的模块化
 * Created by dellc on 2017/9/30.
 */

public class SearchResultItemView extends RelativeLayout {
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.add_friend)
    Button mAddFriend;

    public SearchResultItemView(Context context) {
        this(context, null);
    }

    public SearchResultItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_search_result, this);
        ButterKnife.bind(this);
    }

    //绑定数据集合的控件
    public void bindView(SearchResultItem searchResultItem) {
        mUserName.setText(searchResultItem.userName);
        mTimestamp.setText(searchResultItem.timestamp);

        //如果已经添加过的，按钮不能操作
        if(searchResultItem.added){
            mAddFriend.setEnabled(false);
            mAddFriend.setText(R.string.already_added);
        }else{
            mAddFriend.setEnabled(true);
            mAddFriend.setText(R.string.add_friend);
        }
    }

    @OnClick(R.id.add_friend)
    public void onClick() {
    }
}
