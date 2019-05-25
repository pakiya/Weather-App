package com.mvparchitecture.data.prefs;

import android.content.SharedPreferences;

/**
 * Created by pankaj on 24/06/18.
 */
public class SharedPrefsHelper {

    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(SharedPreferences SharedPreferences) {
        mSharedPreferences = SharedPreferences;
    }

    /*------------------------Put String, Integer, Float, Boolean Type data in SharedPrefs ---------------------*/

    public void put(String key, String value){
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value){
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    /*------------------------Get String, Integer, Float, Boolean Type data in SharedPrefs ---------------------*/

    public String get(String key, String defaultValue){
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    /*------------------------------- Delete save date from SharedPrefs -----------------------------------*/

    public void deleteSaveDate(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    /*-------------------------------- Clear All Date from SharedPrefs ------------------------------------*/

    /**Clears {@link SharedPreferences} file from Data folders of this application in the File System.*/
    public void logOut () {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
