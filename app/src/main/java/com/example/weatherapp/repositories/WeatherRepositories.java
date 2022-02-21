package com.example.weatherapp.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.R;
import com.example.weatherapp.models.jsonPojo.WeatherMain;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Md Minhajul Islam on 1/5/2022.
 */
public class WeatherRepositories extends BaseRepository{
    private static final String TAG = "WeatherRepositories";
    String apikey = "42ffe227fc17c4179df61cd165c5e1a8";

    private MutableLiveData<WeatherMain> weatherLiveData;
    public Call<WeatherMain> weatherCall;

    public MutableLiveData<WeatherMain> getWeather(Double lat, Double lon, Integer cnt, String token) {
        weatherLiveData = new MutableLiveData<>();
        fetchWeatherData(lat, lon, cnt, token);
        return weatherLiveData;
    }

    private void fetchWeatherData(Double lat, Double lon, Integer cnt, String token) {
        weatherCall = getWeatherData().getWeatherList(lat, lon, cnt, token);
        weatherCall.enqueue(new Callback<WeatherMain>() {
            @Override
            public void onResponse(@NonNull Call<WeatherMain> call, @NonNull Response<WeatherMain> response) {
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
            public void onFailure(@NonNull Call<WeatherMain> call, @NonNull Throwable t) {
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
