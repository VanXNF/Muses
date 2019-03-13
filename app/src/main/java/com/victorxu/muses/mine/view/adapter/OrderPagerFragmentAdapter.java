package com.victorxu.muses.mine.view.adapter;

import com.victorxu.muses.mine.view.OrderPageFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OrderPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] pagerTitles;

    public OrderPagerFragmentAdapter(FragmentManager fm, String... pagerTitles) {
        super(fm);
        this.pagerTitles = pagerTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OrderPageFragment.newInstance(-1);
            case 1:
                return OrderPageFragment.newInstance(0);
            case 2:
                return OrderPageFragment.newInstance(1);
            case 3:
                return OrderPageFragment.newInstance(2);
            case 4:
                return OrderPageFragment.newInstance(3);
            default:
                return OrderPageFragment.newInstance(position - 1);
        }
    }

    @Override
    public int getCount() {
        return pagerTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitles[position];
    }
}
