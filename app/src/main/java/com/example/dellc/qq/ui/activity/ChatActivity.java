package com.example.dellc.qq.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dellc.qq.R;
import com.example.dellc.qq.app.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dellc on 2017/10/5.
 */
public class ChatActivity extends BaseActivity {
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

    @Override
    public int getLayoutResID() {
        return R.layout.activity_chat;
    }

    @Override
    protected void init() {
        super.init();
        String userName=getIntent().getStringExtra(Constant.Extra.USER_NAME);
        String title=String.format(getString(R.string.chat_title),userName);
        mTitle.setText(title);
        mBack.setVisibility(View.VISIBLE);
        mMessage.addTextChangedListener(mTextWatcher);
    }

    @OnClick({R.id.back, R.id.send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();

                break;
            case R.id.send:
                break;
        }
    }
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
}
