package com.tangtang.mvvm.app;

import androidx.multidex.MultiDexApplication;

import com.tangtang.mvvm.BuildConfig;
import com.tangtang.mvvm.api.API;
import com.tangtang.mvvm.api.ApiModule;
import com.tangtang.mvvm.api.ApiStore;
import com.tangtang.mvvm.db.DaoHelper;
import com.tangtang.mvvm.view.MediaPlayerUtils;

/**
 * Author:
 * Version    V1.0
 * Date:      2021/7/30
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2021/7/30                  1.0                    1.0
 * Why & What is modified:
 */
public class AppApplication extends MultiDexApplication {
    private static AppApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initApi();
        MediaPlayerUtils.init();
        initDao();
    }

    private void initApi(){
        ApiModule module = new ApiModule(BuildConfig.API_HOST);
        API.getInstance().create(module, ApiStore.class);
    }

    private void initDao(){
        DaoHelper.create(this);
    }

    public static AppApplication getInstance(){
        return application;
    }
}
