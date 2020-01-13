package com.zey.customview.viewgroupspecial;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zey.customview.R;

/**
 * 继承特定的ViewGroup
 * 1.可以扩展这个特定ViewGroup
 * 2.组合控件（大多数是这种情况），方便复用
 */
public class SpecialViewGroup extends RelativeLayout {

    private TextView mTextViewLeft;

    private TextView mTextViewRight;

    private String mLeftString;

    private String mRightString;

    public SpecialViewGroup(@NonNull Context context) {
        this(context, null);
    }

    public SpecialViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecialViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.special_view_group, this, true);
        mTextViewLeft = (TextView) findViewById(R.id.tv_left);
        mTextViewRight = (TextView) findViewById(R.id.tv_right);
        // 加载自定义属性集合
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpecialViewGroup);

        // 解析集合中的属性
        mLeftString = a.getString(R.styleable.SpecialViewGroup_left);
        mRightString = a.getString(R.styleable.SpecialViewGroup_right);

        // 解析后释放资源
        a.recycle();

        mTextViewLeft.setText(mLeftString);
        mTextViewRight.setText(mRightString);
    }
}
