package com.victorxu.muses.shopping_cart.view.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.shopping_cart.view.entity.ShoppingCartProduct;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ShoppingCartAdapter extends BaseQuickAdapter<ShoppingCartProduct, BaseViewHolder> {

    public ShoppingCartAdapter(@Nullable List<ShoppingCartProduct> data) {
        super(R.layout.cart_product_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartProduct item) {

        ((CheckBox) helper.getView(R.id.cart_check_item)).setChecked(item.isChecked());

        helper.setText(R.id.cart_text_product_title, item.getTitleText())
                .setText(R.id.cart_text_product_attr, item.getAttributeText())
                .setText(R.id.cart_text_product_attr_edit_mode, item.getAttributeText())
                .setText(R.id.cart_text_product_price, item.getPrice())
                .setText(R.id.cart_text_product_number, String.valueOf(item.getNumber()))
                .addOnClickListener(R.id.cart_check_item)
                .addOnClickListener(R.id.cart_image_item)
                .addOnClickListener(R.id.cart_image_add)
                .addOnClickListener(R.id.cart_image_remove)
                .addOnClickListener(R.id.cart_attr_container_edit_mode);

        Glide.with(mContext)
                .load(item.getImageUri())
                .into((AppCompatImageView) helper.getView(R.id.cart_image_item));


        helper.getView(R.id.cart_item_number_control).setVisibility(item.isEditedMode()? View.GONE : View.VISIBLE);

        helper.getView(R.id.cart_attr_container_edit_mode).setVisibility(item.isEditedMode() ? View.VISIBLE : View.GONE);

        helper.getView(R.id.cart_text_product_attr).setVisibility(item.isEditedMode() ? View.GONE : View.VISIBLE);


    }
}
