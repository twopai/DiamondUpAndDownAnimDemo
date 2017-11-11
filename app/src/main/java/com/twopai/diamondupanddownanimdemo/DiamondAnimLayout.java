package com.twopai.diamondupanddownanimdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 作者：twopai on 2017/11/2.
 * 邮箱：twopai@hotmail.com
 */

public class DiamondAnimLayout extends RelativeLayout {
    private ViewGroup diamond1,diamond2,diamond3,diamond4,diamond5,diamond6;//砖石1、2、3、4、5、6
    private int mDiamondViewWidth;//砖石的宽度
    private int mScreenWidth;//屏幕的宽度
    private float mDiamondBetweenPadding=0;//砖石间的间隔
    private AnimatorSet animatorSet;//动画集合
    private ValueAnimator valueAnimator135;//属性动画类135
    private ValueAnimator valueAnimator246;//想、属性动画类246
    private View diamond1Shadow,diamond2Shadow,diamond3Shadow,diamond4Shadow,diamond5Shadow,diamond6Shadow;
    private boolean isStop=false;

    public DiamondAnimLayout(Context context) {
        this(context,null);
    }

    public DiamondAnimLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiamondAnimLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DiamondAnimLayout);
        mDiamondBetweenPadding =array.getDimension(R.styleable.
                DiamondAnimLayout_diamond_between_padding, mDiamondBetweenPadding);
        array.recycle();
        mScreenWidth = getScreenWidth(context);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount != 6) {
            throw new RuntimeException("砖石数量应该为6个");
        }
        diamond1 = (ViewGroup) getChildAt(0);
        diamond2 = (ViewGroup) getChildAt(1);
        diamond3 = (ViewGroup) getChildAt(2);
        diamond4 = (ViewGroup) getChildAt(3);
        diamond5 = (ViewGroup) getChildAt(4);
        diamond6 = (ViewGroup) getChildAt(5);
        //默认两砖石间距为30dp
        mDiamondViewWidth = diamond1.getMeasuredWidth();
        diamond1.setX(mScreenWidth/2-mDiamondBetweenPadding*3-mDiamondViewWidth*5/2);
        diamond2.setX(mScreenWidth/2-mDiamondBetweenPadding*2-mDiamondViewWidth*3/2);
        diamond3.setX(mScreenWidth/2-mDiamondBetweenPadding-mDiamondViewWidth/2);
        diamond4.setX(mScreenWidth/2+mDiamondViewWidth/2);
        diamond5.setX(mScreenWidth/2+mDiamondBetweenPadding+mDiamondViewWidth*3/2);
        diamond6.setX(mScreenWidth/2+mDiamondBetweenPadding*2+mDiamondViewWidth*5/2);
        //获取对应的阴影
        int childCountShadow = diamond1.getChildCount();
        if (childCountShadow != 2) {
            throw new RuntimeException("子view数量应该为2个");
        }
        diamond1Shadow = diamond1. getChildAt(1);
        diamond2Shadow = diamond2. getChildAt(1);
        diamond3Shadow = diamond3. getChildAt(1);
        diamond4Shadow = diamond4. getChildAt(1);
        diamond5Shadow = diamond5. getChildAt(1);
        diamond6Shadow = diamond6. getChildAt(1);
    }
    public void stopAnim(Context c){
        if (animatorSet != null) {
            isStop =true;
            valueAnimator135.end();
            valueAnimator246.end();
            animatorSet.end();
        }else {
            Toast.makeText(c, "你还没开始动画", Toast.LENGTH_SHORT).show();
        }
    };
    public void startAnim(){
        isStop =false;
        animatorSet = new AnimatorSet();
        valueAnimator135 = ValueAnimator.ofFloat(10,0,10);
        valueAnimator135.setDuration(2500);
        valueAnimator135.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                diamond1.setY(dpToPx(animatedValue));
                diamond3.setY(dpToPx(animatedValue));
                diamond5.setY(dpToPx(animatedValue));
                diamond2.setY(dpToPx(10-animatedValue));
                diamond4.setY(dpToPx(10-animatedValue));
                diamond6.setY(dpToPx(10-animatedValue));
                float f135s = 1.0f * animatedValue / 10*0.5f;
                float f246s=0.5f-f135s;
                diamond1Shadow.setScaleX(f135s);
                diamond1Shadow.setScaleY(f135s);
                diamond3Shadow.setScaleX(f135s);
                diamond3Shadow.setScaleY(f135s);
                diamond5Shadow.setScaleX(f135s);
                diamond5Shadow.setScaleY(f135s);
                diamond2Shadow.setScaleX(f246s);
                diamond2Shadow.setScaleY(f246s);
                diamond4Shadow.setScaleX(f246s);
                diamond4Shadow.setScaleY(f246s);
                diamond6Shadow.setScaleX(f246s);
                diamond6Shadow.setScaleY(f246s);
            }
        });
        valueAnimator246 = ValueAnimator.ofFloat(10,0,10);
        valueAnimator246.setDuration(2500);
        animatorSet.play(valueAnimator135);
        animatorSet.start();
        valueAnimator135.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }
            @Override
            public void onAnimationEnd(Animator animator) {
                if (isStop == false) {
                    valueAnimator246.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        valueAnimator246.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                diamond1.setY(dpToPx(animatedValue));
                diamond3.setY(dpToPx(animatedValue));
                diamond5.setY(dpToPx(animatedValue));
                diamond2.setY(dpToPx(10-animatedValue));
                diamond4.setY(dpToPx(10-animatedValue));
                diamond6.setY(dpToPx(10-animatedValue));

                float f135s = 1.0f * animatedValue / 10*0.5f;
                float f246s=0.5f-f135s;
                diamond1Shadow.setScaleX(f135s);
                diamond1Shadow.setScaleY(f135s);
                diamond3Shadow.setScaleX(f135s);
                diamond3Shadow.setScaleY(f135s);
                diamond5Shadow.setScaleX(f135s);
                diamond5Shadow.setScaleY(f135s);
                diamond2Shadow.setScaleX(f246s);
                diamond2Shadow.setScaleY(f246s);
                diamond4Shadow.setScaleX(f246s);
                diamond4Shadow.setScaleY(f246s);
                diamond6Shadow.setScaleX(f246s);
                diamond6Shadow.setScaleY(f246s);
            }
        });
        valueAnimator246.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (isStop == false) {
                    valueAnimator135.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
    public int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    public float dpToPx(float dp){
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    };
}
