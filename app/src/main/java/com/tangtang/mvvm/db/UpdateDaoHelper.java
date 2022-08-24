package com.tangtang.mvvm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tangtang.mvvm.dao.DaoMaster;
import com.tangtang.mvvm.utils.LogUtils;

import org.greenrobot.greendao.database.Database;

public class UpdateDaoHelper extends DaoMaster.DevOpenHelper {
    private static final String TAG = UpdateDaoHelper.class.getSimpleName();

    public UpdateDaoHelper(Context context, String name) {
        super(context, name);
    }

    public UpdateDaoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void clearAllTables() {
        DaoMaster.dropAllTables(getWritableDb(), true);
    }

    public void createAllTables() {
        DaoMaster.createAllTables(getWritableDb(), true);
    }

    private void insertColumn(Database db, String table, String columnName) {
        try {
            db.execSQL("ALTER TABLE " + table + " ADD COLUMN " + columnName + " TEXT;");
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.d(TAG, "已经存在列:" + columnName);
        }
    }

}
