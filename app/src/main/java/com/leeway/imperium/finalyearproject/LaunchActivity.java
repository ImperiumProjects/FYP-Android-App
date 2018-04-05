package com.leeway.imperium.finalyearproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.util.HashMap;
import java.util.Map;

public class LaunchActivity extends AppCompatActivity {

    Map<String, Integer> map = new HashMap<String, Integer>();
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        getSupportActionBar().hide();

        map.put("1", R.drawable.mobile_1);
        map.put("2", R.drawable.mobile_2);
        map.put("3", R.drawable.mobile_3);
        map.put("4", R.drawable.mobile_4);
        map.put("5", R.drawable.mobile_5);
        map.put("6", R.drawable.mobile_6);
        map.put("7", R.drawable.mobile_7);

        background = (ImageView) findViewById(R.id.background_one);
        Random r = new Random();
        int i1 = r.nextInt(8 - 1) + 1;
        String bg = Integer.toString(i1);
        background.setImageResource(map.get(bg));
    }

    public void viewMain(View view) {
        Intent intent = new Intent(this, BottomNav.class);
        startActivity(intent);
    }
}
