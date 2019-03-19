package com.victorxu.muses.gallery.view.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.gallery.view.entity.ImageItem;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.ListCommodity;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<ListCommodity.CommodityListModel, BaseViewHolder> {


    public RecommendAdapter(@Nullable List<ListCommodity.CommodityListModel> data) {
        super(R.layout.item_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListCommodity.CommodityListModel item) {
        GlideApp.with(mContext)
                .load(item.getCoverImage())
                .into((AppCompatImageView) helper.getView(R.id.image_recommend));
    }
}
