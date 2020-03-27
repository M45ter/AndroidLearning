package com.zey.viewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private LoopViewPager loopViewPager;

    private void init() {
        loopViewPager = findViewById(R.id.view_pager);
        List<View> list = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        list.add(inflater.inflate(R.layout.pager_text1, null));
        list.add(inflater.inflate(R.layout.pager_text2, null));
        list.add(inflater.inflate(R.layout.pager_text3, null));
        list.add(inflater.inflate(R.layout.pager_text4, null));
        LoopPagerAdapter adapter = new LoopPagerAdapter(list);
        loopViewPager.setAdapter(adapter);
        loopViewPager.setPageTransformer(false, new CubeOutTransformer());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loopViewPager.resumeAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loopViewPager.pauseAutoScroll();
    }
}
