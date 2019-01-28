package com.victorxu.muses;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.victorxu.muses.db.entity.DaoMaster;
import com.victorxu.muses.db.entity.DaoSession;

import me.yokeyword.fragmentation.Fragmentation;

public class Muses extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFragmentation();
        setupDatabase();
    }

    /**
     * 配置 Fragmentation
     */
    private void setupFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.NONE)
                .debug(true)
                .handleException((Exception e)->{})
                .install();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库muses.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "muses.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
