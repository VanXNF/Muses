package com.victorxu.muses.creation.view.adapter;

import android.graphics.Bitmap;
import android.graphics.MaskFilter;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.creation.view.entity.PopularSearchItem;
import com.victorxu.muses.glide.GlideApp;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PopularSearchAdapter extends BaseQuickAdapter<PopularSearchItem, BaseViewHolder> {

    public PopularSearchAdapter(@Nullable List<PopularSearchItem> data) {
        super(R.layout.item_popular_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PopularSearchItem item) {
        helper.setText(R.id.item_popular_search_name, item.getName());

        GlideApp.with(mContext)
                .load(item.getCoverImg())
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into((AppCompatImageView) helper.getView(R.id.item_popular_search_image));
    }
}
