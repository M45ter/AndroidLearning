package com.zey.viewpager;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class LoopViewPager extends ViewPager {

    protected boolean isInfinite = true;
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
            if (!isInfinite && getAdapter().getCount() - 1 == currentPagePosition) {
                currentPagePosition = 0;
            } else {
                currentPagePosition++;
            }
            setCurrentItem(currentPagePosition, true);
        }
    };

    public LoopViewPager(@NonNull Context context) {
        this(context, null);
    }

    public LoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
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
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE) {
                    //Below are code to achieve infinite scroll.
                    //We silently and immediately flip the item to the first / last.
                    if (isInfinite) {
                        if (getAdapter() == null) return;
                        int itemCount = getAdapter().getCount();
                        if (itemCount < 2) {
                            return;
                        }
                        int index = getCurrentItem();
                        if (index == 0) {
                            setCurrentItem(itemCount - 2, false); //Real last item
                        } else if (index == itemCount - 1) {
                            setCurrentItem(1, false); //Real first item
                        }
                    }
                }
            }
        });
        if (isInfinite) setCurrentItem(1, false);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (isInfinite) setCurrentItem(1, false);
    }

    public void resumeAutoScroll() {
        isAutoScrollResumed = true;
        autoScrollHandler.postDelayed(autoScrollRunnable, interval);
    }

    public void pauseAutoScroll() {
        isAutoScrollResumed = false;
        autoScrollHandler.removeCallbacks(autoScrollRunnable);
    }

    public void reset() {
        if (isInfinite) {
            setCurrentItem(1, false);
            currentPagePosition = 1;
        } else {
            setCurrentItem(0, false);
            currentPagePosition = 0;
        }
    }

    public void setInterval(int interval) {
        this.interval = interval;
        resetAutoScroll();
    }

    private void resetAutoScroll() {
        pauseAutoScroll();
        resumeAutoScroll();
    }
}
