package com.mvparchitecture.ui.main.fragment.home;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvparchitecture.applicationLevel.App;
import com.mvparchitecture.data.network.ApiCall;
import com.mvparchitecture.data.prefs.SharedPrefsHelper;
import com.mvparchitecture.data.prefs.SharedPrefsKey;
import com.mvparchitecture.data.prefs.SharedPrefsModule;
import com.mvparchitecture.ui.base.BaseRepository;
import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;
import com.mvparchitecture.utils.Constants;
import com.mvparchitecture.utils.UtilsClass;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository extends BaseRepository<HomePresenter> {

    ApiCall apiCall;
    Gson gson;
    SharedPrefsHelper sharedPrefsHelper;

    @Inject
    public HomeRepository(ApiCall apiCall, Gson gson, SharedPrefsHelper sharedPrefsHelper) {
        this.apiCall = apiCall;
        this.gson = gson;
        this.sharedPrefsHelper = sharedPrefsHelper;
    }

    public void getWeatherApiCall(String name) {
        Call<CityBySearchWrapper> api = apiCall.getApiCall(name);
        api.enqueue(new Callback<CityBySearchWrapper>() {
            @Override
            public void onResponse(Call<CityBySearchWrapper> call, Response<CityBySearchWrapper> response) {
                if (response.code() == 200) {
                    setWeatherInfo(response.body());
                }
            }

            @Override
            public void onFailure(Call<CityBySearchWrapper> call, Throwable t) {

            }
        });
    }

    private void setWeatherInfo(CityBySearchWrapper body) {
        if (body != null) {
            ArrayList<CityBySearchWrapper> weatherList = getWeatherInfo();
            weatherList.add(body);
            String json = gson.toJson(weatherList);
            if (!UtilsClass.isStringNullOrEmpty(json)) {
                sharedPrefsHelper.put(SharedPrefsKey.PREF_KEY_INFO_OBJECT, json);
                getActions().getWeatherResponse(body, weatherList);
            }
        }
    }

    public ArrayList<CityBySearchWrapper> getWeatherInfo() {
        String weatherInfo = sharedPrefsHelper.get(SharedPrefsKey.PREF_KEY_INFO_OBJECT, "");
        ArrayList<CityBySearchWrapper> weatherInfoList = gson.fromJson(weatherInfo, new TypeToken<ArrayList<CityBySearchWrapper>>() {
        }.getType());
        if (weatherInfoList == null) {
            weatherInfoList = new ArrayList<>(0);
        }
        return weatherInfoList;
    }
}
