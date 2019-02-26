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
import com.victorxu.muses.core.view.MainFragment;
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
    private View mViewCollection;

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
    public void initRootView(View view) {
        mTextName = view.findViewById(R.id.mine_text_username);
        mTextId = view.findViewById(R.id.mine_text_user_id);
        mImgAvatar = view.findViewById(R.id.mine_image_avatar);
        mTextCollectionCount = view.findViewById(R.id.mine_text_collection);
        mViewCollection = view.findViewById(R.id.mine_view_collection);

        mTextName.setOnClickListener(v -> mPresenter.goToAccount());
        mImgAvatar.setOnClickListener(v -> mPresenter.goToAccount());
        mViewCollection.setOnClickListener(v ->
                ((MainFragment) getParentFragment()).startBrotherFragment(CollectionFragment.newInstance())
        );
    }

    @Override
    public void showBaseUserInfo(UserStatus.UserBean data) {
        post(() -> {
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

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.mine_page_bar;
    }
}
