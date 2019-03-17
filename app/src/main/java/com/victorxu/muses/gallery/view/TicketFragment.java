package com.victorxu.muses.gallery.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.gallery.view.adapter.TicketAdapter;
import com.victorxu.muses.gallery.view.entity.TicketItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TicketFragment extends BaseSwipeBackFragment {

    private TicketAdapter mTicketAdapter;
    private RecyclerView mTicketRecycler;
    private AppCompatImageView mImageBack;

    public static TicketFragment newInstance() {
        Bundle bundle = new Bundle();
        TicketFragment fragment = new TicketFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mTicketRecycler = view.findViewById(R.id.ticket_recycler_view);
        mTicketRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mTicketAdapter = new TicketAdapter(initTestTicketData());
        mTicketRecycler.setAdapter(mTicketAdapter);
        mImageBack = view.findViewById(R.id.ticket_image_back);
        mImageBack.setOnClickListener((v) -> mActivity.onBackPressed());
    }

    private ArrayList<TicketItem> initTestTicketData() {
        ArrayList<TicketItem> ticketItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ticketItems.add(new TicketItem(90, "满200减30/全品类", "30元现金券", 300));
            ticketItems.add(new TicketItem(45, "价值200元以下，仅限指定商品", "-20%折扣券", 400));
            ticketItems.add(new TicketItem(100, "无门槛/全品类", "10元现金券", 200));
        }
        return ticketItems;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.ticket_page_bar;
    }
}
