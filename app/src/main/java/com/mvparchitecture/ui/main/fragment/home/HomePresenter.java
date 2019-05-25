package com.mvparchitecture.ui.main.fragment.home;

import com.mvparchitecture.ui.base.BasePresenter;
import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter implements HomeContract.Actions {

    HomeFragment fragment;
    HomeRepository repository;

    @Inject
    public HomePresenter(HomeFragment fragment, HomeRepository repository) {
        this.fragment = fragment;
        this.repository = repository;
        repository.onAttach(this);
    }

    @Override
    public void getWeatherApiCall(String name) {
        repository.getWeatherApiCall(name);
    }

    @Override
    public void getWeatherResponse(CityBySearchWrapper body, List<CityBySearchWrapper> weatherList) {
        fragment.showWeatherResponse(body, weatherList);
    }

    @Override
    public void getWeatherList() {
        ArrayList<CityBySearchWrapper> weatherList = repository.getWeatherInfo();
        if (weatherList != null && weatherList.size() > 0) {
            fragment.showWeatherResponse(weatherList.get(0), weatherList);
        } else {
            repository.getWeatherApiCall("India");
        }
    }
}
