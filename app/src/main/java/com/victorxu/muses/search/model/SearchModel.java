package com.victorxu.muses.search.model;


import android.content.Context;

import com.victorxu.muses.db.entity.HistoryKey;
import com.victorxu.muses.db.service.SearchHistoryService;
import com.victorxu.muses.search.contract.SearchContract;
import com.victorxu.muses.util.HttpUtil;

import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

@SuppressWarnings("ConstantConditions")
public class SearchModel implements SearchContract.Model {

    @SuppressWarnings("FieldCanBeLocal")
    private final String HOT_KEY_API = "api/hotkey/";
    private Context context;
    private Call mCallHotKay;

    public SearchModel(Context context) {
        this.context = context;
    }

    @Override
    public void getHotKeyData(Callback callback) {
        mCallHotKay = HttpUtil.getRequest(context, HOT_KEY_API, callback);
    }

    @Override
    public List<HistoryKey> getHistoryKeyData() {
        List<HistoryKey> historyKeys = SearchHistoryService.getAllHistoryKeys();
        if (historyKeys != null && historyKeys.size() != 0) {
            return historyKeys;
        }
        return null;
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

    @Override
    public void cancelTask() {
        cancelCall(mCallHotKay);
    }

    private void cancelCall(Call call) {
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}
