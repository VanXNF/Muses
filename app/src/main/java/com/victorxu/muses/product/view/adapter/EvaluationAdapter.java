package com.victorxu.muses.product.view.adapter;


import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;

import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.PageComment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class EvaluationAdapter extends BaseQuickAdapter<PageComment.PageCommentData.CommentModel, BaseViewHolder> {

    public EvaluationAdapter(@Nullable List<PageComment.PageCommentData.CommentModel> data) {
        super(R.layout.item_evaluation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageComment.PageCommentData.CommentModel item) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        helper.setText(R.id.product_evaluation_text_username, item.getUsername())
                .setText(R.id.product_evaluation_text_comment_date, sdf.format(new Date(item.getDate())))
                .setText(R.id.product_evaluation_text_order_info, item.getCommodityInfo().split(" ")[0])
                .setText(R.id.product_evaluation_text_comment, item.getContent());

        GlideApp.with(mContext)
                .load(item.getHead())
                .apply(RequestOptions.circleCropTransform())
                .into((AppCompatImageView) helper.getView(R.id.product_evaluation_image_avatar));

        List<AppCompatImageView> imageViews = new ArrayList<>();
        imageViews.add(helper.getView(R.id.product_evaluation_image_comment_image_1));
        imageViews.add(helper.getView(R.id.product_evaluation_image_comment_image_2));
        imageViews.add(helper.getView(R.id.product_evaluation_image_comment_image_3));

        int num = item.getImages().size() > 3 ? 3 : item.getImages().size();
        for (int i = 0; i < num; i++) {
            AppCompatImageView imageView = imageViews.get(i);
            GlideApp.with(mContext)
                    .load(item.getImages().get(i))
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageViews.get(i));
        }


    }
}
