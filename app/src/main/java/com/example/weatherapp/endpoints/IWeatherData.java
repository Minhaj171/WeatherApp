package com.example.weatherapp.endpoints;

import com.example.weatherapp.models.allweather.CheckWeather;
import com.example.weatherapp.models.primaryweather.CityWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public interface IWeatherData {
    @GET("data/2.5/find")
    Call<List<CheckWeather>> getWeatherList(@Query("lat") Double lat, @Query("lon") Double lon, @Query("cnt") Integer cnt, @Query("api_token") String appId);

    @GET("data/2.5/weather")
    Call<CityWeather> getCityWeather(@Query("q") String cityName, @Query("APPID") String token);

    @GET("weather")
    Call<CityWeather> getweather(@Query("q") String cityname, @Query("appid") String apikey);

}
