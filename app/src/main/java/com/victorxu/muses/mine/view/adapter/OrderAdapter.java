package com.victorxu.muses.mine.view.adapter;

import android.view.View;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.victorxu.muses.R;
import com.victorxu.muses.glide.GlideApp;
import com.victorxu.muses.gson.PageOrderStatus;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends BaseQuickAdapter<PageOrderStatus.PageOrder.OrderBean, BaseViewHolder> {

    public OrderAdapter(@Nullable List<PageOrderStatus.PageOrder.OrderBean> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PageOrderStatus.PageOrder.OrderBean item) {
        helper.addOnClickListener(R.id.item_order_btn_cancel_order)
                .addOnClickListener(R.id.item_order_btn_check_order)
                .addOnClickListener(R.id.item_order_btn_pay)
                .addOnClickListener(R.id.item_order_btn_confirm_receipt)
                .addOnClickListener(R.id.item_order_btn_evaluation);

        AppCompatTextView btnCancelOrder = helper.getView(R.id.item_order_btn_cancel_order);
        AppCompatTextView btnCheckOrder = helper.getView(R.id.item_order_btn_check_order);
        AppCompatTextView btnPay = helper.getView(R.id.item_order_btn_pay);
        AppCompatTextView btnConfirmReceipt = helper.getView(R.id.item_order_btn_confirm_receipt);
        AppCompatTextView btnEvaluation = helper.getView(R.id.item_order_btn_evaluation);

        RecyclerView mRecyclerCommodity = helper.getView(R.id.item_order_recycler);
        mRecyclerCommodity.setLayoutManager(new LinearLayoutManager(mContext));
        OrderCommodityAdapter adapter = new OrderCommodityAdapter(item.getOrderCommodityModels());
        mRecyclerCommodity.setAdapter(adapter);

        switch (item.getStatus()) {
            case 0:
//                待付款
                btnCancelOrder.setVisibility(View.VISIBLE);
                btnCheckOrder.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.VISIBLE);
                btnConfirmReceipt.setVisibility(View.GONE);
                btnEvaluation.setVisibility(View.GONE);
                break;
            case 1:
//                待发货
                btnCancelOrder.setVisibility(View.VISIBLE);
                btnCheckOrder.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnConfirmReceipt.setVisibility(View.GONE);
                btnEvaluation.setVisibility(View.GONE);
                break;
            case 2:
//                待收货
                btnCancelOrder.setVisibility(View.GONE);
                btnCheckOrder.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnConfirmReceipt.setVisibility(View.VISIBLE);
                btnEvaluation.setVisibility(View.GONE);
                break;
            case 3:
//                待评价
                btnCancelOrder.setVisibility(View.GONE);
                btnCheckOrder.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnConfirmReceipt.setVisibility(View.GONE);
                btnEvaluation.setVisibility(View.VISIBLE);
                break;
            case 4:
//                已评价
                btnCancelOrder.setVisibility(View.GONE);
                btnCheckOrder.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.GONE);
                btnConfirmReceipt.setVisibility(View.GONE);
                btnEvaluation.setVisibility(View.GONE);
                break;
        }

        String tip = mContext.getResources().getString(R.string.tip_prefix)
                + String.valueOf(item.getOrderCommodityModels().size())
                + mContext.getResources().getString(R.string.tip_suffix);
        helper.setText(R.id.item_order_text_tip, tip);
        int sum = 0;
        for (PageOrderStatus.PageOrder.OrderBean.OrderCommodityModelsBean data : item.getOrderCommodityModels()) {
            sum += data.getNumber() * data.getPrice();
        }
        helper.setText(R.id.item_order_text_total_price, String.valueOf(sum));
    }

    class OrderCommodityAdapter extends BaseQuickAdapter<PageOrderStatus.PageOrder.OrderBean.OrderCommodityModelsBean, BaseViewHolder> {

        OrderCommodityAdapter(@Nullable List<PageOrderStatus.PageOrder.OrderBean.OrderCommodityModelsBean> data) {
            super(R.layout.item_order_commodity, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PageOrderStatus.PageOrder.OrderBean.OrderCommodityModelsBean item) {
            helper.setText(R.id.item_order_text_title, item.getTitle())
                    .setText(R.id.item_order_text_attr, item.getDetail())
                    .setText(R.id.item_order_text_price, String.valueOf(item.getPrice()))
                    .setText(R.id.item_order_text_number, String.valueOf(item.getNumber()));

            GlideApp.with(mContext)
                    .load(item.getImage())
                    .apply(RequestOptions.centerCropTransform())
                    .into((AppCompatImageView) helper.getView(R.id.item_order_image_order));


        }
    }
}
