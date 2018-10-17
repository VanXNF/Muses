package com.victorxu.muses.gallery.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.victorxu.muses.R;
import com.victorxu.muses.base.BaseMainFragment;
import com.victorxu.muses.gallery.contract.GalleryContract;
import com.victorxu.muses.gallery.view.adapter.RecommendAdapter;
import com.victorxu.muses.gallery.view.entity.ImageItem;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends BaseMainFragment implements GalleryContract.View {

    private Banner mBanner;
    private RecyclerView mRecycler;
    private List<Integer> mDefaultBannerData;

    public static GalleryFragment newInstance() {
        Bundle args = new Bundle();
//        args.putParcelable();
        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBanner = view.findViewById(R.id.banner_gallery);
        initDefaultBannerData();
        mBanner.setImages(mDefaultBannerData)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        RequestOptions options = RequestOptions
                                .bitmapTransform(new RoundedCorners(
                                        getResources().getInteger(R.integer.banner_image_radius)));
                        Glide.with(context).load(path).apply(options).into(imageView);
                    }
                })
                .start();

        //        recommend
        mRecycler = view.findViewById(R.id.recycler_recommend);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(layoutManager);
        List<ImageItem> data = new ArrayList<>();
        data.add(new ImageItem(1, R.drawable.recommend1));
        data.add(new ImageItem(2, R.drawable.recommend2));
        data.add(new ImageItem(3, R.drawable.recommend3));
        RecommendAdapter adapter = new RecommendAdapter(R.layout.item_recommend, data);
        mRecycler.setAdapter(adapter);

    }

    private void initDefaultBannerData() {
        mDefaultBannerData = new ArrayList<>();
        mDefaultBannerData.add(R.drawable.banner_guide);
        mDefaultBannerData.add(R.drawable.banner_dew);
        mDefaultBannerData.add(R.drawable.banner_cubism);
        mDefaultBannerData.add(R.drawable.banner_institute);
    }

}
