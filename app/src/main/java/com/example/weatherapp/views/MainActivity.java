package com.example.weatherapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.endpoints.IWeatherData;
import com.example.weatherapp.models.allweather.CheckWeather;
import com.example.weatherapp.models.allweather.Main;
import com.example.weatherapp.models.primaryweather.CityWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    IWeatherData iWeatherData;

    EditText et;
    TextView tv;
    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}";
    String apikey = "42ffe227fc17c4179df61cd165c5e1a8";
    LocationManager manager;
    LocationListener locationListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

    }

    public void getweather(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IWeatherData myapi = retrofit.create(IWeatherData.class);
        Call<CityWeather> examplecall = myapi.getweather(et.getText().toString().trim(), apikey);
        examplecall.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if (response.code() == 404) {
                    Toast.makeText(MainActivity.this, "Please Enter a valid City", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity.this, response.code() + " ", Toast.LENGTH_LONG).show();
                    return;
                }
                CityWeather mydata = response.body();
                Main main = mydata.getMain();
                Double temp = main.getTemp();
                Integer temperature = (int) (temp - 273.15);
                tv.setText(String.valueOf(temperature) + "C");
            }

            @Override
            public void onFailure(@NonNull Call<CityWeather> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}