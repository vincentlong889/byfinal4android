package com.byfinal.demo.byfinal4android;

import android.app.Application;

/**
 * @author wangqin.lwq
 * @date 2016-03-03
 */
public class BXLApplication extends Application {
    private static final String TAG = BXLApplication.class.getSimpleName();

    public boolean isLoaded = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
