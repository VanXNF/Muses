package com.victorxu.muses.mine.view.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Address;

import java.util.List;

import androidx.annotation.Nullable;

public class AddressAdapter extends BaseQuickAdapter<Address.AddressBean, BaseViewHolder> {

    public AddressAdapter(@Nullable List<Address.AddressBean> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Address.AddressBean item) {
        helper.setText(R.id.item_address_last_name, item.getSignerName().substring(0, 1))
                .setText(R.id.item_address_name, item.getSignerName())
                .setText(R.id.item_address_phone, item.getSignerMobile())
                .setText(R.id.item_address_address, convertAddress(item));
        helper.getView(R.id.item_address_default_flag).setVisibility(item.isDefaultAddress() ? View.VISIBLE : View.GONE);
        helper.addOnClickListener(R.id.item_address_edit);
    }

    private String convertAddress(Address.AddressBean item) {
        String address = "";
        if (!TextUtils.isEmpty(item.getProvince())) {
            address = address + item.getProvince() + " ";
        }
        if (!TextUtils.isEmpty(item.getCity())) {
            address = address + item.getCity() + " ";
        }
        if (!TextUtils.isEmpty(item.getDistrict())) {
            address = address + item.getDistrict() + " ";
        }
        if (!TextUtils.isEmpty(item.getAddress())) {
            address = address + item.getAddress();
        }
        return address;
    }
}
