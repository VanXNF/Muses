package com.victorxu.muses.gallery.view.adapter;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.gallery.view.entity.TicketItem;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

public class TicketAdapter extends BaseQuickAdapter<TicketItem, BaseViewHolder> {

    public TicketAdapter(@Nullable List<TicketItem> data) {
        super(R.layout.item_ticket, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TicketItem item) {
        helper.setText(R.id.ticket_text_percentage, String.valueOf(item.getPercentage()))
                .setText(R.id.ticket_text_sub_desc, item.getSubDesc())
                .setText(R.id.ticket_text_desc, item.getDesc())
                .setText(R.id.ticket_text_score, String.valueOf(item.getScore()));

        ((ProgressBar) helper.getView(R.id.ticket_progressbar)).setProgress(item.getPercentage());

        if (item.getPercentage() == 100) {
            ((AppCompatTextView) helper.getView(R.id.ticket_text_score)).setTextColor(mContext.getResources().getColor(R.color.light_gray));
            ((AppCompatTextView) helper.getView(R.id.ticket_text_score_unit)).setTextColor(mContext.getResources().getColor(R.color.light_gray));
            ((AppCompatTextView) helper.getView(R.id.ticket_text_redeem)).setTextColor(mContext.getResources().getColor(R.color.light_gray));
//            ColorStateList lists = mContext.getResources().getColorStateList(R.color.light_gray);
//            ((AppCompatImageView) helper.getView(R.id.ticket_image_redeem)).setImageTintMode(PorterDuff.Mode.OVERLAY);
            ((AppCompatImageView) helper.getView(R.id.ticket_image_redeem)).setVisibility(View.INVISIBLE);
            ((AppCompatTextView) helper.getView(R.id.ticket_text_redeem)).setText(mContext.getResources().getText(R.string.redeem_finished));
        }
    }
}
