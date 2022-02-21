package com.example.weatherapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




}