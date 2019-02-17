package com.example.zhoukao1_model;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhoukao1_model.gen.DaoMaster;
import com.example.zhoukao1_model.gen.DaoSession;
import com.example.zhoukao1_model.gen.GreenDaoUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        GreenDaoUtils.initGreenDao(this);

    }
}
