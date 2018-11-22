package com.victorxu.muses.product.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.product.view.entity.EvaluationItem;

import java.util.List;

import androidx.annotation.Nullable;

public class CommentAdapter extends BaseQuickAdapter <EvaluationItem, BaseViewHolder> {

    public CommentAdapter(@Nullable List<EvaluationItem> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluationItem item) {

    }
}
