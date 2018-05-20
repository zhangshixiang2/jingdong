package com.example.xiangmulianxi.app;

import android.app.Application;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        ZXingLibrary.initDisplayOpinion(this);

    }
}
