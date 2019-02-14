package com.victorxu.muses.product.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseSwipeBackFragment;
import com.victorxu.muses.gson.PageComment;
import com.victorxu.muses.product.contract.ProductCommentContract;
import com.victorxu.muses.product.presenter.ProductCommentPresenter;
import com.victorxu.muses.product.view.adapter.CommentAdapter;
import com.victorxu.muses.product.view.entity.EvaluationItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCommentFragment extends BaseSwipeBackFragment implements ProductCommentContract.View {

    private Toolbar mToolbar;
    private RecyclerView mCommentRecycler;
    private CommentAdapter mCommentAdapter;
    private ProductCommentPresenter mPresenter;

    private int id;
//    private

    public static ProductCommentFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        ProductCommentFragment fragment = new ProductCommentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("ID");
        } else {
            id = 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_comment, container, false);
        mPresenter = new ProductCommentPresenter(this, id);
        mPresenter.loadRootView(view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPresenter.loadDataToView();
    }

    @Override
    public void initRootView(View view) {
        mToolbar = view.findViewById(R.id.product_comment_toolbar);
        mCommentRecycler = view.findViewById(R.id.product_comment_recycler_view);
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mCommentAdapter = new CommentAdapter(initTestCommentData());
        mCommentRecycler.setNestedScrollingEnabled(true);
        mCommentRecycler.setAdapter(mCommentAdapter);

        mToolbar.setNavigationOnClickListener((v) -> mActivity.onBackPressed());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showComment(List<PageComment.PageCommentData.CommentModel> data) {

    }

    @Override
    public void showMoreComment(List<PageComment.PageCommentData.CommentModel> moreData) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void hideLoadingMore(boolean isCompeted, boolean isEnd) {

    }

    @Override
    public void showToast(int resId) {
        showToast(getText(resId));
    }

    @Override
    public void showToast(CharSequence text) {
        post(()-> Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(mActivity).statusBarDarkFont(true).init();
    }

    @Override
    protected int setTitleBar() {
        return R.id.product_comment_toolbar;
    }



    private ArrayList<EvaluationItem> initTestCommentData() {
        ArrayList<EvaluationItem> evaluationItems = new ArrayList<>();
        int i = 5;
        while (i > 0) {
            i--;
            ArrayList<String> urls = new ArrayList<>();
            urls.add("https://img.alicdn.com/imgextra/i3/2048829272/O1CN012IMcnFurWfqbAUN_!!0-item_pic.jpg_430x430q90.jpg");
            urls.add("https://img.alicdn.com/imgextra/i2/2048829272/O1CN012IMcn6lxTGWJyTb_!!2048829272.jpg_430x430q90.jpg");
            evaluationItems.add(new EvaluationItem("https://s1.ax1x.com/2018/03/30/9vxcnI.jpg",
                    "夏朗拿度", "2018-11-15", "51", "99+",
                    "这款北欧风情装饰画质量很不错，图案比较美观，印刷质感也很好。发货迅速，下次还到这里购买。",
                    5, urls, "北欧风情装饰画 三联"));
            ArrayList<String> urls2 = new ArrayList<>();
            urls2.add("https://img.alicdn.com/imgextra/i4/2122078663/O1CN012DrhaXqQtvjYMdJ_!!2122078663.jpg_430x430q90.jpg");
            evaluationItems.add(new EvaluationItem("https://s1.ax1x.com/2018/03/30/9vzE4O.jpg",
                    "吉**吉", "2018-11-14", "20", "35",
                    "包装用心，画质很好，纹理清晰，很漂亮，和玄关很搭，不错",
                    5, urls2, "几何抽象"));
        }
        return evaluationItems;
    }
}
