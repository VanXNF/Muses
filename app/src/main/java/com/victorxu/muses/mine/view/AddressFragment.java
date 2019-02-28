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

    public static AddressFragment newInstance() {
        return new AddressFragment();
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
    public void initRootView(View view) {
        mToolbar = view.findViewById(R.id.address_toolbar);
        mRecycler = view.findViewById(R.id.address_recycler_view);
        
        mToolbar.inflateMenu(R.menu.menu_address);
        mToolbar.setNavigationOnClickListener((v) -> mActivity.onBackPressed());

        mToolbar.setOnMenuItemClickListener((MenuItem item) -> {
            int id = item.getItemId();
            if (id == R.id.menu_address_new) {
                startForResult(EditAddressFragment.newInstance(EditAddressFragment.TYPE_ADD), REQUEST_ADD);
            }
            return false;
        });

        mAdapter = new AddressAdapter(mAddressData);
        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            int id = v.getId();
            if (id == R.id.item_address_edit) {
                startForResult(EditAddressFragment.newInstance(EditAddressFragment.TYPE_EDIT), REQUEST_EDIT);
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

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_ADD:
                    // TODO: 2019/2/28 presenter to add new address;
                    showToast(data.getString("Type"));
                    break;
                case REQUEST_EDIT:
                    // TODO: 2019/2/28 presenter to update address;
                    showToast(data.getString("Type"));
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
}
