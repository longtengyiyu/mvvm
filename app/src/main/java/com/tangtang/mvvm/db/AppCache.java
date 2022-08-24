package com.tangtang.mvvm.db;

import com.tangtang.mvvm.app.Constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AppCache {
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    public static boolean isNetConnected(){
        Object obj = cache.get(Constants.NET_WORK_STATUS);
        if (obj == null){
            return true;
        }
        return (boolean) cache.get(Constants.NET_WORK_STATUS);
    }

    public static void setNetConnected(boolean isConnected){
        cache.put(Constants.NET_WORK_STATUS, isConnected);
    }
}
