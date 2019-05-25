package com.mvparchitecture.ui.main.fragment.home.di;

import com.google.gson.Gson;
import com.mvparchitecture.applicationLevel.App;
import com.mvparchitecture.data.network.ApiCall;
import com.mvparchitecture.data.prefs.SharedPrefsHelper;
import com.mvparchitecture.data.prefs.SharedPrefsKey;
import com.mvparchitecture.ui.main.fragment.home.HomeFragment;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    HomeFragment fragment;

    public HomeModule(HomeFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    HomeFragment getFragment() {
        return fragment;
    }

    @Provides
    ApiCall getApiCall() {
        return App.get(fragment.getActivity()).getAppComponent().getApiCall();
    }

    @Provides
    Gson getGson() {
        return App.get(fragment.getActivity()).getAppComponent().getGson();
    }

    @Provides
    SharedPrefsHelper getSharedPrefsHelper() {
        return App.get(fragment.getActivity()).getAppComponent().getSharedPrefsHelper();
    }

    @Provides
    Picasso getPicasso() {
        return App.get(fragment.getActivity()).getAppComponent().getPicasso();
    }
}
