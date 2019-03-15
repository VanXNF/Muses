package com.victorxu.muses.creation.view.adapter;

import android.util.Base64;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.UnfinishedFilterStatus;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class UnFinishedFilterAdapter extends BaseQuickAdapter<UnfinishedFilterStatus.UnfinishedFilterBean, BaseViewHolder> {

    public UnFinishedFilterAdapter(@Nullable List<UnfinishedFilterStatus.UnfinishedFilterBean> data) {
        super(R.layout.item_filter_unfinished, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnfinishedFilterStatus.UnfinishedFilterBean item) {
        helper.setText(R.id.item_my_filter_name, item.getName())
                .setText(R.id.item_my_filter_text_progress, String.valueOf((int) item.getSchedule()) + "%");

        GlideApp.with(mContext)
                .load(Base64.decode(item.getStyleTemplate(), Base64.DEFAULT))
                .apply(RequestOptions.centerCropTransform())
                .into((AppCompatImageView) helper.getView(R.id.item_my_filter_image));

        AppCompatTextView textStatus = helper.getView(R.id.item_my_filter_text_status);
        switch (item.getState()) {
            //已添加
            case 1:
                textStatus.setText(R.string.already_add);
                break;
            //排队中
            case 2:
                textStatus.setText(R.string.waiting_in_line);
                break;
            //训练中
            case 3:
                textStatus.setText(R.string.training);
                break;
        }
    }
}
