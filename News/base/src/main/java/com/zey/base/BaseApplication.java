package com.zey.base;

import android.app.Application;

public class BaseApplication extends Application {
    /**
     * OOM won't happen.
     */
    public static Application sApplication;
    public static  boolean sDebug;

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
