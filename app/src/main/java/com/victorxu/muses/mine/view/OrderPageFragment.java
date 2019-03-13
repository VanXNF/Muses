package com.victorxu.muses.mine.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.custom.AdvancedBottomSheetDialog;
import com.victorxu.muses.gson.PageOrderStatus;
import com.victorxu.muses.mine.contract.OrderContract;
import com.victorxu.muses.mine.presenter.OrderPresenter;
import com.victorxu.muses.mine.view.adapter.OrderAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderPageFragment extends BaseFragment implements OrderContract.View {

    private SmartRefreshLayout mRefreshLayoutOrder;
    private RecyclerView mRecyclerOrder;
    private AdvancedBottomSheetDialog mBottomSheetDialog;
    private View mViewEmpty;
    private AppCompatTextView mTextOrderSN;
    private AppCompatTextView mTextOrderTime;
    private AppCompatTextView mTextOrderPrice;
    private AppCompatTextView mTextOrderSigner;
    private AppCompatTextView mTextOrderPhone;
    private AppCompatButton mBtnPayOrder;

    private OrderPresenter mPresenterOrder;

    private OrderAdapter mAdapterOrder;
    private List<PageOrderStatus.PageOrder.OrderBean> mOrderData = new ArrayList<>();

    private int type;

    private boolean isPay = false;

    public static OrderPageFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        OrderPageFragment fragment = new OrderPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("TYPE");
        } else {
            type = 0;
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenterOrder.loadDataToView();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void initRootView(View view) {
        mRefreshLayoutOrder = view.findViewById(R.id.order_page_refresh_layout);
        mRecyclerOrder = view.findViewById(R.id.order_page_recycler);
        mViewEmpty = view.findViewById(R.id.order_page_empty_view);

        mAdapterOrder = new OrderAdapter(mOrderData);
        mAdapterOrder.setOnItemChildClickListener((BaseQuickAdapter adapter, View v, int position) -> {
            int viewId = v.getId();
            switch (viewId) {
                case R.id.item_order_btn_cancel_order:
                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage(getString(R.string.sure_to_cancel_order))
                            .setPositiveButton(getString(R.string.confirm), (DialogInterface dialog, int id) -> mPresenterOrder.cancelOrder(position))
                            .setNegativeButton(getString(R.string.cancel), (DialogInterface dialog, int id) -> {
                            });
                    builder.show();
                    break;
                case R.id.item_order_btn_check_order:
                    showToast("暂未开放");
                    break;
                case R.id.item_order_btn_confirm_receipt:
                    showToast("暂未开放");
                    break;
                case R.id.item_order_btn_evaluation:
                    showToast("暂未开放");
                    break;
                case R.id.item_order_btn_pay:
                    showPaySheet(position);
                    break;
            }
        });
        mAdapterOrder.setOnLoadMoreListener(() -> mPresenterOrder.loadMoreDataToView(), mRecyclerOrder);
        mRecyclerOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerOrder.setAdapter(mAdapterOrder);

        mRefreshLayoutOrder.setOnRefreshListener((@NonNull RefreshLayout refreshLayout) -> mPresenterOrder.reloadDataToView());
        mRefreshLayoutOrder.setEnableAutoLoadMore(false);
        mRefreshLayoutOrder.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayoutOrder.setRefreshHeader(new MaterialHeader(mActivity));

        initPayBottomSheet();
    }

    @Override
    public void showOrder(List<PageOrderStatus.PageOrder.OrderBean> data) {
        mOrderData.clear();
        mOrderData.addAll(data);
        post(() -> {
            mAdapterOrder.setNewData(mOrderData);
            mAdapterOrder.notifyDataSetChanged();
        });
    }

    @Override
    public void showMoreOrder(List<PageOrderStatus.PageOrder.OrderBean> data) {
        mOrderData.addAll(data);
        post(() -> {
            mAdapterOrder.addData(data);
            mAdapterOrder.notifyDataSetChanged();
        });
    }

    @Override
    public void showLoading() {
        post(() -> mRefreshLayoutOrder.autoRefreshAnimationOnly());
    }

    @Override
    public void hideLoading() {
        post(() -> mRefreshLayoutOrder.finishRefresh());
    }

    @Override
    public void showLoadMore() {

    }

    @Override
    public void hideLoadMore(boolean isCompleted, boolean isEnd) {
        post(() -> {
            if (isEnd) {
                mAdapterOrder.loadMoreEnd();
            } else {
                if (isCompleted) {
                    mAdapterOrder.loadMoreComplete();
                } else {
                    mAdapterOrder.loadMoreFail();
                }
            }
        });
    }

    @Override
    public void showEmptyPage() {
        post(() -> mViewEmpty.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideEmptyPage() {
        post(() -> mViewEmpty.setVisibility(View.GONE));
    }

    @Override
    public void showPaySheet(int position) {
        isPay = false;
        post(() -> {
            mTextOrderSN.setText(mOrderData.get(position).getOrderSN());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mTextOrderTime.setText(sdf.format(new Date(mOrderData.get(position).getAddTime())));
            String data = mOrderData.get(position).getAddress();
            String info[] = data.split(";");
            mTextOrderSigner.setText(info[1]);
            mTextOrderPhone.setText(info[2]);
            mTextOrderPrice.setText(String.valueOf(mOrderData.get(position).getOrderAmount()));

            mBtnPayOrder.setOnClickListener(v -> {
                mPresenterOrder.payOrder(position);
                isPay = true;
            });
            mBottomSheetDialog.show();
        });
    }

    @Override
    public void hidePaySheet() {
        mBottomSheetDialog.dismiss();
    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(() -> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    private void initPayBottomSheet() {
        mBottomSheetDialog = new AdvancedBottomSheetDialog(mActivity, 0.8f, 0.8f);
        View mViewPay = getLayoutInflater().inflate(R.layout.bottom_pay, null);
        mTextOrderSN = mViewPay.findViewById(R.id.pay_order_text_sn);
        mTextOrderTime = mViewPay.findViewById(R.id.pay_order_text_time);
        mTextOrderSigner = mViewPay.findViewById(R.id.pay_order_text_signer_person);
        mTextOrderPhone = mViewPay.findViewById(R.id.pay_order_text_phone);
        mTextOrderPrice = mViewPay.findViewById(R.id.pay_order_text_price);
        mBtnPayOrder = mViewPay.findViewById(R.id.pay_order_button_pay);
        mBottomSheetDialog.setContentView(mViewPay);
        mBottomSheetDialog.setOnDismissListener((DialogInterface dialog) -> {
            if (!isPay) {
                showToast(R.string.order_do_not_finish);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_order, container, false);
        mPresenterOrder = new OrderPresenter(this, type, mActivity);
        mPresenterOrder.loadRootView(view);
        return view;
    }
}
