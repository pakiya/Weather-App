package com.mvparchitecture.data.network;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.mvparchitecture.applicationLevel.AppScope;
import com.mvparchitecture.applicationLevel.ApplicationContext;
import com.mvparchitecture.applicationLevel.ContextModule;
import com.mvparchitecture.data.network.di.NetworkModule;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {
    @Provides
    @AppScope
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @AppScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
