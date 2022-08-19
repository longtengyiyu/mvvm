package com.tangtang.mvvm.db;

import android.content.Context;

import com.tangtang.mvvm.app.Constants;

import java.io.File;

public class DaoHelper {
    private volatile static DaoHelper singleton = null;

//    private volatile DaoSession mDaoSession;
    private volatile UpdateDaoHelper sHelper;

    private DaoHelper(Context context) {
        checkDbDirectories();
//        sHelper = new UpdateDaoHelper(context.getApplicationContext(), Constants.APP_DB_PATH);
//        Database db = sHelper.getWritableDb();
//        mDaoSession = new DaoMaster(db).newSession();
    }

    public UpdateDaoHelper getsHelper() {
        return sHelper;
    }

    /**
     * 创建数据库目录
     */
    public void checkDbDirectories() {
        isFolderExists(Constants.APP_DB_PATH);
    }

    /**
     * 检查目录是否存在，如果不存在则创建目录
     *
     * @param folder 目录路径
     * @return true: 目录文件存在 false: 目录不存在
     */
    public boolean isFolderExists(String folder) {
        File file = new File(folder);
        return file.exists() || file.mkdirs();
    }

    /**
     * 创建一个接口访问服务单例
     *
     * @param context 上下文
     * @return ElectShelfDaoHelper
     */
    public static DaoHelper create(Context context) {
        if (singleton == null) {
            synchronized (DaoHelper.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    /**
     * 获取接口访问服务 在调用此接口时需要先调用{@link #create }接口初始化
     *
     * @return ElectShelfDaoHelper
     */
    public static DaoHelper instance() {
        if (singleton == null) {
            throw new IllegalArgumentException("VipDaoHelper --> 此方法必须在 create 方法之后调用!!!!");
        }
        return singleton;
    }

    public static class Builder {
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        public DaoHelper build() {
            return new DaoHelper(mContext);
        }
    }

//    public DaoSession getDaoSession() {
//        return mDaoSession;
//    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
//        if (sHelper != null) {
//            sHelper.close();
//            sHelper = null;
//        }
    }

    public void closeDaoSession() {
//        if (mDaoSession != null) {
//            mDaoSession.clear();
//            mDaoSession = null;
//        }
    }
}
