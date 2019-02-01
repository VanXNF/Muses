package com.victorxu.muses.search.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.PageCommodity;

import java.util.List;

import androidx.annotation.Nullable;

public class ProductAdapter extends BaseQuickAdapter<PageCommodity.PageBean.CommodityListModel, BaseViewHolder> {

    public ProductAdapter(@Nullable List<PageCommodity.PageBean.CommodityListModel> data) {
        super(R.layout.item_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageCommodity.PageBean.CommodityListModel item) {
        helper.setText(R.id.text_product_title, item.getName())
                .setText(R.id.text_product_tag, item.getBrief())
                .setText(R.id.text_product_price, String.valueOf(item.getDiscountPrice()));

        GlideApp.with(mContext)
                .load(item.getCoverImage())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into((ImageView) helper.getView(R.id.image_product_image));
    }
}
