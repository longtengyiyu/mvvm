package com.tangtang.mvvm.utils;

import android.util.Log;

import com.tangtang.mvvm.BuildConfig;

public class LogUtils {

    public static void d(String tag, String msg){
        if (BuildConfig.DEBUG){
            Log.d(tag, msg);
        }
    }
}
