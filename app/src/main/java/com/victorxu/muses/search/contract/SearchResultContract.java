package com.victorxu.muses.search.contract;

import com.victorxu.muses.search.view.entity.ProductItem;

import java.util.List;

public interface SearchResultContract {
    interface Model {

        /*获取加载状态，是否加载结束*/
        boolean getDataStatus();

        List<ProductItem> getProductData();

        List<ProductItem> getMoreData();
    }

    interface View {

        void showProductList(List<ProductItem> data);

        void refreshProductList(List<ProductItem> data);

        void loadMoreProduct(List<ProductItem> data, boolean isCompleted);

    }

    interface Presenter {

        void loadData();

        void refreshData();

        void loadMoreData();
    }
}
