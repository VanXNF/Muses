package com.victorxu.muses.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.gson.Address;
import com.victorxu.muses.mine.contract.AddressContract;
import com.victorxu.muses.mine.presenter.AddressPresenter;
import com.victorxu.muses.mine.view.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddressFragment extends BaseSwipeBackFragment implements AddressContract.View {

    private final int REQUEST_ADD = 1;
    private final int REQUEST_EDIT = 0;

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private AddressAdapter mAdapter;
    private AddressPresenter mPresenter;

    private List<Address.AddressBean> mAddressData = new ArrayList<>();
    private boolean isChooseMode = false;

    public static AddressFragment newInstance() {
        return new AddressFragment();
    }

    public static AddressFragment newInstance(boolean isChooseMode) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("MODE", isChooseMode);
        AddressFragment fragment = new AddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isChooseMode = bundle.getBoolean("MODE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        mPresenter = new AddressPresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenter.loadDataToView();
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String type = data.getString("TYPE");
            switch (requestCode) {
                case REQUEST_ADD:
                    if (type.equals("add")) {
                        Address.AddressBean addressBean = (Address.AddressBean) data.getSerializable("DATA");
                        mPresenter.addAddress(addressBean);
                    }
                    break;
                case REQUEST_EDIT:
                    if (type.equals("edit")) {
                        Address.AddressBean addressBean = (Address.AddressBean) data.getSerializable("DATA");
                        mPresenter.updateAddress(addressBean);
                    } else if (type.equals("delete")) {
                        int addressId = data.getInt("ADDRESS_ID");
                        mPresenter.deleteAddress(addressId);
                    }
                    break;
            }
        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.address_toolbar;
    }

    @Override
    public void initRootView(View view) {
        mToolbar = view.findViewById(R.id.address_toolbar);
        mRecycler = view.findViewById(R.id.address_recycler_view);

        mToolbar.inflateMenu(R.menu.menu_address);
        mToolbar.setNavigationOnClickListener((v) -> mActivity.onBackPressed());

        mToolbar.setOnMenuItemClickListener((MenuItem item) -> {
            int id = item.getItemId();
            if (id == R.id.menu_address_new) {
                startForResult(EditAddressFragment.newInstance(), REQUEST_ADD);
            }
            return false;
        });

        mAdapter = new AddressAdapter(mAddressData);
        if (isChooseMode) {
            mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View v, int position) -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", mAddressData.get(position));
                setFragmentResult(RESULT_OK, bundle);
                pop();
            });
        }

        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            int id = v.getId();
            if (id == R.id.item_address_edit) {
                startForResult(EditAddressFragment.newInstance(mAddressData.get(position)), REQUEST_EDIT);
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showAddress(List<Address.AddressBean> data) {
        mAddressData.clear();
        mAddressData.addAll(data);
        post(() -> {
            mAdapter.setNewData(mAddressData);
            mAdapter.notifyDataSetChanged();
        });
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
