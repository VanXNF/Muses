package com.victorxu.muses.product.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.product.view.entity.PromotionItem;

import java.util.List;

import androidx.annotation.Nullable;

public class PromotionAdapter extends BaseQuickAdapter<PromotionItem, BaseViewHolder> {

    public PromotionAdapter(@Nullable List<PromotionItem> data) {
        super(R.layout.item_promotion, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PromotionItem item) {
        helper.setText(R.id.promotion_text_description, item.getDescription());
        if (!item.isShowButton()) {
            helper.getView(R.id.promotion_text_get_ticket).setVisibility(View.GONE);
        } else {
            helper.addOnClickListener(R.id.promotion_text_get_ticket);
        }
    }
}
