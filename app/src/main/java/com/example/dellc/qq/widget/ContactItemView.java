package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.model.ContactItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dellc on 2017/9/16.
 */

public class ContactItemView extends RelativeLayout {

    @BindView(R.id.first_letter)
    TextView mFirstLetter;
    @BindView(R.id.user_name)
    TextView mUserName;

    public ContactItemView(Context context) {
        this(context, null);
    }

    public ContactItemView(Context context, AttributeSet attr) {
        super(context, attr);
        init();

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_contact_list_item, this);
        ButterKnife.bind(this, this);
    }


    public void bindView(ContactItem contactItem) {
        mFirstLetter.setText(contactItem.getFristLetter());
        mUserName.setText(contactItem.getUserName());

    }
}
