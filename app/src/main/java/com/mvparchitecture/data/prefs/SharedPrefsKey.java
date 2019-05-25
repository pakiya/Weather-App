package com.mvparchitecture.data.prefs;

import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;

import java.util.ArrayList;
import java.util.List;

public class SharedPrefsKey {
    public static final String PREF_KEY_INFO_OBJECT = "info";

    public static List<CityBySearchWrapper> cityBySearchWrapper = new ArrayList<>(0);
}
