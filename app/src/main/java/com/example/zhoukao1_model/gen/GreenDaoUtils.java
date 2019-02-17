package com.example.zhoukao1_model.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GreenDaoUtils {

    public static  void initGreenDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "shopdata.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private static DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
