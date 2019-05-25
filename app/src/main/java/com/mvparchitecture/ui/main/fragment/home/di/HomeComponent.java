package com.mvparchitecture.ui.main.fragment.home.di;

import com.mvparchitecture.ui.main.fragment.home.HomeFragment;

import dagger.Component;

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void injectHomeFragment(HomeFragment fragment);
}
