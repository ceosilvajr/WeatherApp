package com.ceosilvajr.android.weatherapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ceosilvajr.android.weatherapp.BuildConfig;
import com.ceosilvajr.android.weatherapp.R;
import com.ceosilvajr.android.weatherapp.models.Weather;
import com.ceosilvajr.android.weatherapp.modules.getforecast.GetWeatherContract;
import com.ceosilvajr.android.weatherapp.modules.getforecast.GetWeatherPresenter;
import com.ceosilvajr.android.weatherapp.modules.getforecast.GetWeatherPresenterImplementation;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements GetWeatherContract.View {

    private static final String TAG = "HomeActivity";
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.pb_home)
    ProgressBar mProgressBar;
    @Bind(R.id.tv_date)
    TextView mTextViewDate;
    @Bind(R.id.tv_wind_speed)
    TextView mTextViewWindSpeed;
    @Bind(R.id.tv_description)
    TextView mTextViewDescription;
    @Bind(R.id.tv_sun_rise)
    TextView mTextViewSunRise;
    @Bind(R.id.tv_sun_set)
    TextView mTextViewSunSet;
    @Bind(R.id.iv_icon)
    ImageView mImageViewIcon;
    @Bind(R.id.tv_temp)
    TextView mTextViewTemperature;

    private GetWeatherPresenter mGetWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        this.mGetWeatherPresenter = new GetWeatherPresenterImplementation(this);
        mGetWeatherPresenter.getWeather();
    }

    private void displayDetails(Weather weather) {
        mTextViewDate.setText(weather.getTodayDate());
        mTextViewDescription.setText(weather.getDescription());
        mTextViewWindSpeed.setText(weather.getFormattedWindSpeed());
        mTextViewSunRise.setText(weather.getSunRiseTime());
        mTextViewSunSet.setText(weather.getSunSetTime());
        mTextViewTemperature.setText(weather.getFormattedTemperature());
        Glide.with(this).load(weather.getIconUrl()).asBitmap()
                .placeholder(R.drawable.img_weather_clear).into(mImageViewIcon);
    }

    @Override
    public void showGetWeatherProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGetWeatherProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeather(Weather weather) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, weather.toString());
        }
        displayDetails(weather);
    }

    @Override
    public void showGetWeatherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mGetWeatherPresenter.killSubscription();
    }
}
