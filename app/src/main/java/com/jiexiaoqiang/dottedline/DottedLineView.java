package com.jiexiaoqiang.dottedline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jiexiaoqiang on 2017/3/22 0022.
 */

public class DottedLineView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Paint mFramePaint;
    private Path mFirstPath;
    private Path mSecondPath;
    private boolean check;
    private boolean start;

    public DottedLineView(Context context) {
        super(context);
        init();
    }

    public DottedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mFramePaint = new Paint();
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setColor(Color.WHITE);
        mFramePaint.setStrokeWidth(8);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(8);
        mFirstPath = new Path();
        mSecondPath = new Path();
        PathEffect effects = new DashPathEffect(new float[]{8, 8}, 1);
        mPaint.setPathEffect(effects);

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
            mWidth = 24;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 24;
        }
        setMeasuredDimension(mWidth, mHeight);
        mFirstPath.moveTo(0, 0);
        mFirstPath.lineTo(mWidth, 0);
        mFirstPath.lineTo(mWidth, mHeight);
        mFirstPath.lineTo(0, mHeight);
        mFirstPath.close();
        mSecondPath.moveTo(10, 0);
        mSecondPath.lineTo(mWidth, 0);
        mSecondPath.lineTo(mWidth, mHeight);
        mSecondPath.lineTo(0, mHeight);
        mSecondPath.lineTo(0, 0);
        mFirstPath.close();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (start) {
            canvas.drawPath(mFirstPath,mFramePaint);
            if (check) {
                canvas.drawPath(mFirstPath, mPaint);
            } else {
                canvas.drawPath(mSecondPath, mPaint);
            }
            check = !check;
            postInvalidateDelayed(500);
        }
    }

    public void start() {
        start = true;
        postInvalidateDelayed(500);
    }

    public void stop() {
        start = false;
        postInvalidate();
    }
}
