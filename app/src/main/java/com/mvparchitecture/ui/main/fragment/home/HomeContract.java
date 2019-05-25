package com.mvparchitecture.ui.main.fragment.home;

import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;

import java.util.ArrayList;
import java.util.List;

public interface HomeContract {

    interface Views {

        void showWeatherResponse(CityBySearchWrapper body, List<CityBySearchWrapper> weatherList);

    }

    interface Actions {

        void getWeatherApiCall(String name);

        void getWeatherResponse(CityBySearchWrapper body, List<CityBySearchWrapper> weatherList);

        void getWeatherList();
    }
}
