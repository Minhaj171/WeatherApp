package com.example.weatherapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.adapters.WeatherRowAdapter;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.endpoints.IWeatherData;
import com.example.weatherapp.models.allweather.CheckWeather;
import com.example.weatherapp.models.allweather.Main;
import com.example.weatherapp.models.allweather.ParentWeather;
import com.example.weatherapp.models.primaryweather.CityWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding dataBinding;
    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}";
    String apikey = "42ffe227fc17c4179df61cd165c5e1a8";
    IWeatherData myapi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myapi = retrofit.create(IWeatherData.class);
        getWeatherOfCities();
    }

    private void getWeatherOfCities() {
        Call<ParentWeather> call =  myapi.getWeatherList(23.68,90.35,50,apikey);
        call.enqueue(new Callback<ParentWeather>() {
            @Override
            public void onResponse(@NonNull Call<ParentWeather> call, @NonNull Response<ParentWeather> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    setListOfCitiesWeather(response.body().getList());
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParentWeather> call, @NonNull Throwable t) {

            }
        });


    }

    private void setListOfCitiesWeather(List<CheckWeather> weatherList) {
        WeatherRowAdapter weatherRowAdapter = new WeatherRowAdapter();
        dataBinding.weatherRecycler.setAdapter(weatherRowAdapter);
        dataBinding.weatherRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        weatherRowAdapter.updateWeather(weatherList);

    }


//    public void getweather() {
//        Call<CityWeather> examplecall = myapi.getweather(et.getText().toString().trim(), apikey);
//        examplecall.enqueue(new Callback<CityWeather>() {
//            @Override
//            public void onResponse(@NonNull Call<CityWeather> call, @NonNull Response<CityWeather> response) {
//                if (response.code() == 404) {
//                    Toast.makeText(MainActivity.this, "Please Enter a valid City", Toast.LENGTH_LONG).show();
//                } else if (!(response.isSuccessful())) {
//                    Toast.makeText(MainActivity.this, response.code() + " ", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                CityWeather mydata = response.body();
//                Main main = mydata.getMain();
//                Double temp = main.getTemp();
//                Integer temperature = (int) (temp - 273.15);
//                tv.setText(String.valueOf(temperature) + "C");
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<CityWeather> call, @NonNull Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

}