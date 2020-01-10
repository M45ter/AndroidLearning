package com.zey.customview.viewspecial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 继承特定View，可以对控件功能进行扩展或修改
 * 一般不需要重写方法，除非是功能需要
 */
@SuppressLint("AppCompatCustomView")
public class SpecialView extends TextView {

    public SpecialView(Context context) {
        this(context, null);
    }

    public SpecialView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecialView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTextColor(Color.RED);
    }
}
