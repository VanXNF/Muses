package com.victorxu.muses.trade.contract;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;
import com.victorxu.muses.trade.view.entity.StyleSelectItem;

import java.util.List;

public interface CustomizeContract {
    interface Model {
        void getProductData(okhttp3.Callback callback);

        List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans);

        String getSelectDetail();

        void updateStyleSelectNumber(int number);

        void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected);

        void setProductOrderImage(String image);

        void setCommodityData(Commodity.CommodityDetail data);

        ProductSettleOrderBean getProductSettleData();

        void cancelTask();
    }

    interface View {
        void initRootView(android.view.View view);

        void showCommodityData(Commodity.CommodityDetail data);

        void showStyleData(List<StyleSelectItem> data);

        void showSelectDetail(String detail);

        void showSettleFragment(ProductSettleOrderBean data);

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView();

        void settleProduct();

        void updateStyleSelectNumber(int number);

        void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected, boolean isCompleted);

        void updateProductImage(String image);

        void destroy();
    }
}
