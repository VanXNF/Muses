package com.victorxu.muses.creation.presenter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.FilterCreateContract;
import com.victorxu.muses.creation.model.FilterCreateModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FilterCreatePresenter implements FilterCreateContract.Presenter {

    private static final String TAG = "FilterCreatePresenter";

    private FilterCreateContract.View mView;
    private FilterCreateContract.Model mModel;

    public FilterCreatePresenter(FilterCreateContract.View mView, Context context) {
        this.mView = mView;
        mModel = new FilterCreateModel(context);
    }

    @Override
    public void loadRootView(View view) {
        mView.initRootView(view);
    }

    @Override
    public void loadListener() {
        mView.initListener();
    }

    @Override
    public void updateImageUri(Uri uri) {
        mModel.setFilterUri(uri);
        mView.showImage(uri.getPath());
    }

    @Override
    public void uploadFilter(String filterName, int brushSize, int brushIntensity, int smooth) {
        if (mModel.getFilterUri() != null) {
            if (!TextUtils.isEmpty(filterName)) {
                mModel.uploadFilter(filterName, brushSize, brushIntensity, smooth, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: uploadFilter");
                        mView.showToast(R.string.network_error_please_try_again);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        int code = response.code();
                        if (code == 200) {
                            mView.showToast(R.string.upload_success_please_check_it_in_my_filter_page);
                            mView.quit();
                        } else {
                            mView.showToast(R.string.upload_failed_please_try_again);
                        }
                    }
                });
            } else {
                mView.showToast(R.string.please_input_filter_name_first);
            }
        } else {
            mView.showPicker();
        }

    }

    @Override
    public void destroy() {
        mView = null;
        if (mModel != null) {
            mModel.cancelTask();
            mModel = null;
        }
    }
}
