package com.victorxu.muses.product.view.adapter;

import android.annotation.SuppressLint;
import android.view.View;

import com.bumptech.glide.request.RequestOptions;
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
import androidx.appcompat.widget.AppCompatRatingBar;

public class CommentAdapter extends BaseQuickAdapter <PageComment.PageCommentData.CommentBean, BaseViewHolder> {

    public CommentAdapter(@Nullable List<PageComment.PageCommentData.CommentBean> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageComment.PageCommentData.CommentBean item) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        helper.setText(R.id.product_comment_text_username, item.getUsername())
                .setText(R.id.product_comment_text_comment_date, sdf.format(new Date(item.getDate())))
                .setText(R.id.product_comment_text_like_num, String.valueOf(item.getPraise()))
//                .setText(R.id.product_comment_text_comment_num, item.getCommentNum())
                .setText(R.id.product_comment_text_comment, item.getContent())
                .setText(R.id.product_comment_text_model, item.getCommodityInfo().split(" ")[0]);

        ((AppCompatRatingBar) helper.getView(R.id.product_comment_rating_bar)).setRating(item.getStar());

        GlideApp.with(mContext)
                .load(item.getHead())
                .apply(new RequestOptions().circleCrop())
                .into((AppCompatImageView) helper.getView(R.id.product_comment_image_avatar));

        if (item.getImages() == null || item.getImages().size() == 0) {
            helper.getView(R.id.product_comment_relative_image_container).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.product_comment_relative_image_container).setVisibility(View.VISIBLE);
            ArrayList<AppCompatImageView> images = new ArrayList<>();
            images.add(helper.getView(R.id.product_comment_image_comment_image_1));
            images.add(helper.getView(R.id.product_comment_image_comment_image_2));
            images.add(helper.getView(R.id.product_comment_image_comment_image_3));
            images.add(helper.getView(R.id.product_comment_image_comment_image_4));
            images.add(helper.getView(R.id.product_comment_image_comment_image_5));
            images.add(helper.getView(R.id.product_comment_image_comment_image_6));

            if (item.getImages().size() <= 3) {
                for (int i = 3; i < 6; i++) {
                    images.get(i).setVisibility(View.GONE);
                }
            } else {
                for (int i = 3; i < 6; i++) {
                    images.get(i).setVisibility(View.VISIBLE);
                }
            }

            int size = item.getImages().size() > images.size() ? images.size() : item.getImages().size();
            for (int i = 0; i < size; ++i) {
                GlideApp.with(mContext)
                        .load(item.getImages().get(i))
                        .apply(new RequestOptions().centerCrop())
                        .into(images.get(i));
                images.get(i).setVisibility(View.VISIBLE);
            }
        }
    }
}
