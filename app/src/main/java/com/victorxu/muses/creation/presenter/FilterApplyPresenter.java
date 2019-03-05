package com.victorxu.muses.creation.presenter;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.victorxu.muses.R;
import com.victorxu.muses.creation.contract.FilterApplyContract;
import com.victorxu.muses.creation.model.FilterApplyModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

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
    public void loadDataToView() {

    }

    @Override
    public void uploadData(Uri uri) {
        mModel.uploadImageData(uri, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: uploadImageData");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String data = response.body().string();
                    JSONObject jsonObject = new JSONObject(data);
                    if (!TextUtils.isEmpty(jsonObject.getString("image"))) {
                        mView.showImage(jsonObject.getString("image"));
                    } else {
                        mView.showToast(R.string.data_error_please_try_again);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
