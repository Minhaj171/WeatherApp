package com.example.weatherapp.models.primaryweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Md Minhajul Islam on 1/6/2022.
 */
public class Rain {
    @SerializedName("1h")
    @Expose
    private Double _1h;

    public Double get1h() {
        return _1h;
    }

    public void set1h(Double _1h) {
        this._1h = _1h;
    }
}
