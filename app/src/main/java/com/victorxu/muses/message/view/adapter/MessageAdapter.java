package com.victorxu.muses.message.view.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.message.view.entity.ListMessageItem;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class MessageAdapter extends BaseQuickAdapter<ListMessageItem, BaseViewHolder> {

    public MessageAdapter(@Nullable List<ListMessageItem> data) {
        super(R.layout.item_list_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListMessageItem item) {
        helper.setText(R.id.list_message_text_username, item.getUserName())
                .setText(R.id.list_message_text_message_content, item.getMessageContent())
                .setText(R.id.list_message_text_time, item.getMessageTime());

        if (item.getMessageCount() <= 0) {
            helper.getView(R.id.list_message_text_message_num).setVisibility(View.INVISIBLE);
        } else if (item.getMessageCount() > 99){
            helper.setText(R.id.list_message_text_message_num, "99+");
        } else {
            helper.setText(R.id.list_message_text_message_num, String.valueOf(item.getMessageCount()));
        }

        Glide.with(mContext).load(item.getImageUrl())
                .into((AppCompatImageView) helper.getView(R.id.list_message_image_avatar));
    }
}
