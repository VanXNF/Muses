package com.victorxu.muses.trade.model;

import android.content.Context;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.trade.contract.CustomizeContract;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;
import com.victorxu.muses.trade.view.entity.StyleSelectItem;
import com.victorxu.muses.util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;

public class CustomizeModel implements CustomizeContract.Model {

    private final String COMMODITY_API_PREFIX = "api/commodity/";

    private int id;
    private Context context;

    private Commodity.CommodityDetail mCommodityData;
    private int number = 1;
    private Map<String, Integer> parameter = new HashMap<>();
    private Map<String, String> detail = new HashMap<>();
    private String image;

    private Call mCallGet;

    public CustomizeModel(int id, Context context) {
        this.id = id;
        this.context = context;
    }

    @Override
    public void getProductData(Callback callback) {
        mCallGet = HttpUtil.getRequest(COMMODITY_API_PREFIX + String.valueOf(id), callback);
    }

    @Override
    public List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans) {
        List<StyleSelectItem> styleSelectItems = new ArrayList<>();
        for (int i = 0; i < attributesBeans.size(); i++) {
            styleSelectItems.add(new StyleSelectItem(attributesBeans.get(i)));
        }
        styleSelectItems.add(new StyleSelectItem(1));
        return styleSelectItems;
    }

    @Override
    public String getSelectDetail() {
        String s = detail.toString();
        s = s.substring(s.indexOf('{') + 1, s.lastIndexOf('}'));
        s = s.replace('=', ':');
        s = s.replace(", ", ";");
        return s;
    }

    @Override
    public void updateStyleSelectNumber(int number) {
        this.number = number;
    }

    @Override
    public void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected) {
        if (isSelected) {
            detail.put(key, value);
            parameter.put(key, parameterId);
        } else {
            detail.remove(key);
            parameter.remove(key);
        }
    }

    @Override
    public void setProductOrderImage(String image) {
        this.image = image;
    }

    @Override
    public void setCommodityData(Commodity.CommodityDetail data) {
        mCommodityData = data;
    }

    @Override
    public ProductSettleOrderBean getProductSettleData() {
        ProductSettleOrderBean data = new ProductSettleOrderBean();
        data.setCommodityId(mCommodityData.getId());
        data.setDetail(getSelectDetail());
        data.setNumber(String.valueOf(number));
        data.setParameter(getParameter());
        data.setTitle(mCommodityData.getName());
        data.setPrice(String.valueOf(mCommodityData.getDiscountPrice()));
        data.setImage(image);
        return data;
    }

    private String getParameter() {
        StringBuilder builder = new StringBuilder();
        for (Integer value : parameter.values()) {
            builder.append(value);
            builder.append(',');
        }
        String s = builder.toString();
        return s.substring(0, s.lastIndexOf(','));
    }

    @Override
    public void cancelTask() {
        cancelCall(mCallGet);
    }

    private void cancelCall(Call call) {
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}
