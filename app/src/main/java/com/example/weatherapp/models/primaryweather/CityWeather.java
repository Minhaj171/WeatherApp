package com.example.weatherapp.models.primaryweather;

import com.example.weatherapp.models.jsonPojo.Main;
import com.google.gson.annotations.SerializedName;

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
