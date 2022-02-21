package com.example.weatherapp.endpoints;

import com.example.weatherapp.models.jsonPojo.WeatherMain;
import com.example.weatherapp.models.primaryweather.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public interface IWeatherData {
    @GET("find")
    Call<WeatherMain> getWeatherList(@Query("lat") Double lat, @Query("lon") Double lon, @Query("cnt") Integer cnt, @Query("appid") String token);

    @GET("weather")
    Call<CityWeather> getweather(@Query("q") String cityname, @Query("appid") String apikey);

}
