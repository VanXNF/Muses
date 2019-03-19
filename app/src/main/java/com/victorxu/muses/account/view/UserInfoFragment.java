package com.victorxu.muses.account.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.MainActivity;
import com.victorxu.muses.R;
import com.victorxu.muses.account.contract.InfoContract;
import com.victorxu.muses.account.presenter.InfoPresenter;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class UserInfoFragment extends BaseSwipeBackFragment implements InfoContract.View {

    private AppCompatImageView mImgBack;
    private AppCompatImageView mImgAvatar;
    private View mViewAvatar;
    private AppCompatTextView mTxtUserName;
    private View mViewUserName;
    private AppCompatTextView mTxtUpdatePwd;
    private View mViewUpdatePwd;
    private AppCompatTextView mTxtNickname;
    private View mViewNickname;
    private AppCompatTextView mTxtGender;
    private View mViewGender;
    private AppCompatTextView mTxtBirthDay;
    private View mViewBirthday;
    private AppCompatTextView mTxtEmail;
    private View mViewEmail;
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
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        mPresenter = null;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mPresenter.loadDataToView();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.user_info_page_bar;
    }

    @Override
    public void initRootView(View view) {
        mImgBack = view.findViewById(R.id.user_info_image_back);
        mImgAvatar = view.findViewById(R.id.user_info_image_avatar);
        mViewAvatar = view.findViewById(R.id.user_info_line_avatar);
        mTxtUserName = view.findViewById(R.id.user_info_text_username);
        mViewUserName = view.findViewById(R.id.user_info_line_username);
        mTxtUpdatePwd = view.findViewById(R.id.user_info_text_update_pwd);
        mViewUpdatePwd = view.findViewById(R.id.user_info_line_update_pwd);
        mTxtNickname = view.findViewById(R.id.user_info_text_nickname);
        mViewNickname = view.findViewById(R.id.user_info_line_nickname);
        mTxtGender = view.findViewById(R.id.user_info_text_gender);
        mViewGender = view.findViewById(R.id.user_info_line_gender);
        mTxtBirthDay = view.findViewById(R.id.user_info_text_birthday);
        mViewBirthday = view.findViewById(R.id.user_info_line_birthday);
        mTxtEmail = view.findViewById(R.id.user_info_text_email);
        mViewEmail = view.findViewById(R.id.user_info_line_email);
        mTxtQuit = view.findViewById(R.id.user_info_text_quit);

        mImgBack.setOnClickListener(v -> mActivity.onBackPressed());
        mViewUpdatePwd.setOnClickListener(v -> showPasswordDialog());
        mViewNickname.setOnClickListener(v -> showNicknameDialog());
        mViewGender.setOnClickListener(v -> showSexChooseDialog());
        mViewBirthday.setOnClickListener(v -> showDateChooseDialog());
        mViewEmail.setOnClickListener(v -> showEmailDialog());
        mTxtQuit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_base, null);
            AppCompatTextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
            AppCompatTextView dialogConfirm = dialogView.findViewById(R.id.dialog_confirm);
            AppCompatTextView dialogCancel = dialogView.findViewById(R.id.dialog_cancel);
            dialogTitle.setText(R.string.are_you_sure_to_quit);
            builder.setView(dialogView);
            builder.setCancelable(false);
            AlertDialog quitDialog = builder.create();
            Window window = quitDialog.getWindow();
            window.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangle_all_bg, null));

            dialogConfirm.setOnClickListener(vi -> {
                quitDialog.dismiss();
                mPresenter.quit();
                LoginByPWDFragment fragment = LoginByPWDFragment.newInstance();
                fragment.addLoginListener((MainActivity) getActivity());
                startWithPop(fragment);
            });
            dialogCancel.setOnClickListener(vi -> quitDialog.dismiss());
            quitDialog.show();
        });
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
    public void showNickname(String nickname) {
        mUserData.setNickname(nickname);
        post(() -> mTxtNickname.setText(nickname));
    }

    @Override
    public void showBirthday(long timestamp) {
        mUserData.setBirthday(timestamp);
        post(() -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            mTxtBirthDay.setText(sdf.format(new Date(timestamp)));
        });
    }

    @Override
    public void showGender(int gender) {
        mUserData.setGender(gender);
        post(() -> {
            switch (gender) {
                case 0:
                    mTxtGender.setText("女");
                    break;
                case 1:
                    mTxtGender.setText("男");
                    break;
            }
        });
    }

    @Override
    public void showEmail(String email) {
        mUserData.setEmail(email);
        if (!TextUtils.isEmpty(email)) {
            post(() -> mTxtEmail.setText(email));
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    private void showPasswordDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View passwordView = layoutInflater.inflate(R.layout.dialog_password, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setView(passwordView);

        AppCompatEditText editOldPassword = passwordView.findViewById(R.id.dialog_password_old);
        AppCompatEditText editNewPassword = passwordView.findViewById(R.id.dialog_password_new);
        AppCompatTextView txtConfirm = passwordView.findViewById(R.id.dialog_password_confirm);
        AppCompatTextView txtCancel = passwordView.findViewById(R.id.dialog_password_cancel);

        builder.setTitle(getString(R.string.update_password))
                .setCancelable(true);

        AlertDialog alertDialog = builder.create();
        txtConfirm.setOnClickListener(v -> {
            String oldPwd = editOldPassword.getText().toString();
            String newPwd = editNewPassword.getText().toString();
            int code = 0;
            if (TextUtils.isEmpty(oldPwd)) {
                code = 1;
            }
            if (TextUtils.isEmpty(newPwd)) {
                code = 2;
            }
            switch (code) {
                case 0:
                    mPresenter.updatePassword(oldPwd, newPwd);
                    alertDialog.dismiss();
                    break;
                case 1:
                    showToast(R.string.please_input_old_password);
                    break;
                case 2:
                    showToast(R.string.please_input_new_password);
                    break;
            }
        });
        txtCancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    private void showNicknameDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View emailView = layoutInflater.inflate(R.layout.dialog_nickname, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        alertDialogBuilder.setView(emailView);

        AppCompatEditText editNickname = emailView.findViewById(R.id.dialog_nickname);
        AppCompatTextView txtConfirm = emailView.findViewById(R.id.dialog_nickname_confirm);
        AppCompatTextView txtCancel = emailView.findViewById(R.id.dialog_nickname_cancel);
        editNickname.setHint(mTxtNickname.getText());
//        设置过滤器，只能输入中文、英文和数字，最长为8位
        InputFilter typeFilter = (CharSequence source, int start, int end, Spanned dest, int dstart, int dend) -> {
            Pattern p = Pattern.compile("[a-zA-Z|\u4e00-\u9fa5|0-9]+");
            Matcher m = p.matcher(source.toString());
            if (!m.matches()) return "";
            return null;
        };
        editNickname.setFilters(new InputFilter[]{typeFilter, new InputFilter.LengthFilter(8)});

        alertDialogBuilder
                .setTitle(getString(R.string.nickname) +
                        " (" + getString(R.string.chinese_character_english_character_only) + ")")
                .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        txtConfirm.setOnClickListener(v -> {
            String nickname = editNickname.getText().toString();
            if (!TextUtils.isEmpty(nickname)) {
                mPresenter.updateNickname(nickname);
                alertDialog.dismiss();
            } else {
                showToast(R.string.nickname_can_not_be_null);
            }
        });
        txtCancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    private void showSexChooseDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View sexView = layoutInflater.inflate(R.layout.dialog_sex, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        alertDialogBuilder.setView(sexView);

        AppCompatImageView imgWoman = sexView.findViewById(R.id.dialog_sex_woman);
        AppCompatImageView imgMan = sexView.findViewById(R.id.dialog_sex_man);

        if (mTxtGender.getText().toString().equals("男")) {
            imgWoman.setImageResource(R.drawable.ic_woman);
            imgMan.setImageResource(R.drawable.ic_man_x);
        } else {
            imgWoman.setImageResource(R.drawable.ic_woman_x);
            imgMan.setImageResource(R.drawable.ic_man);
        }
        alertDialogBuilder
                .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();

        imgWoman.setOnClickListener(v -> {
            mPresenter.updateGender(0);
            alertDialog.dismiss();
        });
        imgMan.setOnClickListener(v -> {
            mPresenter.updateGender(1);
            alertDialog.dismiss();
        });
        alertDialog.show();
    }

    private void showDateChooseDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mActivity,
                (DatePicker vi, int year, int month, int dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    mPresenter.updateBirthday(calendar.getTimeInMillis());
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void showEmailDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View emailView = layoutInflater.inflate(R.layout.dialog_email, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        alertDialogBuilder.setView(emailView);

        AppCompatEditText editEmail = emailView.findViewById(R.id.dialog_email);
        AppCompatTextView txtConfirm = emailView.findViewById(R.id.dialog_email_confirm);
        AppCompatTextView txtCancel = emailView.findViewById(R.id.dialog_email_cancel);
        editEmail.setHint(mTxtEmail.getText());

        alertDialogBuilder
                .setTitle(getString(R.string.email))
                .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        txtConfirm.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String pattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
            if (!TextUtils.isEmpty(email)) {
                if (email.matches(pattern)) {
                    mPresenter.updateEmail(email);
                    alertDialog.dismiss();
                } else {
                    showToast("请输入正确的邮箱格式");
                }
            } else {
                mTxtEmail.setText(R.string.add);
                mPresenter.updateEmail("");
                alertDialog.dismiss();
            }
        });
        txtCancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }
}
