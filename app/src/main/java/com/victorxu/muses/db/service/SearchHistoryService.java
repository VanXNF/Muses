package com.victorxu.muses.db.service;

import android.util.Log;

import com.victorxu.muses.Muses;
import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.db.entity.HistoryKeyDao;

import java.util.Date;
import java.util.List;

public class SearchHistoryService {

    private static final String TAG = "SearchHistoryService";

    public static void addHistoryKey(HistoryKey historyKey) {
        Muses.getDaoInstant().getHistoryKeyDao().insert(historyKey);
    }

    public static void addHistoryKeyByName(String name) {
        HistoryKey historyKey = new HistoryKey();
        historyKey.setName(name);
        historyKey.setUpdateTime(new Date());
        addHistoryKey(historyKey);
    }

    public static void deleteHistoryKey(HistoryKey historyKey) {
        Muses.getDaoInstant().getHistoryKeyDao().delete(historyKey);
    }

    public static void deleteHistoryKeyById(long id) {
        Muses.getDaoInstant().getHistoryKeyDao().deleteByKey(id);
    }

    public static void deleteHistoryKeyByName(String name) {
        HistoryKey historyKey = getHistoryKeyByName(name);
        if (historyKey != null) {
            deleteHistoryKey(historyKey);
        } else {
            Log.w(TAG, "deleteHistoryKeyByName: ERROR");
        }

    }

    public static void deleteAllHistoryKeys() {
        Muses.getDaoInstant().getHistoryKeyDao().deleteAll();
    }

    /**
     * 更新数据
     */
    public static void updateHistoryKeyById(long id) {
        HistoryKey key = getHistoryKeyById(id);
        key.setUpdateTime(new Date());
        Muses.getDaoInstant().getHistoryKeyDao().update(key);
    }

    public static void updateHistoryKeyByName(String name) {
        HistoryKey key = getHistoryKeyByName(name);
        if (key != null) {
            key.setUpdateTime(new Date());
            Muses.getDaoInstant().getHistoryKeyDao().update(key);
        } else {
            Log.e(TAG, "updateHistoryKeyByName: ERROR");
        }

    }

    public static HistoryKey getHistoryKeyByName(String name) {
        List<HistoryKey> list = Muses.getDaoInstant().getHistoryKeyDao().queryBuilder()
                .where(HistoryKeyDao.Properties.Name.eq(name))
                .list();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static HistoryKey getHistoryKeyById(long id) {
        return Muses.getDaoInstant().getHistoryKeyDao().load(id);
    }

    /**
     * 查询全部数据
     * 按修改时间降序排列
     */
    public static List<HistoryKey> getAllHistoryKeys() {
        return Muses.getDaoInstant().getHistoryKeyDao()
                .queryBuilder()
                .orderDesc(HistoryKeyDao.Properties.UpdateTime)
                .limit(30)
                .list();
    }
}
