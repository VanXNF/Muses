package com.victorxu.muses.product.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;

import java.util.List;

import androidx.annotation.Nullable;

public class AttributeInfoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AttributeInfoAdapter(@Nullable List<String> data) {
        super(R.layout.item_attribute_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        String data[] = item.split(":");
        helper.setText(R.id.item_attribute_name, data[0].substring(data[0].indexOf('"') + 1, data[0].lastIndexOf('"')));
        helper.setText(R.id.item_attribute_value, data[1].substring(data[1].indexOf('"') + 1, data[1].lastIndexOf('"')));
    }
}
