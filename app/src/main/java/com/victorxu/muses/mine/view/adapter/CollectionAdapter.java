package com.victorxu.muses.mine.view.adapter;

import android.text.TextUtils;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.Collection;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CollectionAdapter extends BaseQuickAdapter<Collection.CollectionBean, BaseViewHolder> {

    public CollectionAdapter(@Nullable List<Collection.CollectionBean> data) {
        super(R.layout.item_collection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Collection.CollectionBean item) {
        helper.setText(R.id.item_collection_title, item.getTitle())
                .setText(R.id.item_collection_price, String.valueOf(item.getPrice()));

        GlideApp.with(mContext)
                .load(item.getSrc())
                .apply(RequestOptions.centerCropTransform())
                .into((AppCompatImageView) helper.getView(R.id.item_collection_image));

        if (!TextUtils.isEmpty(item.getMessage())) {
            helper.setText(R.id.item_collection_tip, item.getMessage());
        }
    }
}
