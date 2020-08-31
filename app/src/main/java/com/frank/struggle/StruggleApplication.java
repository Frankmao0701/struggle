package com.frank.struggle;

import android.app.Application;
import android.content.Context;

/**
 * @author maowenqiang
 * @desc
 */
public class StruggleApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }


    public static Context getContext() {
        return context;
    }
}
