package com.victorxu.muses.search.model;

import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.db.service.SearchHistoryService;
import com.victorxu.muses.search.contract.SearchContract;
import com.victorxu.muses.util.HttpUtil;

import java.util.List;

import okhttp3.Callback;

public class SearchModel implements SearchContract.Model {

    private final String HOT_KEY_API = "api/hotkey/";

    @Override
    public void getHotKeyData(Callback callback) {
        HttpUtil.getRequest(HOT_KEY_API, callback);
    }

    @Override
    public List<HistoryKey> getHistoryKeyData() {
        return SearchHistoryService.queryAll(0);
    }

    @Override
    public void addHistoryKeyData(String key, String value) {

    }

    @Override
    public void removeHistoryKeyData(String key) {

    }

    @Override
    public void clearAllHistoryKeyData() {

    }
}
