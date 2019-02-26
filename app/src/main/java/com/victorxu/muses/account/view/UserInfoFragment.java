package com.victorxu.muses.account.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.InfoContract;
import com.victorxu.muses.account.presenter.InfoPresenter;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class UserInfoFragment extends BaseSwipeBackFragment implements InfoContract.View {

    private AppCompatImageView mImgBack;
    private AppCompatImageView mImgAvatar;
    private AppCompatTextView mTxtUserName;
    private AppCompatTextView mTxtUpdatePwd;
    private AppCompatTextView mTxtNickname;
    private AppCompatTextView mTxtGender;
    private AppCompatTextView mTxtBirthDay;
    private AppCompatTextView mTxtEmail;
    private AppCompatTextView mTxtQuit;

    private UserInfo.UserInfoBean mUserData;
    private InfoPresenter mPresenter;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        mPresenter = new InfoPresenter(this, mActivity);
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
        mImgBack = view.findViewById(R.id.user_info_image_back);
        mImgAvatar = view.findViewById(R.id.user_info_image_avatar);
        mTxtUserName = view.findViewById(R.id.user_info_text_username);
        mTxtUpdatePwd = view.findViewById(R.id.user_info_text_update_pwd);
        mTxtNickname = view.findViewById(R.id.user_info_text_nickname);
        mTxtGender = view.findViewById(R.id.user_info_text_gender);
        mTxtBirthDay = view.findViewById(R.id.user_info_text_birthday);
        mTxtEmail = view.findViewById(R.id.user_info_text_email);
        mTxtQuit = view.findViewById(R.id.user_info_text_quit);

        mImgBack.setOnClickListener(v -> mActivity.onBackPressed());
//        mTxtUpdatePwd.setOnClickListener(v -> );
        mTxtBirthDay.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
//            mTvDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
            DatePickerDialog dialog = new DatePickerDialog(mActivity,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            mTxtBirthDay.setText(sdf.format(calendar.getTime()));
//                            mTvDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
                        }
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
        mTxtQuit.setOnClickListener(v -> mPresenter.quit());
    }

    @Override
    public void showUserInfo(UserInfo.UserInfoBean data) {
        mUserData = data;
        post(() -> {
            GlideApp.with(mActivity)
                    .load(data.getAvatar())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mImgAvatar);
            if (!TextUtils.isEmpty(mUserData.getUsername())) {
                mTxtUserName.setText(mUserData.getUsername());
            }
            if (!TextUtils.isEmpty(mUserData.getNickname())) {
                mTxtNickname.setText(mUserData.getNickname());
            }
            if (!TextUtils.isEmpty(String.valueOf(mUserData.getGender()))) {
                switch (mUserData.getGender()) {
                    case 0:
                        mTxtGender.setText("女");
                        break;
                    case 1:
                        mTxtGender.setText("男");
                        break;
                }
            }
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!TextUtils.isEmpty(sdf.format(new Date(mUserData.getBirthday())))) {
                mTxtBirthDay.setText(sdf.format(new Date(mUserData.getBirthday())));
            }
            if (!TextUtils.isEmpty(mUserData.getEmail())) {
                mTxtEmail.setText(mUserData.getEmail());
            }
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
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.user_info_page_bar;
    }
}
