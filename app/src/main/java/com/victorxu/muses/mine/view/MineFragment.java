package com.victorxu.muses.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.account.view.UserInfoFragment;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.core.MainFragment;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.UserStatus;
import com.victorxu.muses.mine.contract.MineContract;
import com.victorxu.muses.mine.presenter.MinePresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class MineFragment extends BaseMainFragment implements MineContract.View {

    private AppCompatTextView mTextName;
    private AppCompatTextView mTextId;
    private AppCompatImageView mImgAvatar;
    private AppCompatTextView mTextCollectionCount;
    private AppCompatImageView mImgSetting;
    private View mViewCollection;
    private View mViewAddress;
    private View mViewPendingPayment;
    private View mViewToBeDelivered;
    private View mViewPendingReceipt;
    private View mViewWaitingForEvaluation;
    private View mViewReturnAfterSale;

    private MinePresenter mPresenter;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mPresenter = new MinePresenter(this, mActivity);
        mPresenter.loadRootView(view);
        return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.loadDataToView();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.mine_page_bar;
    }

    @Override
    public void initRootView(View view) {
        mTextName = view.findViewById(R.id.mine_text_username);
        mTextId = view.findViewById(R.id.mine_text_user_id);
        mImgAvatar = view.findViewById(R.id.mine_image_avatar);
        mTextCollectionCount = view.findViewById(R.id.mine_text_collection);
        mViewCollection = view.findViewById(R.id.mine_view_collection);
        mViewAddress = view.findViewById(R.id.mine_relative_address_management);
        mViewPendingPayment = view.findViewById(R.id.mine_linear_pending_payment);
        mViewToBeDelivered = view.findViewById(R.id.mine_linear_to_be_delivered);
        mViewPendingReceipt = view.findViewById(R.id.mine_linear_pending_receipt);
        mViewWaitingForEvaluation = view.findViewById(R.id.mine_linear_waiting_for_evaluation);
        mViewReturnAfterSale = view.findViewById(R.id.mine_linear_return_after_sale);
        mImgSetting = view.findViewById(R.id.mine_button_setting);

        mTextName.setOnClickListener(v -> mPresenter.goToAccount());
        mImgAvatar.setOnClickListener(v -> mPresenter.goToAccount());
        mViewCollection.setOnClickListener(v -> ((MainFragment) getParentFragment()).startBrotherFragment(CollectionFragment.newInstance()));
        mViewAddress.setOnClickListener(v -> ((MainFragment) getParentFragment()).startBrotherFragment(AddressFragment.newInstance()));

        mViewPendingPayment.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(OrderFragment.newInstance(1)));
        mViewToBeDelivered.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(OrderFragment.newInstance(2)));
        mViewPendingReceipt.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(OrderFragment.newInstance(3)));
        mViewWaitingForEvaluation.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(OrderFragment.newInstance(4)));
//        mViewReturnAfterSale.setOnClickListener(v -> {});
        mImgSetting.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(SettingFragment.newInstance()));
    }

    @Override
    public void showBaseUserInfo(UserStatus.UserBean data) {
        post(() -> {
            if (data.getUsername().length() > 8) {
                mTextName.setTextSize(getResources().getDimension(R.dimen.sp_8));
            } else if (data.getUsername().length() > 4) {
                mTextName.setTextSize(getResources().getDimension(R.dimen.sp_11));
            } else {
                mTextName.setTextSize(getResources().getDimension(R.dimen.sp_14));
            }
            mTextName.setText(data.getUsername());
            mTextId.setText("ID: " + data.getUserId());
            GlideApp.with(mActivity)
                    .load(data.getAvatar())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mImgAvatar);
        });
    }

    @Override
    public void showCollectionCount(int count) {
        post(() -> mTextCollectionCount.setText(String.valueOf(count)));
    }

    @Override
    public void goToLoginPage() {
        ((MainFragment) getParentFragment()).startToAccountFragment();
    }

    @Override
    public void goToProfilePage() {
        ((MainFragment) getParentFragment()).startBrotherFragment(UserInfoFragment.newInstance());
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
