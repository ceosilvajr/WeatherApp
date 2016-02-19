package com.ceosilvajr.android.weatherapp.modules.getforecast;

/**
 * Created by ceosilvajr on 13/12/15.
 */
public interface GetWeatherPresenter {

    void getWeather();

    void killSubscription();
}
