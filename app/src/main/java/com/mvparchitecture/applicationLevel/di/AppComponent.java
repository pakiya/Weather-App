package com.mvparchitecture.applicationLevel.di;

import com.google.gson.Gson;
import com.mvparchitecture.applicationLevel.AppScope;
import com.mvparchitecture.data.network.ApiCall;
import com.mvparchitecture.data.network.PicassoModule;
import com.mvparchitecture.data.prefs.SharedPrefsHelper;
import com.mvparchitecture.data.prefs.SharedPrefsModule;
import com.squareup.picasso.Picasso;

import dagger.Component;
import retrofit2.Retrofit;

@AppScope
@Component(modules = {AppModule.class, PicassoModule.class, SharedPrefsModule.class})
public interface AppComponent {
    Picasso getPicasso();

    ApiCall getApiCall();

    SharedPrefsHelper getSharedPrefsHelper();

    Gson getGson();

    Retrofit getRetrofit();
}
