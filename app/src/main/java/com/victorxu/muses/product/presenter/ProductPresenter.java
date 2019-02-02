package com.victorxu.muses.product.presenter;

import android.view.View;

import com.victorxu.muses.product.contract.ProductContract;
import com.victorxu.muses.product.model.ProductModel;

public class ProductPresenter implements ProductContract.Presenter {

    private ProductContract.View mView;
    private ProductContract.Model mModel;

    public ProductPresenter(ProductContract.View mView) {
        this.mView = mView;
        mModel = new ProductModel();
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadDataToView() {
        mView.showBanner();
        mView.showProductDetail("<p><img align='absmiddle' src='https://img.alicdn.com/imgextra/i2/1036275215/TB2UC84cjnD8KJjSspbXXbbEXXa_!!1036275215.jpg' class='img-ks-lazyload'><img align='absmiddle' src='https://img.alicdn.com/imgextra/i3/1036275215/TB2WsXBhSCWBuNjy0FhXXb6EVXa_!!1036275215.jpg' class='img-ks-lazyload' data-spm-anchor-id='a220o.1000855.0.i0.50813b53SsihXC'><img align='absmiddle' src='https://img.alicdn.com/imgextra/i1/1036275215/TB2W8fbb0cnBKNjSZR0XXcFqFXa_!!1036275215.jpg' class='img-ks-lazyload'><img align='absmiddle' src='https://img.alicdn.com/imgextra/i4/1036275215/TB28s2Tb2ImBKNjSZFlXXc43FXa_!!1036275215.jpg' class='img-ks-lazyload'><img src='https://img.alicdn.com/imgextra/i3/1036275215/O1CN01Jg3HXs1oOVyMtfhMe_!!1036275215.jpg' align='absmiddle' class='img-ks-lazyload'><img src='https://img.alicdn.com/imgextra/i4/1036275215/O1CN011oOVxQtIWSWCQiB_!!1036275215.jpg' align='absmiddle' class='img-ks-lazyload'><img align='absmiddle' src='https://img.alicdn.com/imgextra/i1/1036275215/TB21UN4ccLJ8KJjy0FnXXcFDpXa_!!1036275215.jpg' class='img-ks-lazyload'></p>");
        mView.showComment();
    }
}
