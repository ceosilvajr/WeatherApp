package com.ceosilvajr.android.weatherapp.modules.getweather;

import rx.Subscription;

/**
 * Created by ceosilvajr on 13/12/15.
 */
public interface GetForecastInteractor {

    void getForecast(GetForecastContract.Listener listener);

    Subscription getSubscription();

}
