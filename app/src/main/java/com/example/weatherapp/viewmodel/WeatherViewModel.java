package com.example.weatherapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.models.jsonPojo.WeatherMain;
import com.example.weatherapp.repositories.WeatherRepositories;

/**
 * Created by Md Minhajul Islam on 2/12/2022.
 */
public class WeatherViewModel extends ViewModel {
    WeatherRepositories weatherRepositories = new WeatherRepositories();

    public LiveData<WeatherMain> getWeatherData(Double lat, Double lon, Integer cnt, String token) {
        return weatherRepositories.getWeather(lat, lon, cnt, token);
    }

}
