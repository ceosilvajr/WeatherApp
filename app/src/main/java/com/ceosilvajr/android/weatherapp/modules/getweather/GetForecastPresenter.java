package com.ceosilvajr.android.weatherapp.modules.getweather;

/**
 * Created by ceosilvajr on 13/12/15.
 */
public interface GetForecastPresenter {

    void getForecast();

    void killSubscription();
}
