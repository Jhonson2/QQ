package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.dellc.qq.R;

/**
 * Created by dellc on 2017/9/16.
 */

public class ContactItemView extends RelativeLayout{

    public ContactItemView(Context context) {
        this(context,null);
    }

    public ContactItemView(Context context, AttributeSet attr) {
        super(context,attr);
        init();

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_contact_list_item,this);
    }


}
