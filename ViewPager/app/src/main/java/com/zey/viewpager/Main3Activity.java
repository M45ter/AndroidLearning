package com.zey.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
    }

    private AutoScrollViewPager autoScrollViewPager;

    private void init() {
        autoScrollViewPager = findViewById(R.id.view_pager);
        getLifecycle().addObserver(autoScrollViewPager);
        List<View> list = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        list.add(inflater.inflate(R.layout.pager_text1, null));
        list.add(inflater.inflate(R.layout.pager_text2, null));
        list.add(inflater.inflate(R.layout.pager_text3, null));
        list.add(inflater.inflate(R.layout.pager_text4, null));
        MaxPagerAdapter adapter = new MaxPagerAdapter(list);
        autoScrollViewPager.setAdapter(adapter);
        autoScrollViewPager.setPageTransformer(false, new CubeOutTransformer());
        int firstPage = Integer.MAX_VALUE / 2 / list.size() * list.size();
        autoScrollViewPager.setCurrentItem(firstPage, false);
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this, new LinearInterpolator());
            scroller.setmDuration(2000);
            field.set(autoScrollViewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
