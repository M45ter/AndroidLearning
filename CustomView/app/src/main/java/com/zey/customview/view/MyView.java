package com.zey.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.zey.customview.R;

/**
 * 继承View
 * 一般需要重写onMeasure、onDraw
 * 注意：
 * 1.wrap_content需要自己实现
 * 2.padding需要自己实现
 * (margin不需要实现，因为margin是由父容器实现的)
 */
public class MyView extends View {

    private int mColor;

    private Paint mPaint;

    /**
     * 一般new的时候使用
     * @param context
     */
    public MyView(Context context) {
        this(context, null);
    }

    /**
     * xml布局自动调用
     * @param context
     * @param attrs
     */
    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 有默认defStyleAttr时，由构造方法二调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载自定义属性集合
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        // 解析集合中的属性
        // 第二个参数是默认值
        mColor = a.getColor(R.styleable.MyView_color,Color.BLUE);

        // 解析后释放资源
        a.recycle();

        init();
    }

    /**
     * 一般不用写
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void init() {
        mPaint = new Paint();
        // 设置画笔颜色为蓝色
        mPaint.setColor(mColor);
        // 设置画笔宽度为10px
        mPaint.setStrokeWidth(5f);
        //设置画笔模式为填充
        mPaint.setStyle(Paint.Style.FILL);
        //打开抗锯齿
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = 200;
        int mHeight = 200;

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();


        // 获取控件的高度和宽度
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        // 设置圆的半径 = 宽,高最小值的2分之1
        int r = Math.min(width, height) / 2;

        // 画出圆
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, r, mPaint);
    }
}
