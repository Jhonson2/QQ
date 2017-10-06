package com.example.dellc.qq.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
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

public class SendMessageItemView extends RelativeLayout {

    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.send_message)
    TextView mSendMessage;
    @BindView(R.id.progress)
    ImageView mProgress;

    public SendMessageItemView(Context context) {
        this(context, null);
    }

    public SendMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_send_message_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(EMMessage  emMessage,boolean showTimestamp) {
        updateTimestamp(showTimestamp,emMessage);//刷新时间
        updateText(emMessage);//刷新文本
        updateProgress(emMessage);  //刷新进度菊花
    }

    private void updateProgress(EMMessage emMessage) {
        //刷新进度菊花
        switch (emMessage.status()) {
            case INPROGRESS:
                //帧动画:转动
                mProgress.setVisibility(View.VISIBLE);
                mProgress.setImageResource(R.drawable.send_msg_progress);
                AnimationDrawable drawable = (AnimationDrawable) mProgress.getDrawable();
                drawable.start();
                break;
            case SUCCESS:
                mProgress.setVisibility(View.GONE);
                break;
            case FAIL:
                mProgress.setVisibility(View.VISIBLE);
                mProgress.setImageResource(R.mipmap.msg_error);
                break;

        }
    }

    private void updateText(EMMessage emMessage) {
        //刷新文本
        EMMessageBody body = emMessage.getBody();
        //暂时接收文本消息
        if (body instanceof EMMessageBody) {
            mSendMessage.setText(((EMTextMessageBody) body).getMessage());
        } else {
            //非文本消息
            mSendMessage.setText(getContext().getString(R.string.no_text_message));
        }
    }

    private void updateTimestamp(boolean showTimestamp,EMMessage emMessage) {
        //刷新时间
        if(showTimestamp){
            mTimestamp.setVisibility(View.VISIBLE);
            long msgTime = emMessage.getMsgTime();
            String time = DateUtils.getTimestampString(new Date(msgTime));
            mTimestamp.setText(time);
        }else{
            mTimestamp.setVisibility(View.GONE);
        }


    }
}
