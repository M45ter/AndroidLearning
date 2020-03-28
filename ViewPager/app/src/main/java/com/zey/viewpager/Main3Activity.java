package com.zey.viewpager;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.zey.viewpager.indicator.CustomNavigator;
import com.zey.viewpager.indicator.ScaleCircleNavigator;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

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

    private MagicIndicator indicator;

    private void init() {
        autoScrollViewPager = findViewById(R.id.view_pager);
        getLifecycle().addObserver(autoScrollViewPager);
        final List<View> list = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        list.add(inflater.inflate(R.layout.pager_text1, null));
        list.add(inflater.inflate(R.layout.pager_text2, null));
        list.add(inflater.inflate(R.layout.pager_text3, null));
        list.add(inflater.inflate(R.layout.pager_text4, null));
        MaxPagerAdapter adapter = new MaxPagerAdapter(list);
        autoScrollViewPager.setAdapter(adapter);
//        autoScrollViewPager.setPageTransformer(false, new CubeOutTransformer());
        int firstPage = Integer.MAX_VALUE / 2 / list.size() * list.size();
        autoScrollViewPager.setCurrentItem(firstPage, false);
//        try {
//            Field field = ViewPager.class.getDeclaredField("mScroller");
//            field.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(this, new LinearInterpolator());
//            scroller.setmDuration(2000);
//            field.set(autoScrollViewPager, scroller);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        indicator = findViewById(R.id.magic_indicator);
//        CircleNavigator circleNavigator = new CircleNavigator(this);
//        circleNavigator.setFollowTouch(true);
//        circleNavigator.setCircleCount(list.size());
//        circleNavigator.setCircleColor(0xFF8DB4FF);
//        indicator.setNavigator(circleNavigator);
//        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
//        scaleCircleNavigator.setCircleCount(list.size());
//        scaleCircleNavigator.setNormalCircleColor(0xFF8DB4FF);
//        scaleCircleNavigator.setSelectedCircleColor(0xFF8DB4FF);
//        scaleCircleNavigator.setMaxRadius(UIUtil.dip2px(this, 4));
//        indicator.setNavigator(scaleCircleNavigator);

        CustomNavigator customNavigator = new CustomNavigator(this);
        customNavigator.setCircleCount(list.size());
        customNavigator.setNormalCircleColor(0xFF8DB4FF);
        customNavigator.setSelectedCircleColor(0xFF8DB4FF);
        indicator.setNavigator(customNavigator);

        autoScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                indicator.onPageScrolled(i % list.size(), v, i1);
            }

            @Override
            public void onPageSelected(int i) {
                indicator.onPageSelected(i % list.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                indicator.onPageScrollStateChanged(i);
            }
        });
    }
}
