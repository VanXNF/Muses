package com.victorxu.muses.gallery.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.gallery.view.entity.ImageItem;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<ImageItem, BaseViewHolder> {

    public RecommendAdapter(int layoutResId, @Nullable List<ImageItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageItem item) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new RoundedCorners(mContext.getResources().getInteger(R.integer.recommend_image_radius)));
        Glide.with(mContext).load(item.getmImageUri() == null ? item.getmImageResId() : item.getmImageUri())
                .apply(options)
                .into((AppCompatImageView) helper.getView(R.id.image_recommend));
    }

}
