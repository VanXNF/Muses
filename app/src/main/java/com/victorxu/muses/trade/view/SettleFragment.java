package com.victorxu.muses.trade.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;

import com.victorxu.muses.gson.Address;
import com.victorxu.muses.gson.DefaultAddress;
import com.victorxu.muses.gson.ShoppingCart;
import com.victorxu.muses.trade.contract.SettleContract;
import com.victorxu.muses.trade.presenter.SettlePresenter;
import com.victorxu.muses.trade.view.adapter.SettleAdapter;
import com.victorxu.muses.trade.view.entity.SettleOrderBean;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettleFragment extends BaseFragment implements SettleContract.View {

    private Toolbar mToolbarSettle;
    private View mViewAddressPicker;
    private AppCompatTextView mTextChooseAddress;
    private AppCompatTextView mTextConsignee;
    private AppCompatTextView mTextPhone;
    private AppCompatTextView mTextAddress;
    private RecyclerView mRecyclerSettle;
    private AppCompatTextView mTextTotalPrice;
    private AppCompatTextView mTextSubmit;

    private SettleAdapter mAdapterSettle;
    private SettleOrderBean mOrderData;
    private SettlePresenter mPresenterSettle;

    public static SettleFragment newInstance(SettleOrderBean data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATA", data);
        SettleFragment fragment = new SettleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mOrderData = (SettleOrderBean) bundle.getSerializable("DATA");
        } else {
            showToast(R.string.data_error_please_try_again);
            pop();
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenterSettle.loadDefaultAddress();
        mPresenterSettle.loadCartItemOfCart(mOrderData.getOrderData());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.settle_toolbar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settle, container, false);
        mPresenterSettle = new SettlePresenter(this, mActivity);
        mPresenterSettle.loadRootView(view);
        return view;
    }

    @Override
    public void initRootView(View view) {
        mToolbarSettle = view.findViewById(R.id.settle_toolbar);
        mViewAddressPicker = view.findViewById(R.id.settle_address_view);
        mTextChooseAddress = view.findViewById(R.id.settle_address_text_choose_address);
        mTextConsignee = view.findViewById(R.id.settle_address_text_consignee);
        mTextPhone = view.findViewById(R.id.settle_address_text_phone);
        mTextAddress = view.findViewById(R.id.settle_address_text_shipping_address);
        mTextTotalPrice = view.findViewById(R.id.settle_order_text_price);
        mTextSubmit = view.findViewById(R.id.settle_order_text_submit_order);
        mRecyclerSettle = view.findViewById(R.id.settle_order_recycler_view);

        mToolbarSettle.setNavigationOnClickListener(v -> mActivity.onBackPressed());
        mViewAddressPicker.setOnClickListener(v -> {
        });
        mTextSubmit.setOnClickListener(v -> {
        });

        mRecyclerSettle.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapterSettle = new SettleAdapter(new ArrayList<>());
        mRecyclerSettle.setAdapter(mAdapterSettle);
    }

    @Override
    public void showCartItemOfOrder(List<ShoppingCart.CartItemBean> data) {
        post(() -> {
            mAdapterSettle.setNewData(data);
            mAdapterSettle.notifyDataSetChanged();
        });
    }

    @Override
    public void showAddress(Address.AddressBean data) {
        post(() -> {
            String consignee = getText(R.string.consignee_with_colon) + data.getSignerName();
            mTextConsignee.setText(consignee);
            mTextPhone.setText(data.getSignerMobile());
            String address = getText(R.string.shipping_address_with_colon) + data.getProvince() + data.getCity() + data.getDistrict() + data.getAddress();
            mTextAddress.setText(address);
            mTextChooseAddress.setVisibility(View.GONE);
            mTextConsignee.setVisibility(View.VISIBLE);
            mTextPhone.setVisibility(View.VISIBLE);
            mTextAddress.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void showAddress(DefaultAddress.DefaultAddressBean data) {
        post(() -> {
            String consignee = getText(R.string.consignee_with_colon) + data.getSignerName();
            mTextConsignee.setText(consignee);
            mTextPhone.setText(data.getSignerMobile());
            String address = getText(R.string.shipping_address_with_colon) + data.getProvince() + data.getCity() + data.getDistrict() + data.getAddress();
            mTextAddress.setText(address);
            mTextChooseAddress.setVisibility(View.GONE);
            mTextConsignee.setVisibility(View.VISIBLE);
            mTextPhone.setVisibility(View.VISIBLE);
            mTextAddress.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void showTotalPrice(String price) {
        post(() -> mTextTotalPrice.setText(price));
    }

    @Override
    public void showPayPage() {

    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }
}
