package com.mvparchitecture.applicationLevel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mvparchitecture.applicationLevel.di.AppComponent;
import com.mvparchitecture.applicationLevel.di.DaggerAppComponent;
import com.mvparchitecture.data.network.ApiCall;
import com.mvparchitecture.data.prefs.SharedPrefsHelper;
import com.squareup.picasso.Picasso;

public class App extends Application {

    Picasso picasso;
    ApiCall apiCall;
    AppComponent appComponent;
    static SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeAppComponent();
    }

    private void initializeAppComponent() {
        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
        apiCall = appComponent.getApiCall();
        picasso = appComponent.getPicasso();
        sharedPrefsHelper = appComponent.getSharedPrefsHelper();
    }

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static SharedPrefsHelper getSharedPrefsHelper() {
        return sharedPrefsHelper;
    }
}
