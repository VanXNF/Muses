package com.victorxu.muses.search.view.adapter;

import com.victorxu.muses.search.view.SearchResultPageFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SearchResultPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] mPagerTitles;

    public SearchResultPagerFragmentAdapter(FragmentManager fm, String... pagerTitles) {
        super(fm);
        mPagerTitles = pagerTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchResultPageFragment.newInstance();
            case 1:
                return SearchResultPageFragment.newInstance();
            case 2:
                return SearchResultPageFragment.newInstance();
            case 3:
                return SearchResultPageFragment.newInstance();
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
