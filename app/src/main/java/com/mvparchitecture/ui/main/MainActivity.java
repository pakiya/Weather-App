package com.mvparchitecture.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.mvparchitecture.R;
import com.mvparchitecture.ui.base.BaseActivity;
import com.mvparchitecture.ui.main.callback.SwitchFragmentCallBack;
import com.mvparchitecture.ui.main.di.DaggerMainComponent;
import com.mvparchitecture.ui.main.di.MainComponent;
import com.mvparchitecture.ui.main.di.MainModule;
import com.mvparchitecture.ui.main.fragment.home.HomeFragment;
import com.mvparchitecture.utils.FragmentHandler;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.Views, SwitchFragmentCallBack {

    @Inject
    FragmentManager mFragmentManager;
    @Inject
    FragmentHandler mFragmentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injectMainActivity();
        openHomeFragment();
        registerForFragmentChanges();
    }

    private void openHomeFragment() {
        mFragmentHandler.changeFragment(HomeFragment.newInstance(), true, false);
    }

    private void registerForFragmentChanges() {
        mFragmentManager.addOnBackStackChangedListener(mFragmentHandler);
    }

    private void injectMainActivity() {
        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
        component.injectMainActivity(this);
    }


    @Override
    public void switchFragment(int fragment, Bundle bundle) {
        mFragmentHandler.handleSwitchFragment(fragment, bundle);
    }
}
