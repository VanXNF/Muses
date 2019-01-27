package com.victorxu.muses.db.service;

import com.victorxu.muses.Muses;
import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.db.entity.HistoryKeyDao;

import java.util.Date;
import java.util.List;

public class SearchHistoryService {

    /**
     * 插入历史搜索
     * @param historyKey
     */
    public static void addHistoryKey(HistoryKey historyKey) {
        Muses.getDaoInstant().getHistoryKeyDao().insert(historyKey);
    }

    public static void addHistoryKeyByName(String name, long userId) {
        HistoryKey historyKey = new HistoryKey();
        historyKey.setName(name);
        historyKey.setUpdateTime(new Date());
        historyKey.setUserId(userId);
        addHistoryKey(historyKey);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteHistoryKeyById(long id) {
        Muses.getDaoInstant().getHistoryKeyDao().deleteByKey(id);
    }

    public static void deleteHistoryKeyByName(String name, long userId) {
        HistoryKey historyKey = getHistoryKeyByName(name, userId);
        Muses.getDaoInstant().getHistoryKeyDao().delete(historyKey);
    }

    /**
     * 更新数据
     */
    public static void updateHistoryKeyById(long id) {
        HistoryKey key = getHistoryKeyById(id);
        key.setUpdateTime(new Date());
        Muses.getDaoInstant().getHistoryKeyDao().update(key);
    }

    public static void updateHistoryKeyByName(String name, long userId) {
        HistoryKey key = getHistoryKeyByName(name, userId);
        key.setUpdateTime(new Date());
        Muses.getDaoInstant().getHistoryKeyDao().update(key);
    }

    /**
     * 按历史记录值 查询数据
     */
    public static HistoryKey getHistoryKeyByName(String name, long userId) {
        return Muses.getDaoInstant().getHistoryKeyDao().queryBuilder()
                .where(HistoryKeyDao.Properties.Name.eq(name), HistoryKeyDao.Properties.UserId.eq(userId))
                .list().get(0);
    }

    public static HistoryKey getHistoryKeyById(long id) {
        return Muses.getDaoInstant().getHistoryKeyDao().load(id);
    }

    /**
     * 查询全部数据
     * 按优先级降序排列
     */
    public static List<HistoryKey> queryAll(long userId) {
        return Muses.getDaoInstant().getHistoryKeyDao()
                .queryBuilder().where(HistoryKeyDao.Properties.UserId.eq(userId))
                .orderDesc(HistoryKeyDao.Properties.UpdateTime)
                .limit(30)
                .list();
    }
}
