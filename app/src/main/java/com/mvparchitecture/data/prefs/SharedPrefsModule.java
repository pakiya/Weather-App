package com.mvparchitecture.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.mvparchitecture.applicationLevel.AppScope;
import com.mvparchitecture.applicationLevel.ApplicationContext;
import com.mvparchitecture.applicationLevel.ContextModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pankaj on 24/06/18.
 */
@Module(includes = ContextModule.class)
public class SharedPrefsModule {

    @Provides
    @AppScope
    public SharedPrefsHelper sharedPrefsHelper(@ApplicationContext Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return new SharedPrefsHelper(sharedPreferences);
    }

}
