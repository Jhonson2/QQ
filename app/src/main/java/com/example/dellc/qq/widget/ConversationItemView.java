package com.example.dellc.qq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationItemView extends RelativeLayout {
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.last_message)
    TextView mLastMessage;
    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.unread_count)
    TextView mUnreadCount;

    public ConversationItemView(Context context) {
        this(context, null);
    }

    public ConversationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_conversation_item, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(EMConversation emConversation) {
        //用户名
        mUserName.setText(emConversation.conversationId());

        //最后一条消息
        EMMessage lastMessage = emConversation.getLastMessage();
        EMMessageBody body = lastMessage.getBody();

        if (body instanceof EMTextMessageBody) {
            //文本消息
            mLastMessage.setText(((EMTextMessageBody) body).getMessage());
        } else {
            // 非文本消息
            mLastMessage.setText(R.string.no_text_message);
        }

        //时间戳
        Long lastMsgTime=lastMessage.getMsgTime();
        String timestampString=DateUtils.getTimestampString(new Date(lastMsgTime));
        mTimestamp.setText(timestampString);

        //未读消息个数
        int unreadCount=emConversation.getUnreadMsgCount();
        if(unreadCount==0){
            mUnreadCount.setVisibility(GONE);
        }else {
            mUnreadCount.setVisibility(VISIBLE);
            mUnreadCount.setText(String.valueOf(unreadCount));
        }
    }
}
