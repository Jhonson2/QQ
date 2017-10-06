package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dellc on 2017/10/5.
 */

public class ReceiveMessageItemView extends RelativeLayout {

    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.recevie_message)
    TextView mRecevieMessage;

    public ReceiveMessageItemView(Context context) {
        this(context, null);
    }

    public ReceiveMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        ButterKnife.bind(this,this);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_recevie_message_item, this);
    }

    public void bindView(EMMessage emMessage) {
        updateTimestamp(emMessage);//刷新时间
        updateText(emMessage);//刷新文本
    }

    private void updateText(EMMessage emMessage) {
        //刷新文本
        EMMessageBody body = emMessage.getBody();
        //暂时接收文本消息
        if (body instanceof EMMessageBody) {
            mRecevieMessage.setText(((EMTextMessageBody) body).getMessage());
        } else {
            //非文本消息
            mRecevieMessage.setText(getContext().getString(R.string.no_text_message));
        }
    }

    private void updateTimestamp(EMMessage emMessage) {
        //刷新时间
        long msgTime = emMessage.getMsgTime();
        String time = DateUtils.getTimestampString(new Date(msgTime));
        mTimestamp.setText(time);
    }
}
