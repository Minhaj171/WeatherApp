package com.example.weatherapp.endpoints;

import com.example.weatherapp.models.CheckWeather;
import com.example.weatherapp.models.JsonResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public interface IWeatherData {
    @GET("data/2.5/find")
    Call<JsonResponse<List<CheckWeather>>> getWeatherList();

//    @Query("lat") Double lat, @Query("lon") Double lon,
//    @Query("cnt") Integer cnt, @Query("app") String appId)
}
