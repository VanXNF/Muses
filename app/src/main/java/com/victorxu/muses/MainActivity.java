package com.victorxu.muses;

import android.content.Context;
import android.os.Bundle;
import android.support.design.internal.FlowLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.victorxu.muses.base.BaseActivity;
import com.victorxu.muses.gallery.view.adapter.RecommendAdapter;
import com.victorxu.muses.gallery.view.entity.ImageItem;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private Banner mBanner;
    private List<Integer> mDefaultBannerData;
    private RecyclerView mRecycler;
    private FlowLayout flowLayout;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        flowLayout = findViewById(R.id.historical_search_flow_layout);
        linearLayout = findViewById(R.id.delete_all);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeAllViews();
            }
        });
//        initView();

    }

//    private void initView() {
//        mBanner = findViewById(R.id.banner_gallery);
//        initDefaultBannerData();
//        mBanner.setImages(mDefaultBannerData)
//                .setImageLoader(new ImageLoader() {
//                    @Override
//                    public void displayImage(Context context, Object path, ImageView imageView) {
//                        RequestOptions options = RequestOptions
//                                .bitmapTransform(new RoundedCorners(
//                                        getResources().getInteger(R.integer.banner_image_radius)));
//                        Glide.with(context).load(path).apply(options).into(imageView);
//                    }
//                })
//                .start();
//
////        recommend
//        mRecycler = findViewById(R.id.recycler_recommend);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecycler.setLayoutManager(layoutManager);
//        List<ImageItem> data = new ArrayList<>();
//        data.add(new ImageItem(1, R.drawable.recommend1));
//        data.add(new ImageItem(2, R.drawable.recommend2));
//        data.add(new ImageItem(3, R.drawable.recommend3));
//        RecommendAdapter adapter = new RecommendAdapter(R.layout.item_recommend, data);
//        mRecycler.setAdapter(adapter);
//
//    }

    private void initDefaultBannerData() {
        mDefaultBannerData = new ArrayList<>();
        mDefaultBannerData.add(R.drawable.banner_guide);
        mDefaultBannerData.add(R.drawable.banner_dew);
        mDefaultBannerData.add(R.drawable.banner_cubism);
        mDefaultBannerData.add(R.drawable.banner_institute);
    }

}
