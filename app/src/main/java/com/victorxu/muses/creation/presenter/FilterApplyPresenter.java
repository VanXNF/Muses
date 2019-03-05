package com.victorxu.muses.creation.presenter;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.creation.model.FilterApplyModel;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FilterApplyPresenter implements FilterApplyContract.Presenter {

    private static final String TAG = "FilterApplyPresenter";

    private FilterApplyContract.View mView;
    private FilterApplyContract.Model mModel;

    public FilterApplyPresenter(FilterApplyContract.View mView, int filterId) {
        this.mView = mView;
        mModel = new FilterApplyModel(filterId);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void setListener() {
        mView.initListener();
    }

    @Override
    public void saveData() {
        if (!TextUtils.isEmpty(mModel.getFilterUrl())) {
            mView.saveFilterImage();
        } else {
            mView.showToast(R.string.please_choose_pic_first);
        }
    }

    @Override
    public void uploadData(Uri uri) {
        mView.showFilterImage(uri.getPath());
        mView.showLoading();
        mModel.uploadImageData(uri, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: uploadImageData");
                mView.hideLoading();
                mView.showToast(R.string.network_error_please_try_again);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String data = response.body().string();
                    JSONObject jsonObject = new JSONObject(data);
                    String url = jsonObject.getString("image");
                    if (!TextUtils.isEmpty(url)) {
                        mModel.setFilterUrl(url);
                        mView.showFilterImage(url);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mView.showToast(R.string.data_error_please_try_again);
                } finally {
                    mView.hideLoading();
                }
            }
        });
    }
}
