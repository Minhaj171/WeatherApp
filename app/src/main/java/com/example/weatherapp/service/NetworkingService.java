package com.example.weatherapp.service;

import android.os.Build;
import android.util.Log;

import com.example.weatherapp.app.Constraints;
import com.example.weatherapp.endpoints.IWeatherData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public class NetworkingService {
    public static String API_BASE_URL = Constraints.BASE_URL;

    IWeatherData iWeatherData;

    // Get client and call object for the request
    protected Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    protected Retrofit retrofit = builder.build();

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
