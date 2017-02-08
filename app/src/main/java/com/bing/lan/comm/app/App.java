package com.bing.lan.comm.app;

import android.app.Application;

import com.bing.lan.comm.utils.AppUtil;

/**
 * @author 蓝兵
 * @time 2017/1/9  18:26
 */
public class App extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        AppUtil.initGlobal(this, getApplicationContext());

//        ImageUtil.prepare(getApplicationContext());
    }
}
