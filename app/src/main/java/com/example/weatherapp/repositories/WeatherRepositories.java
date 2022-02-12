package com.example.weatherapp.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.R;
import com.example.weatherapp.endpoints.IWeatherData;
import com.example.weatherapp.models.allweather.ParentWeather;
import com.example.weatherapp.service.NetworkingService;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public class WeatherRepositories extends BaseRepository{
    private static final String TAG = "WeatherRepositories";
    String apikey = "42ffe227fc17c4179df61cd165c5e1a8";

    private MutableLiveData<ParentWeather> weatherLiveData;
    public Call<ParentWeather> weatherCall;

    public MutableLiveData<ParentWeather> getWeather(Double lat, Double lon, Integer cnt, String token) {
        weatherLiveData = new MutableLiveData<>();
        fetchWeatherData(lat, lon, cnt, token);
        return weatherLiveData;
    }

    private void fetchWeatherData(Double lat, Double lon, Integer cnt, String token) {
        weatherCall = getWeatherData().getWeatherList(lat, lon, cnt, token);
        weatherCall.enqueue(new Callback<ParentWeather>() {
            @Override
            public void onResponse(@NonNull Call<ParentWeather> call, @NonNull Response<ParentWeather> response) {
                if (response.body() != null && response.isSuccessful()) {
                    weatherLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: "+response.body());
                }else if (response.code() == 401) {
                    setErrorMessage(R.string.authentication_error);
                }else if (response.code() == 500) {
                    setErrorMessage(R.string.server_error);
                }else {
                    setErrorMessage(R.string.unknown_error);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParentWeather> call, @NonNull Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    setErrorMessage(R.string.timed_out);
                } else {
                    setErrorMessage(R.string.no_internet);
                }
            }
        });
    }


    @Override
    protected void setErrorMessage(int errorMessage) {

    }
}
