package com.victorxu.muses.product.contract;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.view.entity.StyleSelectItem;

import java.util.List;

public interface ProductContract {
    interface Model {
        void getProductData(okhttp3.Callback callback);
        void getCommentData(okhttp3.Callback callback);
        void addProductDataToCart(okhttp3.Callback callback);
        void checkFavoriteStatus(okhttp3.Callback callback);
        void addProductDataToFavorite(okhttp3.Callback callback);
        void removeProductDataFromFavorite(okhttp3.Callback callback);
        List<StyleSelectItem> getStyleSelectData(List<Commodity.CommodityDetail.AttributesBean> attributesBeans);
        List<String> getAttributeInfoData(String information);
        String getSelectDetail();
        void updateStyleSelectNumber(int number);
        void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected);
        boolean checkUserStatus();
        void setFavoriteId(int id);
    }

    interface View {
        void initRootView(android.view.View view);
        void showBaseInfo(Commodity.CommodityDetail data);
        void showBanner(List<String> imageUrls);
        void showProductDetail(String htmlData);
        void showEvaluation(List<PageComment.PageCommentData.CommentBean> commentData);
        void showAttributeBottomSheet(List<String> data);
        void showStyleBottomSheet(List<StyleSelectItem> data);
        void showSelectDetail(String detail);
        void showFavorite(boolean isFavorite);
        void showToast(int resId);
        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);
        void loadDataToView();
        void addToCart();
        void addToFavorite();
        void removeFromFavorite();
        void updateStyleSelectNumber(int number);
        void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected, boolean isCompleted);
    }
}
