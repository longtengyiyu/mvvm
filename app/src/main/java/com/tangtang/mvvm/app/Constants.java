package com.tangtang.mvvm.app;

public class Constants {

    public static String APP_ROOT_PATH = AppApplication.getInstance().getFilesDir().getAbsolutePath();

    public final static String APP_DB_PATH = APP_ROOT_PATH + "/cache/";

    public final static String APP_DB = APP_DB_PATH + "chat-db";

    //app cache
    public static final String NET_WORK_STATUS = "network";
}
