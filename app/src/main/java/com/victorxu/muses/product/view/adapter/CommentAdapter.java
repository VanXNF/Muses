package com.victorxu.muses.product.view.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.victorxu.muses.R;
import com.victorxu.muses.custom.AdvancedImageView;
import com.victorxu.muses.product.view.entity.EvaluationItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class CommentAdapter extends BaseQuickAdapter <EvaluationItem, BaseViewHolder> {

    public CommentAdapter(@Nullable List<EvaluationItem> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluationItem item) {
        helper.setText(R.id.product_comment_text_username, item.getUserName())
                .setText(R.id.product_comment_text_comment_date, item.getCommentDate())
                .setText(R.id.product_comment_text_like_num, item.getLikeNum())
                .setText(R.id.product_comment_text_comment_num, item.getCommentNum())
                .setText(R.id.product_comment_text_comment, item.getComment())
                .setText(R.id.product_comment_text_model, item.getType());

        ((SimpleRatingBar) helper.getView(R.id.product_comment_rating_bar)).setRating(item.getRank());

        Glide.with(mContext)
                .load(item.getAvatarUrl())
                .into((AdvancedImageView) helper.getView(R.id.product_comment_image_avatar));

        if (item.getCommentImages() == null || item.getCommentImages().size() == 0) {
            helper.getView(R.id.product_comment_relative_image_container).setVisibility(View.GONE);
        } else {
            if (item.getCommentImages().size() <= 3) {
                helper.getView(R.id.product_comment_image_comment_image_4).setVisibility(View.GONE);
                helper.getView(R.id.product_comment_image_comment_image_5).setVisibility(View.GONE);
                helper.getView(R.id.product_comment_image_comment_image_6).setVisibility(View.GONE);
            }
            ArrayList<AdvancedImageView> images = new ArrayList<>();
            images.add(helper.getView(R.id.product_comment_image_comment_image_1));
            images.add(helper.getView(R.id.product_comment_image_comment_image_2));
            images.add(helper.getView(R.id.product_comment_image_comment_image_3));
            images.add(helper.getView(R.id.product_comment_image_comment_image_4));
            images.add(helper.getView(R.id.product_comment_image_comment_image_5));
            images.add(helper.getView(R.id.product_comment_image_comment_image_6));

            for (int i = 0; i < item.getCommentImages().size(); ++i) {
                Glide.with(mContext)
                        .load(item.getCommentImages().get(i))
                        .into(images.get(i));
            }
        }



    }
}
