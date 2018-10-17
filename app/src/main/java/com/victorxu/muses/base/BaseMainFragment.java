package com.victorxu.muses.base;

import android.content.Context;

import com.victorxu.muses.gallery.view.GalleryFragment;

public class BaseMainFragment extends BaseFragment {

    protected OnBackToFirstListener mBackToFirstListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            mBackToFirstListener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBackToFirstListener = null;
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            mDelegate.popChild();
        } else {
            if (this instanceof GalleryFragment) {   // 如果是 第一个Fragment GalleryFragment 则退出app
                mActivity.finish();
            } else {                                    // 如果不是,则回到第一个Fragment
                mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
