package com.zey.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 继承ViewGroup
 * 一般需要重写onMeasure、onLayout
 * 1.wrap_content需要自己实现
 * 2.padding需要自己实现
 * 3.子View的margin需要实现
 */
public class MyViewGroup extends ViewGroup {

    private static final String TAG = "MyViewGroup";

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量自身
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取mode、size
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //子view测量
        final int childCount = getChildCount();
        //和measureChildren效果没区别？
//        for (int i = 0; i < childCount; i++) {
//            final View child = getChildAt(i);
//            LayoutParams lp = (LayoutParams) child.getLayoutParams();
//            final int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
//                    getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width);
//
//            int heightPadding = lp.topMargin + lp.bottomMargin;
//            if (i == 0) {
//                heightPadding += getPaddingTop();
//            }
//            if (i == childCount - 1) {
//                heightPadding += getPaddingBottom();
//            }
//            final int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
//                    heightPadding, lp.height);
//
//            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//        }
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //根据子View尺寸，再测量自己尺寸
        int width = 0;
        int height = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    final View child = getChildAt(i);
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    width = Math.max(width, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                }
                break;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    final View child = getChildAt(i);
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    height += (child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                }
                break;
        }
        //保存自身尺寸
        setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), height + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        int top = 0;
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();

            Log.i(TAG, "onLayout: child " + i + " height: " + height + " width: " + width + " leftMargin: " + lp.leftMargin);

            int left = getPaddingLeft() + lp.leftMargin;
            if (i == 0) {
                top += getPaddingTop();
            }
            top += lp.topMargin;
            int right = left + width;
            int bottom = top + height;
            child.layout(left, top, right, bottom);
            top = bottom + lp.bottomMargin;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }


        public LayoutParams(int width, int height) {
            super(width, height);
        }


        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }


        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }
}
