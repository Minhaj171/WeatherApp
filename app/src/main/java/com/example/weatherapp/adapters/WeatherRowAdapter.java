package com.example.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.databinding.WeatherRowBinding;
import com.example.weatherapp.models.allweather.CheckWeather;
import com.example.weatherapp.models.allweather.Weather;

import java.util.List;

/**
 * Created by Md Minhajul Islam on 1/7/2022.
 */
public class WeatherRowAdapter extends RecyclerView.Adapter<WeatherRowAdapter.ViewHolder> {
    private List<CheckWeather> weatherList;


    public void updateWeather(List<CheckWeather> weatherList){
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherRowBinding weatherRowBinding = WeatherRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(weatherRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRowAdapter.ViewHolder holder, int position) {
        CheckWeather checkWeather = weatherList.get(position);
        holder.bindView(checkWeather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WeatherRowBinding binding;
        public ViewHolder(WeatherRowBinding weatherRowBinding) {
            super(weatherRowBinding.getRoot());
            this.binding = weatherRowBinding;
        }

        public void bindView(CheckWeather checkWeather) {
            String tempName = null;
            binding.setRowBinding(checkWeather);
            for (Weather weather : checkWeather.getWeather()) {
                tempName = weather.getMain();
            }
            Double tempo =checkWeather.getMain().getTemp();
            Integer temperature=(int)(tempo-273.15);
            binding.setTodayWeatherName(tempName);
            binding.setTodayTemp(temperature);
        }
    }
}
