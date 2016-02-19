package com.ceosilvajr.android.weatherapp.ui.activities;

import android.content.Intent;

import com.ceosilvajr.android.weatherapp.R;
import com.ceosilvajr.android.weatherapp.extras.generics.SplashActivity;

public class MainActivity extends SplashActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public int setTimeInSeconds() {
        return 3;
    }

    @Override
    public void init() {

    }

    @Override
    public void done() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
