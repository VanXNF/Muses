package com.victorxu.muses.message.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.message.view.adapter.MessageAdapter;
import com.victorxu.muses.message.view.entity.ListMessageItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MessageListFragment extends BaseSwipeBackFragment {

    private RecyclerView mMessageRecycler;
    private MessageAdapter mMessageAdapter;
    private AppCompatImageView mBackImage;

    public static MessageListFragment newInstance() {
        Bundle bundle = new Bundle();
        MessageListFragment fragment = new MessageListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mBackImage = view.findViewById(R.id.list_message_image_back);
        mMessageRecycler = view.findViewById(R.id.message_list_recycler_view);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mMessageAdapter = new MessageAdapter(initTestMessageData());
        mMessageRecycler.setAdapter(mMessageAdapter);
        mBackImage.setOnClickListener((v) -> mActivity.onBackPressed());
    }

    private ArrayList<ListMessageItem> initTestMessageData() {
        ArrayList<ListMessageItem> messageItems = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            messageItems.add(new ListMessageItem("https://s1.ax1x.com/2018/03/30/9vzmgH.jpg"
                    , "客服 " + String.valueOf(i + 1) + " 号", "亲，请确认一下订单信息。以下是您的收货信息",
                    "12:56", i * i * i));
        }
        return messageItems;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.message_page_bar;
    }
}
