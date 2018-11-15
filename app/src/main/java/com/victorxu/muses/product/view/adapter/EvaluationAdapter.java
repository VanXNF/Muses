package com.victorxu.muses.product.view.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.custom.AdvancedImageView;
import com.victorxu.muses.product.view.entity.EvaluationItem;

import java.util.List;

import androidx.annotation.Nullable;

public class EvaluationAdapter extends BaseQuickAdapter<EvaluationItem, BaseViewHolder> {

    public EvaluationAdapter(@Nullable List<EvaluationItem> data) {
        super(R.layout.item_evaluation , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluationItem item) {
        helper.setText(R.id.product_index_evaluation_text_username, item.getUserName())
            .setText(R.id.product_index_evaluation_text_comment_date, item.getCommentDate())
            .setText(R.id.product_index_evaluation_text_like_num, item.getLikeNum())
            .setText(R.id.product_index_evaluation_text_comment_num, item.getCommentNum())
            .setText(R.id.product_index_evaluation_text_comment, item.getComment());

        Glide.with(mContext)
                .load(item.getAvatarUrl())
                .into((AdvancedImageView) helper.getView(R.id.product_index_evaluation_image_avatar));

        Glide.with(mContext)
                .load(item.getCommentImage1())
                .into((AdvancedImageView) helper.getView(R.id.product_index_evaluation_image_comment_image_1));

        Glide.with(mContext)
                .load(item.getCommentImage2())
                .into((AdvancedImageView) helper.getView(R.id.product_index_evaluation_image_comment_image_2));

        Glide.with(mContext)
                .load(item.getCommentImage3())
                .into((AdvancedImageView) helper.getView(R.id.product_index_evaluation_image_comment_image_3));
    }
}
