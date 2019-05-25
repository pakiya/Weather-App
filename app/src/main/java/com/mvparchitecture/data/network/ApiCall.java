package com.mvparchitecture.data.network;

import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("data/2.5/weather?")
    Call<CityBySearchWrapper> getApiCall(@Query("q") String name);
}
