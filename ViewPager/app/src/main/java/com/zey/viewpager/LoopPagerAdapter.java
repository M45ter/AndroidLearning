package com.zey.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoopPagerAdapter extends PagerAdapter {

    private static final String TAG = "LoopPagerAdapter";

    private List<View> mViewList;

    private SparseIntArray mAddedSpecialViews = new SparseIntArray();

    public LoopPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        int count = mViewList.size();
        if (count >= 2) {
            count += 2;
        }
        return count;
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
        if (mViewList.size() >= 2 && (realPosition == 0 || realPosition == mViewList.size() - 1)) {
            if (mAddedSpecialViews.indexOfValue(realPosition) < 0) {
                container.addView(view);
            }
            mAddedSpecialViews.put(position, realPosition);
        } else {
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem: " + position);
        int realPosition = getRealPosition(position);
        View view = mViewList.get(realPosition);
        if (mViewList.size() >= 2 && (realPosition == 0 || realPosition == mViewList.size() - 1)) {
            int count = 0;
            for (int i = 0; i < mAddedSpecialViews.size(); i++) {
                if (mAddedSpecialViews.valueAt(i) == realPosition) {
                    count++;
                }
            }
            if (count == 1) {
                container.removeView(view);
            }
            mAddedSpecialViews.delete(position);
        } else {
            container.removeView(view);
        }
    }

    private int getRealPosition(int position) {
        if (mViewList.size() >= 2) {
            if (position == 0) {
                position = mViewList.size() - 1;
            } else if (position == getCount() - 1) {
                position = 0;
            } else {
                position = position - 1;
            }
        }
        return position;
    }
}
