package com.victorxu.muses.search.model;


import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.db.service.SearchHistoryService;
import com.victorxu.muses.search.contract.SearchContract;
import com.victorxu.muses.util.HttpUtil;

import java.util.List;

import okhttp3.Callback;

@SuppressWarnings("ConstantConditions")
public class SearchModel implements SearchContract.Model {

    @SuppressWarnings("FieldCanBeLocal")
    private final String HOT_KEY_API = "api/hotkey/";

    @Override
    public void getHotKeyData(Callback callback) {
        HttpUtil.getRequest(HOT_KEY_API, callback);
    }

    @Override
    public List<HistoryKey> getHistoryKeyData() {
        return SearchHistoryService.getAllHistoryKeys();
    }

    @Override
    public void addHistoryKeyData(String name) {
        HistoryKey historyKey = SearchHistoryService.getHistoryKeyByName(name);
        if (historyKey == null) {
            SearchHistoryService.addHistoryKeyByName(name);
        } else {
            SearchHistoryService.updateHistoryKeyById(historyKey.getId());
        }
    }

    @Override
    public void removeHistoryKeyData(String key) {
        SearchHistoryService.deleteHistoryKeyByName(key);
    }

    @Override
    public void clearAllHistoryKeyData() {
        SearchHistoryService.deleteAllHistoryKeys();
    }
}
