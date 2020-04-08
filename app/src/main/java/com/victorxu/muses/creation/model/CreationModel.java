package com.victorxu.muses.creation.model;

import android.content.Context;

import com.victorxu.muses.creation.contract.CreationContract;
import com.victorxu.muses.creation.view.entity.PopularSearchItem;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

@SuppressWarnings("FieldCanBeLocal")
public class CreationModel implements CreationContract.Model {

    private final String FILTER_CLASS_LIST_API = "api/filterCategory/";
    private Context context;
    private Call mCallFilterClass;

    public CreationModel(Context context) {
        this.context = context;
    }

    @Override
    public void getFilterClassData(Callback callback) {
        mCallFilterClass = HttpUtil.getRequest(context, FILTER_CLASS_LIST_API, callback);
    }

    @Override
    public List<PopularSearchItem> getPopularSearchData() {
        return initLocalData();
    }

    private List<PopularSearchItem> initLocalData() {
        List<PopularSearchItem> local = new ArrayList<>();
        local.add(new PopularSearchItem(245, "糖果砖块", "http://muses.deepicecream.com:7010/img/filter_cover/img1.jpg"));
        local.add(new PopularSearchItem(89, "冷色调", "http://muses.deepicecream.com:7010/img/filter_cover/img2.jpg"));
        local.add(new PopularSearchItem(451, "白咖啡", "http://muses.deepicecream.com:7010/img/filter_cover/img3.jpg"));
        local.add(new PopularSearchItem(139, "水彩纸", "http://muses.deepicecream.com:7010/img/filter_cover/139.png"));
        local.add(new PopularSearchItem(143, "抽象线条", "http://muses.deepicecream.com:7010/img/filter_cover/143.png"));
        return local;
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallFilterClass);
    }

    private void cancelCall(Call call) {
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}
