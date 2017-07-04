package com.liang.phonecontactlist;

import android.app.Application;
import android.content.Context;

/**
 * Created by Liang on 2017/3/3 0003.
 */

public class MyApplication extends Application {

    private static Context mContent;

    public static Context getmContent() {
        return mContent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContent=getApplicationContext();
    }
}
