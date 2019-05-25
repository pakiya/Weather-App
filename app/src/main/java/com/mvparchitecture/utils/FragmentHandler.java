package com.mvparchitecture.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.mvparchitecture.ui.base.BaseFragment;
import com.mvparchitecture.ui.base.FragmentTransactionManager;
import com.mvparchitecture.ui.main.MainActivity;


public class FragmentHandler implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager mFragmentManager;
    private int mContainerLayout;

    private MainActivity mainActivity;

    public FragmentHandler(MainActivity mainActivity, int mContainerLayout, FragmentManager mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
        this.mContainerLayout = mContainerLayout;
        this.mainActivity = mainActivity;
    }

    public void changeFragment(BaseFragment fragment, boolean doPopBackStack, boolean popHome) {
        FragmentTransactionManager.doContentFragmentTransactionUsingAdd(
                mFragmentManager,
                fragment,
                doPopBackStack,
                mContainerLayout, popHome
        );
    }


    public void handleSwitchFragment(int fragmentId, Bundle bundle) {

        switch (fragmentId) {

        }
    }


    @Override
    public void onBackStackChanged() {

    }
}
