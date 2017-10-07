package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.presenter.ConversationPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ConversationView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by dellc on 2017/10/7.
 */

public class ConversationPresenterImpl implements ConversationPersenter {

    public ConversationView mConversationView;

    private List<EMConversation> mEMConversation;

    public ConversationPresenterImpl(ConversationView conversationView){
        mEMConversation=new ArrayList<EMConversation>();
        mConversationView=conversationView;
    }

    @Override
    public void loadConversation() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //清空原来的会话全部数据集合
                mEMConversation.clear();

                Map<String, EMConversation> conversations = EMClient.getInstance()
                        .chatManager().getAllConversations();
                mEMConversation.addAll(conversations.values());
                //最后一条信息时间戳：排序
                Collection<EMConversation> values=conversations.values();
               // List<EMConversation> values= (List<EMConversation>) conversations.values();
                Collections.sort(mEMConversation, new Comparator<EMConversation>() {
                    @Override
                    public int compare(EMConversation o1, EMConversation o2) {
                        //降序排列，根据最后一条消息的时间戳

                        return (int) (o2.getLastMessage().getMsgTime()-o1.getLastMessage().getMsgTime());
                    }
                });



                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mConversationView.onLoadConversationSuccess();

                    }
                });
            }
        });
    }

    /**
     * 获取消息（会话）数据
     * @return
     */
    @Override
    public List<EMConversation> getConversations() {
        return mEMConversation;
    }
}
