package com.victorxu.muses.search.view.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.custom.AdvancedImageView;
import com.victorxu.muses.search.view.entity.ProductItem;

import java.util.List;

import androidx.annotation.Nullable;

public class ProductAdapter extends BaseQuickAdapter<ProductItem, BaseViewHolder> {

    public ProductAdapter(@Nullable List<ProductItem> data) {
        super(R.layout.item_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductItem item) {
        helper.setText(R.id.text_product_title, item.getmTitle())
                .setText(R.id.text_product_tag, item.getmTag())
                .setText(R.id.text_product_price, item.getmPrice());

        Glide.with(mContext)
                .load(item.getmImageUri())
                .into((AdvancedImageView) helper.getView(R.id.image_product_image));
    }
}
