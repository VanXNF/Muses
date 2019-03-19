package com.victorxu.muses.trade.contract;

import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;
import com.victorxu.muses.trade.view.entity.StyleSelectItem;

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

        void setProductOrderImage(String image);

        void setCommodityData(Commodity.CommodityDetail data);

        ProductSettleOrderBean getProductSettleData();

        void cancelTask();
    }

    interface View {
        void initRootView(android.view.View view);

        void showBaseInfo(Commodity.CommodityDetail data);

        void showBanner(List<String> imageUrls);

        void showProductDetail(String htmlData);

        void showEvaluation(List<PageComment.PageCommentData.CommentBean> commentData);

        void showEmptyEvaluation();

        void showAttributeBottomSheet(List<String> data);

        void showStyleBottomSheet(List<StyleSelectItem> data);

        void showSelectDetail(String detail);

        void showFavorite(boolean isFavorite);

        void showSettleFragment(ProductSettleOrderBean data);

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView();

        void addToCart();

        void buyNow();

        void addToFavorite();

        void removeFromFavorite();

        void updateStyleSelectNumber(int number);

        void updateStyleSelectDetail(String key, String value, int parameterId, boolean isSelected, boolean isCompleted);

        void updateProductImage(String image);

        void destroy();
    }
}
