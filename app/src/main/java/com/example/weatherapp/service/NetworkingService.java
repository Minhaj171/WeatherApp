package com.example.weatherapp.service;

import android.os.Build;
import android.util.Log;

import com.example.weatherapp.endpoints.IWeatherData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public class NetworkingService {
    IWeatherData iWeatherData;
    // Get client and call object for the request
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
