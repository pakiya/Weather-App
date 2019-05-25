package com.mvparchitecture.applicationLevel.di;

import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvparchitecture.BuildConfig;
import com.mvparchitecture.applicationLevel.AppScope;
import com.mvparchitecture.data.network.ApiCall;
import com.mvparchitecture.data.network.di.NetworkModule;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module(includes = NetworkModule.class)
public class AppModule {

    @Provides
    @AppScope
    public ApiCall apiCall(Retrofit klohApiCallRetrofit) {
        return klohApiCallRetrofit.create(ApiCall.class);
    }

    @Provides
    @AppScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @AppScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

}
