package com.mvparchitecture.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvparchitecture.R;
import com.mvparchitecture.ui.main.dataModel.CityBySearchWrapper;
import com.mvparchitecture.ui.main.fragment.home.HomeFragment;
import com.mvparchitecture.utils.UtilsClass;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<CityBySearchWrapper> weatherList = new ArrayList<>(0);
    private HomeFragment homeFragment;

    @Inject
    public WeatherAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public void updateList(List<CityBySearchWrapper> weatherList) {
        this.weatherList.clear();
        this.weatherList.addAll(weatherList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(weatherList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_item_weather)
        TextView txtWeather;
        @BindView(R.id.image_item_item_weather)
        ImageView ivWether;
        @BindView(R.id.temp_item_weather)
        TextView txtTemp;
        @BindView(R.id.weather_location_home_fragment)
        TextView txtLocation;
        @BindView(R.id.weather_description_home_fragment)
        TextView txtDescription;
        @BindView(R.id.weather_date_home_fragment)
        TextView txtDateTime;
        @BindView(R.id.weather_pressure_home_fragment)
        TextView txtPressure;
        @BindView(R.id.weather_humidity_chance_home_fragment)
        TextView txtHumidity;
        @BindView(R.id.wind_home_fragment)
        TextView txtWind;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void bind(CityBySearchWrapper body, int i) {
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
                            Glide.with(homeFragment).asBitmap().load("http://openweathermap.org/img/w/" +
                                    body.getWeather().get(0).getIcon() + ".png").into(ivWether);
                        } catch (IllegalArgumentException e) {
                            Log.d("Exception ", e.getMessage());
                        }
                    }
                }

                if (body.getDt() != null) {
                    txtDateTime.setText(UtilsClass.getDateTimeDayMonthDay(body.getDt()) + " - " + UtilsClass.getTime(body.getDt()));
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
        }
    }
}
