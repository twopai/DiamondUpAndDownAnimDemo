package com.twopai.diamondupanddownanimdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 作者：twopai on 2017/11/3.
 * 邮箱：twopai@hotmail.com
 */

public class DiamondShadowView extends View{
    private int mShadowColor= Color.GRAY;
    private Paint mDiamondShadowPaint;

    public DiamondShadowView(Context context) {
        this(context,null);
    }

    public DiamondShadowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiamondShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DiamondShadowView);
        mShadowColor =array.getColor(R.styleable.DiamondShadowView_shadow_color, mShadowColor);
        array.recycle();
        mDiamondShadowPaint = new Paint();
        mDiamondShadowPaint.setColor(mShadowColor);
        mDiamondShadowPaint.setStrokeWidth(1);
        mDiamondShadowPaint.setAntiAlias(true);
        mDiamondShadowPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(dpToPx(30),dpToPx(20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(dpToPx(5), dpToPx(0), dpToPx(25), dpToPx(5));//兼容api21以下
        canvas.drawOval(rectF, mDiamondShadowPaint);
    }
    public int dpToPx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    };
}
