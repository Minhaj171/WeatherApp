package com.example.weatherapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.R;
import com.example.weatherapp.adapters.ICitiesOfWeatherClick;
import com.example.weatherapp.adapters.WeatherRowAdapter;
import com.example.weatherapp.databinding.CitiesFragmentBinding;
import com.example.weatherapp.models.jsonPojo.CheckWeather;
import com.example.weatherapp.models.jsonPojo.WeatherMain;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.util.List;


public class CitiesFragment extends Fragment implements ICitiesOfWeatherClick {
    private CitiesFragmentBinding binding;
    private String apikey = "42ffe227fc17c4179df61cd165c5e1a8";
    private WeatherViewModel weatherViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = CitiesFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        fetchWeatherData(23.68,90.35,50,apikey);
    }

    private void fetchWeatherData(double lat, double lon, int cnt, String apikey) {
        weatherViewModel.getWeatherData(lat,lon,cnt,apikey).observe(getViewLifecycleOwner(), new Observer<WeatherMain>() {
            @Override
            public void onChanged(WeatherMain parentWeather) {
                setListOfCitiesWeather(parentWeather.getList());
            }
        });
    }


    private void setListOfCitiesWeather(List<CheckWeather> weatherList) {
        WeatherRowAdapter weatherRowAdapter = new WeatherRowAdapter();
        binding.weatherRecycler.setAdapter(weatherRowAdapter);
        binding.weatherRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        weatherRowAdapter.updateWeather(weatherList, this);
    }

    @Override
    public void onWeatherClick(CheckWeather checkWeather) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("weather", checkWeather);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_citiesFragment_to_googleMapsFragment, bundle);
    }
}