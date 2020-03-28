package com.zey.viewpager.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import net.lucode.hackware.magicindicator.NavigatorHelper;
import net.lucode.hackware.magicindicator.abs.IPagerNavigator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;

public class CustomNavigator extends View implements IPagerNavigator, NavigatorHelper.OnNavigatorScrollListener {

    private int mRadius;
    private int mRectWidth;
    private int mNormalCircleColor = Color.LTGRAY;
    private int mSelectedCircleColor = Color.GRAY;
    private int mCircleSpacing;
    private int mCircleCount;

    private int mSelectedPosition = 0;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private NavigatorHelper mNavigatorHelper = new NavigatorHelper();

    public CustomNavigator(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mRadius = UIUtil.dip2px(context, 3);
        mRectWidth = UIUtil.dip2px(context, 16);
        mCircleSpacing = UIUtil.dip2px(context, 5);
        mNavigatorHelper.setNavigatorScrollListener(this);
        mNavigatorHelper.setSkimOver(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = width;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                if (mCircleCount <= 0) {
                    result = getPaddingLeft() + getPaddingRight();
                } else {
                    result = (mCircleCount - 1) * mRadius * 2 + mRectWidth + (mCircleCount - 1) * mCircleSpacing + getPaddingLeft() + getPaddingRight();
                }
                break;
            default:
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = height;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = mRadius * 2 + getPaddingTop() + getPaddingBottom();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = 0;
        for (int i = 0; i < mCircleCount; i++) {
            int spacing = (i == 0 ? 0 : mCircleSpacing);
            if (i == mSelectedPosition) {
                float left = x + spacing;
                float top = getHeight() / 2 - mRadius;
                mPaint.setColor(mSelectedCircleColor);
                canvas.drawRoundRect(left, top, left + mRectWidth, top + 2 * mRadius, mRadius, mRadius, mPaint);
                x = left + mRectWidth;
            } else {
                float cx = x + spacing + mRadius;
                mPaint.setColor(mNormalCircleColor);
                canvas.drawCircle(cx, getHeight() / 2, mRadius, mPaint);
                x = cx + mRadius;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mNavigatorHelper.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        mSelectedPosition = position;
        mNavigatorHelper.onPageSelected(position);
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mNavigatorHelper.onPageScrollStateChanged(state);
    }

    @Override
    public void onAttachToMagicIndicator() {

    }

    @Override
    public void onDetachFromMagicIndicator() {

    }

    @Override
    public void notifyDataSetChanged() {
        requestLayout();
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

    }

    @Override
    public void onSelected(int index, int totalCount) {

    }

    @Override
    public void onDeselected(int index, int totalCount) {

    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        invalidate();
    }

    public void setRectWidth(int rectWidth) {
        this.mRectWidth = rectWidth;
        invalidate();
    }

    public void setNormalCircleColor(int normalCircleColor) {
        mNormalCircleColor = normalCircleColor;
        invalidate();
    }

    public void setSelectedCircleColor(int selectedCircleColor) {
        mSelectedCircleColor = selectedCircleColor;
        invalidate();
    }

    public void setCircleSpacing(int circleSpacing) {
        mCircleSpacing = circleSpacing;
        invalidate();
    }

    public void setCircleCount(int count) {
        mCircleCount = count;  // 此处不调用invalidate，让外部调用notifyDataSetChanged
        mNavigatorHelper.setTotalCount(mCircleCount);
    }
}
