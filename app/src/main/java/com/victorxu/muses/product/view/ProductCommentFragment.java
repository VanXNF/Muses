package com.victorxu.muses.product.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseFragment;
import com.victorxu.muses.product.view.adapter.CommentAdapter;
import com.victorxu.muses.product.view.entity.EvaluationItem;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCommentFragment extends BaseFragment {

    private RelativeLayout mParentToolbar;
    private TabLayout mParentTabLayout;
    private ImmersionBar mImmersionBar;
    private RecyclerView mCommentRecycler;
    private CommentAdapter mCommentAdapter;

    public static ProductCommentFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductCommentFragment fragment = new ProductCommentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mImmersionBar = ImmersionBar.with(this).transparentStatusBar().fitsSystemWindows(false);
        mImmersionBar.init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_comment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mParentToolbar = ((ProductContainerFragment) getParentFragment()).getToolbar();
        mParentToolbar.setBackgroundColor(Color.argb( 255, 255,255,255));
        mParentTabLayout = ((ProductContainerFragment) getParentFragment()).getTabLayout();
        mParentTabLayout.setAlpha(1);
        mCommentRecycler = view.findViewById(R.id.product_comment_recycler_view);
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mCommentAdapter = new CommentAdapter(initTestCommentData());
        mCommentRecycler.setAdapter(mCommentAdapter);
    }

    private ArrayList<EvaluationItem> initTestCommentData() {
        ArrayList<EvaluationItem> evaluationItems = new ArrayList<>();

//        urls.add("https://s1.ax1x.com/2018/03/30/9vzmgH.jpg");
//        urls.add("https://s1.ax1x.com/2018/03/30/9vzE4O.jpg");
        for (int i = 0; i < 6; i++) {
            ArrayList<String> urls = new ArrayList<>();
            if (urls.size() < 6) {
                int j = i + 1;
                while (j-- > 0) {
                    urls.add("https://s1.ax1x.com/2018/03/30/9vzmgH.jpg");
                }
            }
            evaluationItems.add(new EvaluationItem("https://s1.ax1x.com/2018/03/30/9vxcnI.jpg",
                    "夏朗拿度", "2018-11-15", "51", "99+",
                    "这款北欧风情装饰画质量很不错，图案比较美观，印刷质感也很好。发货迅速，下次还到这里购买。",
                    5, urls, "北欧风情装饰画 三联"));
        }
        return evaluationItems;
    }
}
