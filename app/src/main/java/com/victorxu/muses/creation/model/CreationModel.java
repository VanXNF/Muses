package com.victorxu.muses.creation.model;

import com.victorxu.muses.creation.contract.CreationContract;
import com.victorxu.muses.creation.view.entity.PopularSearchItem;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;

public class CreationModel implements CreationContract.Model {

    private final String FILTER_CLASS_LIST_API = "api/filterCategory/";

    @Override
    public void getFilterClassData(Callback callback) {
        HttpUtil.getRequest(FILTER_CLASS_LIST_API, callback);
    }

    @Override
    public List<PopularSearchItem> getPopularSearchData() {
        return initLocalData();
    }

    private List<PopularSearchItem> initLocalData() {
        List<PopularSearchItem> local = new ArrayList<>();
        local.add(new PopularSearchItem(245, "糖果砖块", "http://muses.deepicecream.com:7010/img/filter_cover/245.png"));
        local.add(new PopularSearchItem(184, "水墨艺术", "http://muses.deepicecream.com:7010/img/filter_cover/184.png"));
        local.add(new PopularSearchItem(178, "蜂蜜", "http://muses.deepicecream.com:7010/img/filter_cover/178.png"));
        local.add(new PopularSearchItem(139, "水彩纸", "http://muses.deepicecream.com:7010/img/filter_cover/139.png"));
        local.add(new PopularSearchItem(143, "抽象线条", "http://muses.deepicecream.com:7010/img/filter_cover/143.png"));
        return local;
    }
}
