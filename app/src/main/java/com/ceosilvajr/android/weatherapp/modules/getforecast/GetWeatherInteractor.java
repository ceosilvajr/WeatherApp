package com.ceosilvajr.android.weatherapp.modules.getforecast;

import rx.Subscription;

/**
 * Created by ceosilvajr on 13/12/15.
 */
public interface GetWeatherInteractor {

    void getWeather(GetWeatherContract.Listener listener);

    Subscription getSubscription();

}
