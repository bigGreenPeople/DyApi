package com.shark.dyapi.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shark.hook.ServiceManagerWraper;
import com.ss.android.common.applog.GlobalContext;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        //hook pms
        ServiceManagerWraper.hookPMS(this);
        super.onCreate();
        Log.i("SharkChilli", "setContext!!!");
        GlobalContext.setContext(getApplicationContext());
    }
}
