package com.mvparchitecture.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentTransactionManager {

    public static synchronized void doContentFragmentTransactionUsingAdd(FragmentManager mFragmentManager, BaseFragment fragment, boolean popStack, int container, boolean popHome) {

        if (!mFragmentManager.isDestroyed()) {
            if (popStack) {
                try {
                    mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } catch (IllegalStateException e) {
                    e.getMessage();
                }
            }

            if (popHome) {
                for (int i = 0; i < 2; ++i) {
                    mFragmentManager.popBackStack();
                }
            }

            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(container, fragment, fragment.createTag());

            ft.addToBackStack(fragment.createTag());
            ft.commitAllowingStateLoss();
            mFragmentManager.executePendingTransactions();
        }
    }

    public static Fragment getCurrentFragment(FragmentManager mFragmentManager) {
        if (mFragmentManager.getBackStackEntryCount() != 0) {
            String fragmentTag = mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getName();
            return mFragmentManager.findFragmentByTag(fragmentTag);
        } else {
            return null;
        }
    }
}
