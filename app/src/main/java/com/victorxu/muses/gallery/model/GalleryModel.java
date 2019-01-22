package com.victorxu.muses.gallery.model;

import com.victorxu.muses.gallery.contract.GalleryContract;
import com.victorxu.muses.gson.Banner;

import java.util.ArrayList;

public class GalleryModel implements GalleryContract.Model {

    @Override
    public ArrayList<Banner.BannerData> getBannerData() {
        return null;
    }

    @Override
    public void getRecommendData() {

    }

    @Override
    public void getNewProductData() {

    }

    @Override
    public void getHotProductData() {

    }
}
