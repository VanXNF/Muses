package com.victorxu.muses.mine.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.gson.Address;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

public class EditAddressFragment extends BaseSwipeBackFragment {

    public static final int TYPE_EDIT = 0;
    public static final int TYPE_ADD = 1;

    private int type;

    private Toolbar mToolbar;
    private AppCompatEditText mEditReceiver;
    private AppCompatEditText mEditPhone;
    private AppCompatTextView mTextLocation;
    private AppCompatEditText mEditLocationDetail;
    private Switch mSwitchDefault;
    private AppCompatTextView mTextSave;
    private AppCompatTextView mTextDelete;
    private CityPickerView mCityPicker = new CityPickerView();

    private Address.AddressBean mAddressData;

    public static EditAddressFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", TYPE_ADD);
        EditAddressFragment fragment = new EditAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static EditAddressFragment newInstance(Address.AddressBean data) {
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", TYPE_EDIT);
        bundle.putSerializable("DATA", data);
        EditAddressFragment fragment = new EditAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCityPicker.init(mActivity);
        mAddressData = new Address.AddressBean();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("TYPE");
            if (type == TYPE_EDIT) {
                Address.AddressBean data = (Address.AddressBean) bundle.getSerializable("DATA");
                inputAddressData(data);
            }
        } else {
            type = TYPE_ADD;
        }
    }

    private void inputAddressData(Address.AddressBean data) {
//        mAddressData = new Address.AddressBean();
        mAddressData.setId(data.getId());
        mAddressData.setProvince(data.getProvince());
        mAddressData.setCity(data.getCity());
        mAddressData.setDistrict(data.getDistrict());
        mAddressData.setAddress(data.getAddress());
        mAddressData.setSignerName(data.getSignerName());
        mAddressData.setSignerMobile(data.getSignerMobile());
        mAddressData.setUserId(data.getUserId());
        mAddressData.setDefaultAddress(data.isDefaultAddress());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_address, container, false);
        initRootView(view);
        return attachToSwipeBack(view);
    }

    private void initRootView(View view) {
        mToolbar = view.findViewById(R.id.edit_address_toolbar);
        mEditReceiver = view.findViewById(R.id.address_edit_receiver);
        mEditPhone = view.findViewById(R.id.address_edit_phone);
        mTextLocation = view.findViewById(R.id.address_edit_location);
        mEditLocationDetail = view.findViewById(R.id.address_edit_location_detail);
        mSwitchDefault = view.findViewById(R.id.address_edit_switch_default);
        mTextSave = view.findViewById(R.id.address_edit_save_address);
        mTextDelete = view.findViewById(R.id.address_edit_delete_address);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mToolbar.setNavigationOnClickListener((v) -> mActivity.onBackPressed());

        CityConfig.Builder cityConfig = new CityConfig.Builder()
                .confirTextColor("#FF4013")
                .setShowGAT(true);

        if (type == TYPE_ADD) {
            mToolbar.setTitle(getText(R.string.add_new_address));
            mTextDelete.setVisibility(View.GONE);
        } else if (type == TYPE_EDIT) {
            mToolbar.setTitle(R.string.edit_address);
            mEditReceiver.setText(mAddressData.getSignerName());
            mEditPhone.setText(mAddressData.getSignerMobile());
            String location = "";
            if (!TextUtils.isEmpty(mAddressData.getProvince())) {
                location = location + mAddressData.getProvince() + " ";
                cityConfig.province(mAddressData.getProvince());
            }
            if (!TextUtils.isEmpty(mAddressData.getCity())) {
                location = location + mAddressData.getCity() + " ";
                cityConfig.city(mAddressData.getCity());
            }
            if (!TextUtils.isEmpty(mAddressData.getDistrict())) {
                location = location + mAddressData.getDistrict();
                cityConfig.district(mAddressData.getDistrict());
            }
            mTextLocation.setText(location);
            mEditLocationDetail.setText(mAddressData.getAddress());
            mSwitchDefault.setChecked(mAddressData.isDefaultAddress());
        }

        mCityPicker.setConfig(cityConfig.build());
        mCityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                mAddressData.setProvince(province.getName());
                mAddressData.setCity(city.getName());
                mAddressData.setDistrict(district.getName());
                mTextLocation.setText(province.getName() + " " + city.getName() + " " + district.getName());
            }
        });
        mTextLocation.setOnClickListener(v -> {
            hideSoftInput();
            mCityPicker.showCityPicker();
        });
        Bundle bundle = new Bundle();
        mTextSave.setOnClickListener(v -> {
            hideSoftInput();
            if (checkAddressData()) {
                if (type == TYPE_ADD) {
                    bundle.putString("TYPE", "add");
                    bundle.putSerializable("DATA", mAddressData);
                    setFragmentResult(RESULT_OK, bundle);
                } else if (type == TYPE_EDIT) {
                    bundle.putString("TYPE", "edit");
                    bundle.putSerializable("DATA", mAddressData);
                    setFragmentResult(RESULT_OK, bundle);
                }
                pop();
            }
        });
        mTextDelete.setOnClickListener(v -> {
            hideSoftInput();
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setMessage(getString(R.string.are_you_sure_delete_address))
                    .setPositiveButton(getString(R.string.confirm), (DialogInterface dialog, int id) -> {
                        bundle.putString("TYPE", "delete");
                        bundle.putInt("ADDRESS_ID", mAddressData.getId());
                        setFragmentResult(RESULT_OK, bundle);
                        pop();
                    })
                    .setNegativeButton(getString(R.string.cancel), (DialogInterface dialog, int id) -> {
                    });
            builder.show();
        });
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.edit_address_toolbar;
    }

    private boolean checkAddressData() {
        if (!TextUtils.isEmpty(mEditReceiver.getText())) {
            mAddressData.setSignerName(mEditReceiver.getText().toString());
        } else {
            showToast(R.string.please_input_receiver);
            return false;
        }
        if (!TextUtils.isEmpty(mEditPhone.getText())) {
            mAddressData.setSignerMobile(mEditPhone.getText().toString());
        } else {
            showToast(R.string.please_input_mobile);
            return false;
        }
        if (TextUtils.isEmpty(mTextLocation.getText())) {
            showToast(R.string.please_choose_location);
            return false;
        }
        if (!TextUtils.isEmpty(mEditLocationDetail.getText())) {
            mAddressData.setAddress(mEditLocationDetail.getText().toString());
        } else {
            showToast(R.string.please_input_location_detail);
            return false;
        }
        mAddressData.setDefaultAddress(mSwitchDefault.isChecked());
        return true;
    }

    private void showToast(int resId) {
        showToast(getText(resId));
    }

    private void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }
}
