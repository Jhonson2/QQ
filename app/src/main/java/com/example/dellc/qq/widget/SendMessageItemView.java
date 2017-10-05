package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.dellc.qq.R;

/**
 * Created by dellc on 2017/10/5.
 */

public class SendMessageItemView extends RelativeLayout {

    public SendMessageItemView(Context context) {
        this(context,null);
    }

    public SendMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_send_message_item,this);
    }
}
