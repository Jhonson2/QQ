package com.example.dellc.qq.ui.activity;

import android.icu.text.IDNA;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.adapter.MessageListAdapter;
import com.example.dellc.qq.app.Constant;
import com.example.dellc.qq.presenter.ChatPersenter;
import com.example.dellc.qq.presenter.impl.ChatPresenterImpl;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ChatView;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dellc on 2017/10/5.
 */
public class ChatActivity extends BaseActivity implements ChatView{
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.message)
    EditText mMessage;
    @BindView(R.id.send)
    Button mSend;

    private ChatPersenter mChatPersenter;
    private String userName;

    private MessageListAdapter mMessageListAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_chat;
    }

    @Override
    protected void init() {
        super.init();
        mChatPersenter=new ChatPresenterImpl(this);
        userName= getIntent().getStringExtra(Constant.Extra.USER_NAME);
        String title=String.format(getString(R.string.chat_title),userName);
        mTitle.setText(title);
        mBack.setVisibility(View.VISIBLE);
        mMessage.addTextChangedListener(mTextWatcher);
        mMessage.setOnEditorActionListener(mOnEditorActionListener);

        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListener);//接收消息

        initRecyclerView();//初始化聊天界面的RecyclerView
        mChatPersenter.loadMessage(userName);//加载聊天记录
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageListAdapter=new MessageListAdapter(this,mChatPersenter.getMessage());
        mRecyclerView.setAdapter(mMessageListAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

    }

    @OnClick({R.id.back, R.id.send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.send:
                //按钮：发送
                sendMessage();
                break;
        }
    }
//发送的方法
    private void sendMessage() {
        hideKeyboard();
        String message= mMessage.getText().toString().trim();//发送的消息
        mChatPersenter.sendMessage(userName,message);

    }

    //键盘：发送键盘中按钮
    private TextView.OnEditorActionListener mOnEditorActionListener=new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId== EditorInfo.IME_ACTION_SEND){
                sendMessage();
                return true;
            }
            return false;
        }
    };

    //文本变化
    private TextWatcher mTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mSend.setEnabled(s.length()>0);
        }
    };

    @Override
    public void onStartSendMessage() {
        showProgress(getString(R.string.sending));
        mMessageListAdapter.notifyDataSetChanged();
        smoothScrollToBottom();

    }

    @Override
    public void onSendMessageSuccess() {
        hideProgress();
        hideKeyboard();
        toast(getString(R.string.send_success));
        mMessage.getText().clear();//发送成功后清空输入框
        mMessageListAdapter.notifyDataSetChanged();//刷新聊天列表


    }

    @Override
    public void onSendMessageFailed() {
        hideProgress();
        toast(getString(R.string.send_failed));
        mMessageListAdapter.notifyDataSetChanged();//刷新聊天列表
    }

    @Override
    public void onLoadMessageSuccess() {
        mMessageListAdapter.notifyDataSetChanged();//刷新聊天列表
        smoothToBottom();
        toast(getString(R.string.load_message_success));
    }


    /**
     * 加载更多数据成功
     */
    @Override
    public void onLoadMoreMessageSuccess(int size) {
        toast(getString(R.string.load_moore_message_success));
        mMessageListAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(size);//滚动rececyclerView到指定位置size
    }

    @Override
    public void onNoMoreData() {
        toast(getString(R.string.no_more_data));
    }

    /**
     * 设计接送消息的监听
     */
    private EMMessageListener mEMMessageListener=new EMMessageListener() {
        //收到消息
        @Override
        public void onMessageReceived(final List<EMMessage> list) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //刷新聊天列表
                    mMessageListAdapter.addNewMessage(list.get(0));//单聊
                    smoothScrollToBottom();
                }
            });
        }

        //收到透传消息
        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        //收到已读回执
        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        //收到已送达回执
        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        //消息回复
        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }
        //消息状态变动
        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    /**
     * 屏幕平和滚动
     */
    private void smoothScrollToBottom() {
        mRecyclerView.smoothScrollToPosition(mChatPersenter.getMessage().size()-1);//自动滚动到底部
    }

    /**
     *
     */

    private void smoothToBottom() {
        mRecyclerView.scrollToPosition(mChatPersenter.getMessage().size()-1);//自动滚动到底部
    }

    /**
     *滚动监听
     */
    private RecyclerView.OnScrollListener mOnScrollListener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
           if(newState==RecyclerView.SCROLL_STATE_IDLE){
               //滚动结束时，是否加载更多
               if(mLinearLayoutManager.findFirstVisibleItemPosition()==0){
                    mChatPersenter.loadMoreMessage(userName);
               }
           }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //不需要时，要移除监听
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListener);
    }
}
