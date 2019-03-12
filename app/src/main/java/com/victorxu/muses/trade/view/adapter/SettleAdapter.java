package com.victorxu.muses.trade.view.adapter;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.trade.view.entity.CartSettleOrderBean;
import com.victorxu.muses.trade.view.entity.ProductSettleOrderBean;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class SettleAdapter extends BaseQuickAdapter<ProductSettleOrderBean, BaseViewHolder> {


    public SettleAdapter(@Nullable List<ProductSettleOrderBean> data) {
        super(R.layout.item_settle_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductSettleOrderBean item) {
        helper.setText(R.id.item_settle_text_title, item.getTitle())
                .setText(R.id.item_settle_text_price, item.getPrice())
                .setText(R.id.item_settle_text_attr, item.getDetail())
                .setText(R.id.item_settle_text_number, item.getNumber());

        GlideApp.with(mContext)
                .load(item.getImage())
                .apply(RequestOptions.centerCropTransform())
                .into((AppCompatImageView) helper.getView(R.id.item_settle_image_order));
    }

}
