package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.dellc.qq.R;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationItemView extends RelativeLayout{
    public ConversationItemView(Context context) {
        this(context,null);
    }

    public ConversationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_conversation_item,this);
    }
}
