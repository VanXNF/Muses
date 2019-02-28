package com.victorxu.muses.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.gson.Address;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private AppCompatTextView mTextLocaltion;
    private AppCompatEditText mEditLocationDetail;
    private Switch mSwitchDefault;
    private AppCompatTextView mTextSave;
    private AppCompatTextView mTextDelete;

    public static EditAddressFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        EditAddressFragment fragment = new EditAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static EditAddressFragment newInstance(Address.AddressBean data) {
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", TYPE_EDIT);
        // TODO: 2019/2/28 store data; 
        EditAddressFragment fragment = new EditAddressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("TYPE");
        } else {
            type = 1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_address, container, false);
        initRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mToolbar.setNavigationOnClickListener((v) -> mActivity.onBackPressed());
        String title = "";
        if (type == 0) {
            title += getString(R.string.edit_address);
        } else if (type == 1) {
            title += getString(R.string.add_new_address);
        }
        mToolbar.setTitle(title);

        Bundle bundle = new Bundle();
        if (type == 1) {
            bundle.putString("Type", "add");
            setFragmentResult(RESULT_OK, bundle);
            mTextDelete.setVisibility(View.GONE);
        } else if (type == 0) {
            bundle.putString("Type", "edit");
            setFragmentResult(RESULT_OK, bundle);
        }
    }

    private void initRootView(View view) {
        mToolbar = view.findViewById(R.id.edit_address_toolbar);
        mEditReceiver = view.findViewById(R.id.address_edit_receiver);
        mEditPhone = view.findViewById(R.id.address_edit_phone);
        mTextLocaltion = view.findViewById(R.id.address_edit_location);
        mEditLocationDetail = view.findViewById(R.id.address_edit_location_detail);
        mSwitchDefault = view.findViewById(R.id.address_edit_switch_default);
        mTextSave = view.findViewById(R.id.address_edit_save_address);
        mTextDelete = view.findViewById(R.id.address_edit_delete_address);

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.edit_address_toolbar;
    }
}
