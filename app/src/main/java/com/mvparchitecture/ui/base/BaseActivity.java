package com.mvparchitecture.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = FragmentTransactionManager.getCurrentFragment(getSupportFragmentManager());
        if (fragment instanceof IBackPressInterceptor) {
            ((IBackPressInterceptor) fragment).onBackPressed(new IBackPressInterceptor.BackCallBack() {
                @Override
                public void backCompleted() {

                }
            });
        }

        super.onBackPressed();
    }
}
