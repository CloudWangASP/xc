package com.cloud.xc.app;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * Created by cloud on 2017/11/20.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
