package com.victorxu.muses.creation.model;

import com.victorxu.muses.creation.contract.CreationContract;
import com.victorxu.muses.util.HttpUtil;

import okhttp3.Callback;

public class CreationModel implements CreationContract.Model {

    private final String FILTER_CLASS_LIST_API = "api/filterCategory/";

    @Override
    public void getFilterClassData(Callback callback) {
        HttpUtil.getRequest(FILTER_CLASS_LIST_API, callback);
    }
}
