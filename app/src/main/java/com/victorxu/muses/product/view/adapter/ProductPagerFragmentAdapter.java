package com.victorxu.muses.product.view.adapter;

import com.victorxu.muses.product.view.ProductDetailFragment;
import com.victorxu.muses.product.view.ProductIndexFragment;
import com.victorxu.muses.product.view.ProductCommentFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] mPagerTitles;

    public ProductPagerFragmentAdapter(FragmentManager fm, String... pagerTitles) {
        super(fm);
        mPagerTitles = pagerTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProductIndexFragment.newInstance();
            case 1:
                return ProductDetailFragment.newInstance();
            case 2:
                return ProductCommentFragment.newInstance();
            default:
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return mPagerTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPagerTitles[position];
    }
}
