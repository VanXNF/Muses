package com.victorxu.muses.trade.view.adapter;

import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.gson.Commodity;
import com.victorxu.muses.trade.view.entity.StyleSelectItem;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class StyleSelectAdapter extends BaseMultiItemQuickAdapter<StyleSelectItem, BaseViewHolder> {

    private static final String TAG = "StyleSelectAdapter";

    private OnTagItemClickListener onTagItemClickListener;
    private onNumberSelectListener onNumberSelectListener;

    public StyleSelectAdapter(@Nullable List<StyleSelectItem> data) {
        super(data);
        addItemType(StyleSelectItem.NUM, R.layout.item_select_number);
        addItemType(StyleSelectItem.STYLE, R.layout.item_style_select);
    }

    @Override
    protected void convert(BaseViewHolder helper, StyleSelectItem item) {
        switch (item.getItemType()) {
            case StyleSelectItem.STYLE:
                helper.setText(R.id.item_style_name, item.getAttribute().getName());
                TagFlowLayout flowLayout = helper.getView(R.id.item_style_tag_layout);
                flowLayout.setAdapter(new TagAdapter<Commodity.CommodityDetail.AttributesBean.ParametersBean>(item.getAttribute().getParameters()) {
                    @Override
                    public View getView(FlowLayout parent, int position, Commodity.CommodityDetail.AttributesBean.ParametersBean parametersBean) {
                        AppCompatTextView tag = (AppCompatTextView) LayoutInflater.from(mContext).inflate(R.layout.item_tag_rectangle, parent, false);
                        tag.setText(parametersBean.getValue());
                        return tag;
                    }

                    @Override
                    public void onSelected(int position, View view) {
                        super.onSelected(position, view);
                        ((AppCompatTextView) view).setTextColor(mContext.getResources().getColor(R.color.dark_red));
                    }

                    @Override
                    public void unSelected(int position, View view) {
                        super.unSelected(position, view);
                        ((AppCompatTextView) view).setTextColor(mContext.getResources().getColor(R.color.black));
                    }
                });
                flowLayout.setOnTagClickListener((View view, int position, FlowLayout parent) -> {
                    Set<Integer> pos = flowLayout.getSelectedList();
                    onTagItemClickListener.onClick(position, item.getAttribute().getParameters().get(position), pos.contains(position));
                    return false;
                });
                break;
            case StyleSelectItem.NUM:
                AppCompatTextView textNumber = helper.getView(R.id.item_text_product_number);
                textNumber.setText(String.valueOf(item.getSelectNum()));
                helper.getView(R.id.item_image_add).setOnClickListener((v) -> {
                    int number = Integer.parseInt(textNumber.getText().toString());
                    if (number < 999) {
                        number++;
                        textNumber.setText(String.valueOf(number));
                    }
                    if (onNumberSelectListener != null) {
                        onNumberSelectListener.onClick(number);
                    }
                });
                helper.getView(R.id.item_image_remove).setOnClickListener((v) -> {
                    int number = Integer.parseInt(textNumber.getText().toString());
                    if (number > 1) {
                        number--;
                        textNumber.setText(String.valueOf(number));
                    }
                    if (onNumberSelectListener != null) {
                        onNumberSelectListener.onClick(number);
                    }
                });
                break;
        }
    }

    public void setOnTagItemClickListener(OnTagItemClickListener onTagItemClickListener) {
        this.onTagItemClickListener = onTagItemClickListener;
    }

    public void setOnNumberSelectListener(StyleSelectAdapter.onNumberSelectListener onNumberSelectListener) {
        this.onNumberSelectListener = onNumberSelectListener;
    }

    public interface OnTagItemClickListener {
        void onClick(int index, Commodity.CommodityDetail.AttributesBean.ParametersBean parameter, boolean isSelected);
    }

    public interface onNumberSelectListener {
        void onClick(int number);
    }
}
