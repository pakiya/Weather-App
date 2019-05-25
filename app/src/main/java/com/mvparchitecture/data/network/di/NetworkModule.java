package com.mvparchitecture.data.network.di;

import android.util.Log;

import com.mvparchitecture.applicationLevel.AppScope;
import com.mvparchitecture.applicationLevel.ContextModule;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = ContextModule.class)
public class NetworkModule {


    @Provides
    @AppScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(NetworkModule.class.getSimpleName(), message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @AppScope
    public Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder()
                        .addHeader("x-api-key", "14755c1bd25c7a9b631f00f74efa6102");
                return chain.proceed(builder.build());
            }
        };
    }

    public Interceptor sessionHandle() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (response.code() == 401 || response.code() == 403) {
                    //TODO: Handle session expire case.
                    Log.e("logOut:", "Request is successful -LogOut ");
                    response = chain.proceed(request);
                }
                // otherwise just pass the original response on
                return response;
            }
        };
    }

    @Provides
    @AppScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10MB Cache
    }

    @Provides
    @AppScope
    public File cacheFile() {
        File cacheFile = new File("cacheFile");
        return new File(cacheFile.getPath(), "okhttp_cache");
    }

    @Provides
    @AppScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Interceptor headerInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(120 * 1000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .addInterceptor(sessionHandle()).build();
    }

}
