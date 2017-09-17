package com.example.dellc.qq.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.dellc.qq.R;
import com.hyphenate.util.DensityUtil;

/**
 * 自定义控件SlideBar
 * Created by dellc on 2017/9/17.
 */

public class SlideBar extends View {
    private Paint mPaint;
    private static final String[] SECTIONS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //暂时写20
    private float mTextSize;

    private float mTextBaseline = 0;
    //当前下标
    private int mCurrentIndex = -1;

    private onSlideChangeListener mOnSlideChangeListener;

    public SlideBar(Context context) {
        this(context, null);
    }

    public SlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(DensityUtil.sp2px(getContext(), 12));
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);//设置文本居中
        mPaint.setColor(getResources().getColor(R.color.silde_bar_text_color));//设置文本颜色
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mTextSize = h * 1.0f / SECTIONS.length;//计算分配给各个字符的高度
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float mTextHeight = fontMetrics.descent - fontMetrics.ascent;//获取绘制字符的实际高度
        mTextBaseline = mTextSize / 2 + mTextHeight / 2 - fontMetrics.descent;//计算字符居中时的baseline

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = getWidth() * 1.0f / 2;
        //float y = mTextSize;
        float baseline = mTextBaseline;
        for (int i = 0; i < SECTIONS.length; i++) {
            canvas.drawText(SECTIONS[i], x, baseline, mPaint);
            baseline += mTextSize;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.bg_slide_bar);
                notifyPositionChange(event);
                break;

            case MotionEvent.ACTION_MOVE:
                notifyPositionChange(event);
                break;

            case MotionEvent.ACTION_UP:
                if (mOnSlideChangeListener != null) {
                    setBackgroundColor(Color.TRANSPARENT);
                    mOnSlideChangeListener.onSlidingFinish();
                }
                break;
        }
        return true;
    }

    private void notifyPositionChange(MotionEvent event) {
        //移动右侧某字符时,同时移动该字符的联系人
        int index = (int) (event.getY() / mTextSize);
        if (index < 0 || index > SECTIONS.length - 1) {
            return;
        }
        //位置下标出现变化时，才触摸ACTION_MOVE事件通知外界contactFragment
        if (index != mCurrentIndex) {
            String firstLetter = SECTIONS[index];
            if (mOnSlideChangeListener != null) {
                mOnSlideChangeListener.onSildeChange(index, firstLetter);
            }
        }
        mCurrentIndex = index;
    }

    public interface onSlideChangeListener {
        void onSildeChange(int index, String firstLetter);

        void onSlidingFinish();

    }

    public void setOnSlideChangeListener(onSlideChangeListener l) {
        mOnSlideChangeListener = l;

    }
}
