package com.twopai.diamondupanddownanimdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 作者：twopai on 2017/11/2.
 * 邮箱：twopai@hotmail.com
 */

/**
 *   (-4,0)(-2,2)(2,2)(2,0)(0,-3)
 */
public class DiamondView extends View {

    private int mDiamondColor= Color.YELLOW;
    private int mDiamondShadowColor=Color.GRAY;
    private float mDiamondHorizontalSize=dpToPx(2);
    private Paint mDiamondPaint;
    private Paint mDiamondShadowPaint;

    public DiamondView(Context context) {
        this(context,null);
    }

    public DiamondView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiamondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DiamondView);
        mDiamondColor =array.getColor(R.styleable.DiamondView_diamond_color, mDiamondColor);
        mDiamondShadowColor =array.getColor(R.styleable.DiamondView_diamond_shadow_color, mDiamondShadowColor);
        mDiamondHorizontalSize =array.getDimension(R.styleable.DiamondView_diamond_horizontal_size, mDiamondHorizontalSize);
        array.recycle();

        mDiamondPaint = new Paint();
        mDiamondPaint.setColor(mDiamondColor);
        mDiamondPaint.setStrokeWidth(1);
        mDiamondPaint.setAntiAlias(true);
        mDiamondPaint.setStyle(Paint.Style.FILL);

        mDiamondShadowPaint = new Paint();
        mDiamondShadowPaint.setColor(mDiamondShadowColor);
        mDiamondShadowPaint.setStrokeWidth(1);
        mDiamondShadowPaint.setAntiAlias(true);
        mDiamondShadowPaint.setStyle(Paint.Style.FILL);
    }
    public int dpToPx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    };
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //高：13dp 宽：10dp
        setMeasuredDimension(dpToPx(30),dpToPx(28));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path diamondPath = new Path();
        //diamond:(2,4)(3,2)(7,2)(8,4)(5,7)
        //shadow:Oval:(2,11)(8,9)
        diamondPath.moveTo(dpToPx(5),dpToPx(10));
        diamondPath.lineTo(dpToPx(10),dpToPx(5));
        diamondPath.lineTo(dpToPx(20),dpToPx(5));
        diamondPath.lineTo(dpToPx(25),dpToPx(10));
        diamondPath.lineTo(dpToPx(15),dpToPx(23));
        diamondPath.close();
        canvas.drawPath(diamondPath,mDiamondPaint);
    }

}