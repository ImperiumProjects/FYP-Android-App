package com.example.imperium.finalyearproject;

//
// Author: Jason Power
//

import android.content.Intent;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewPrediction(View view) {
        Intent intent = new Intent(this, PredictionActivity.class);
        startActivity(intent);
    }

    public void viewWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    public void viewAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    /*
    public void viewWeatherAnalysis(View view) {
        Intent intent = new Intent(this, PredictionActivity.class);
        startActivity(intent);
    }*/
}
