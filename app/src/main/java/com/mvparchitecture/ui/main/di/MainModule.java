package com.mvparchitecture.ui.main.di;

import android.support.v4.app.FragmentManager;

import com.google.gson.Gson;
import com.mvparchitecture.R;
import com.mvparchitecture.applicationLevel.App;
import com.mvparchitecture.data.network.ApiCall;
import com.mvparchitecture.ui.main.MainActivity;
import com.mvparchitecture.utils.FragmentHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    public MainActivity getMainActivity() {
        return mainActivity;
    }

    @Provides
    public ApiCall getApiCall() {
        return App.get(mainActivity).getAppComponent().getApiCall();
    }

    @Provides
    public FragmentManager getFragmentManager() {
        return mainActivity.getSupportFragmentManager();
    }

    @Provides
    FragmentHandler getFragmentHandler(FragmentManager fragmentManager) {
        return new FragmentHandler(mainActivity, R.id.main_counter_view, fragmentManager);
    }

    @Provides
    Gson getGson() {
        return App.get(mainActivity).getAppComponent().getGson();
    }

}
