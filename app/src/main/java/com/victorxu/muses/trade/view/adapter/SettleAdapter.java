package com.victorxu.muses.trade.view.adapter;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.ShoppingCart;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class SettleAdapter extends BaseQuickAdapter<ShoppingCart.CartItemBean, BaseViewHolder> {

    public SettleAdapter(@Nullable List<ShoppingCart.CartItemBean> data) {
        super(R.layout.item_settle_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCart.CartItemBean item) {
        helper.setText(R.id.item_settle_text_title, item.getCommodity().getName())
                .setText(R.id.item_settle_text_price, String.valueOf(item.getCommodity().getDiscountPrice()))
                .setText(R.id.item_settle_text_attr, item.getDetail())
                .setText(R.id.item_settle_text_number, String.valueOf(item.getNumber()));

        GlideApp.with(mContext)
                .load(item.getImage())
                .apply(RequestOptions.centerCropTransform())
                .into((AppCompatImageView) helper.getView(R.id.item_settle_image_order));
    }
}
