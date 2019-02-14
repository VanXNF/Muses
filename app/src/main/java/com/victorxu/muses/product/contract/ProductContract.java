package com.victorxu.muses.product.contract;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.view.entity.StyleSelectItem;

import java.util.List;

public interface ProductContract {
    interface Model {
        void getProductData(okhttp3.Callback callback);
        void getCommentData(okhttp3.Callback callback);
        List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans);
    }

    interface View {
        void initRootView(android.view.View view);
        void showBaseInfo(Commodity.CommodityDetail data);
        void showBanner(List<String> imageUrls);
        void showProductDetail(String htmlData);
        void showEvaluation(List<PageComment.PageCommentData.CommentModel> commentData);
        void showAttributeBottomSheet();
        void showStyleBottomSheet(List<StyleSelectItem> data);
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
    }
}
