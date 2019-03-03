package com.victorxu.muses.creation.view.adapter;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.PageFilter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class FilterAdapter extends BaseQuickAdapter<PageFilter.FilterBean, BaseViewHolder> {

    public FilterAdapter(@Nullable List<PageFilter.FilterBean> data) {
        super(R.layout.item_filter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageFilter.FilterBean item) {
        helper.setText(R.id.item_filter_name, item.getFilterName());
        GlideApp.with(mContext)
                .load(item.getCoverImage())
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into((AppCompatImageView) helper.getView(R.id.item_filter_image));
    }
}
