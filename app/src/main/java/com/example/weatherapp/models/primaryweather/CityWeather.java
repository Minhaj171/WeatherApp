package com.example.weatherapp.models.primaryweather;

import com.example.weatherapp.models.allweather.Clouds;
import com.example.weatherapp.models.allweather.Coord;
import com.example.weatherapp.models.allweather.Main;
import com.example.weatherapp.models.allweather.Sys;
import com.example.weatherapp.models.allweather.Weather;
import com.example.weatherapp.models.allweather.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Md Minhajul Islam on 1/6/2022.
 */
public class CityWeather {
    @SerializedName("main")
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
