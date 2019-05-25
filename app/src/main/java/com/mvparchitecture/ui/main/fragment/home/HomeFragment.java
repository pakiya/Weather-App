package com.mvparchitecture.ui.main.fragment.home;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.mvparchitecture.R;
import com.mvparchitecture.ui.base.BaseFragment;
import com.mvparchitecture.ui.base.IBackPressInterceptor;
import com.mvparchitecture.ui.main.MainActivity;
import com.mvparchitecture.ui.main.adapter.WeatherAdapter;
import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;
import com.mvparchitecture.ui.main.fragment.home.di.DaggerHomeComponent;
import com.mvparchitecture.ui.main.fragment.home.di.HomeComponent;
import com.mvparchitecture.ui.main.fragment.home.di.HomeModule;
import com.mvparchitecture.utils.UtilsClass;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment<MainActivity> implements HomeContract.Views, IBackPressInterceptor {

    @BindView(R.id.weather_name_home_fragment)
    TextView txtWeather;
    @BindView(R.id.iv_weather_home_fragment)
    ImageView ivWeather;
    @BindView(R.id.weather_pressure_home_fragment)
    TextView txtPressure;
    @BindView(R.id.weather_date_home_fragment)
    TextView txtDate;
    @BindView(R.id.weather_time_home_fragment)
    TextView txtTime;
    @BindView(R.id.weather_temp_home_fragment)
    TextView txtTemp;
    @BindView(R.id.weather_location_home_fragment)
    TextView txtLocation;
    @BindView(R.id.weather_description_home_fragment)
    TextView txtDescription;
    @BindView(R.id.weather_humidity_chance_home_fragment)
    TextView txtHumidity;
    @BindView(R.id.wind_home_fragment)
    TextView txtWind;

    @BindView(R.id.recycler_view_home_fragment)
    com.mvparchitecture.custom.RecyclerViewEmptySupport rvWeather;

    @Inject
    HomePresenter presenter;

    @Inject
    WeatherAdapter weatherAdapter;

    @Inject
    Picasso picasso;

    PlaceAutocompleteFragment placeAutocompleteFragment;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public String createTag() {
        return HomeFragment.class.getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachParent((MainActivity) getActivity());
        ButterKnife.bind(this, view);
        injectHomeFragment();
        initViews();
    }

    private void injectHomeFragment() {
        HomeComponent component = DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build();
        component.injectHomeFragment(this);
    }

    private void initViews() {


        rvWeather.setHasFixedSize(true);
        rvWeather.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWeather.setAdapter(weatherAdapter);

        presenter.getWeatherList();

        placeAutocompleteFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);

        //autocomplete fragment location picker

        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Geocoder mGeocoder = new Geocoder(getActivity(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = mGeocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
                    if (addresses != null && addresses.size() > 0) {
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String location = addresses.get(0).getAddressLine(0);
                        presenter.getWeatherApiCall(place.getName().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getContext(), status.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void showWeatherResponse(CityBySearchWrapper body, List<CityBySearchWrapper> weatherList) {
        if (body != null) {
            if (body.getWeather() != null && body.getWeather().size() > 0) {
                if (!UtilsClass.isStringNullOrEmpty(body.getWeather().get(0).getMain())) {
                    txtWeather.setText(body.getWeather().get(0).getMain());
                }
                if (!UtilsClass.isStringNullOrEmpty(body.getWeather().get(0).getDescription())) {
                    txtDescription.setText(body.getWeather().get(0).getDescription());
                }

                if (!UtilsClass.isStringNullOrEmpty(body.getWeather().get(0).getIcon())) {
                    try {
                        picasso.load("http://openweathermap.org/img/w/" +
                                body.getWeather().get(0).getIcon() + ".png").into(ivWeather);
                    } catch (IllegalArgumentException e) {
                        Log.d("Exception ", e.getMessage());
                    }
                }
            }


            if (body.getDt() != null) {
                txtDate.setText(UtilsClass.getDateTimeDayMonthDay(body.getDt()));
                txtTime.setText(UtilsClass.getTime(body.getDt()));
            }

            if (!UtilsClass.isStringNullOrEmpty(body.getName())) {
                txtLocation.setText(body.getName());
            }

            if (body.getMain() != null) {
                if (body.getMain().getTemp() != null) {
                    txtTemp.setText(UtilsClass.convertKtoC(body.getMain().getTemp()) + "\u00B0" + " C");
                }

                if (body.getMain().getHumidity() != null) {
                    txtHumidity.setText(body.getMain().getHumidity() + "%");
                }

                if (body.getMain().getPressure() != null) {
                    txtPressure.setText(body.getMain().getPressure() + "");
                }
            }

            if (body.getWind() != null) {
                if (body.getWind().getSpeed() != null) {
                    txtWind.setText(body.getWind().getSpeed() + " km/h");
                }
            }

        }

        showWeatherList(weatherList);

    }


    private void showWeatherList(List<CityBySearchWrapper> weatherList) {
        if (weatherList != null && weatherList.size() > 0) {
            weatherAdapter.updateList(weatherList);
        }
    }


    @Override
    public void onBackPressed(BackCallBack backCallBack) {


    }
}
