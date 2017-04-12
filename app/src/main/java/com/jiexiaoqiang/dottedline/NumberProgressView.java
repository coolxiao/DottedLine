package com.jiexiaoqiang.dottedline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jiexiaoqiang on 2017/3/22 0022.
 */

public class NumberProgressView extends View {
    private final int RECT_HEIGHT = 10;

    private int mWidth;
    private int mHeight;

    private float progress = 0;
    private float mTextLength;
    private int mRectMaxLength;

    private Paint mFramePaint;
    private Paint mProgressPaint;

    private Rect mFrameRect = new Rect(0, 0, 0, 0);
    private Rect mProgressRect = new Rect(0, 0, 0, 0);

    public NumberProgressView(Context context) {
        super(context);
        init();
    }

    public NumberProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mFramePaint = new Paint();
        mFramePaint.setColor(Color.GRAY);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(Color.BLUE);
        mProgressPaint.setTextSize(12);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 200;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 200;
        }
        setMeasuredDimension(mWidth, mHeight);
        mTextLength = mProgressPaint.measureText(progress + "%");
        mRectMaxLength = (int) (mWidth - mTextLength);
        mFrameRect.set(0, -(mHeight / 2 - RECT_HEIGHT), mRectMaxLength, -(mHeight / 2 - RECT_HEIGHT));
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mFrameRect, mFramePaint);
        canvas.drawRect(mProgressRect, mProgressPaint);
        canvas.drawText(progress + "%", mRectMaxLength + mTextLength / 2, mHeight / 2, mProgressPaint);
    }

    public void setProgressValue(float currentProgress, float maxProgress) {

    }
}
