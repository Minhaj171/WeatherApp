package com.example.weatherapp.repositories;

import com.example.weatherapp.endpoints.IWeatherData;
import com.example.weatherapp.service.NetworkingService;

/**
 * Created by Minhajul Islam  on 12/02/2022.
 */
public abstract class BaseRepository {
    NetworkingService networkingService = new NetworkingService();

    public IWeatherData getWeatherData(){
        return networkingService.getRetrofit().create(IWeatherData.class);
    }

    protected abstract void setErrorMessage(int errorMessage);

}
