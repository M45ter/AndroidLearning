package com.zey.viewpager;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class AutoScrollViewPager extends ViewPager implements LifecycleObserver {

    private static final String TAG = "AutoScrollViewPager";

    protected boolean isAutoScroll = true;

    private int interval = 5000;
    private int previousPosition = 0;
    private int currentPagePosition = 0;
    private boolean isAutoScrollResumed = false;

    private Handler autoScrollHandler = new Handler();
    private Runnable autoScrollRunnable = new Runnable() {
        @Override
        public void run() {
            if (getAdapter() == null || !isAutoScroll || getAdapter().getCount() < 2) return;
            setCurrentItem(++currentPagePosition, true);
        }
    };

    public AutoScrollViewPager(@NonNull Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                previousPosition = currentPagePosition;
                currentPagePosition = position;
                if (isAutoScrollResumed) {
                    autoScrollHandler.removeCallbacks(autoScrollRunnable);
                    autoScrollHandler.postDelayed(autoScrollRunnable, interval);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resumeAutoScroll() {
        Log.d(TAG, "resumeAutoScroll: ");
        isAutoScrollResumed = true;
        autoScrollHandler.postDelayed(autoScrollRunnable, interval);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pauseAutoScroll() {
        Log.d(TAG, "pauseAutoScroll: ");
        isAutoScrollResumed = false;
        autoScrollHandler.removeCallbacks(autoScrollRunnable);
    }

    public void setInterval(int interval) {
        this.interval = interval;
        resetAutoScroll();
    }

    private void resetAutoScroll() {
        pauseAutoScroll();
        resumeAutoScroll();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pauseAutoScroll();
                break;
            case MotionEvent.ACTION_UP:
                resumeAutoScroll();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
