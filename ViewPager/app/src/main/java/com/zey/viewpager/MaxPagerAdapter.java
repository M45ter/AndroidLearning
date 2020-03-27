package com.zey.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MaxPagerAdapter extends PagerAdapter {

    private static final String TAG = "MaxPagerAdapter";

    private List<View> mViewList;

    public MaxPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem: " + position);
        int realPosition = getRealPosition(position);
        View view = mViewList.get(realPosition);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem: " + position);
        int realPosition = getRealPosition(position);
        View view = mViewList.get(realPosition);
        container.removeView(view);
    }

    private int getRealPosition(int position) {
        return position % mViewList.size();
    }
}
