package com.victorxu.muses.creation.view.adapter;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.FilterClass;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class FilterClassAdapter extends BaseQuickAdapter<FilterClass.FilterClassBean, BaseViewHolder> {

    public FilterClassAdapter(@Nullable List<FilterClass.FilterClassBean> data) {
        super(R.layout.item_filter_class, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterClass.FilterClassBean item) {
        helper.setText(R.id.item_filter_class_name, item.getCategoryName());
        GlideApp.with(mContext)
                .load(item.getImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(((AppCompatImageView) helper.getView(R.id.item_filter_class_image)));
    }
}
