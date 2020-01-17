package com.zey.news.application;

import com.zey.base.BaseApplication;
import com.zey.news.BuildConfig;

public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setDebug(BuildConfig.DEBUG);
    }
}
