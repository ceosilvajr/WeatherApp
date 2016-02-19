package com.ceosilvajr.android.weatherapp.modules.getforecast;

import com.ceosilvajr.android.weatherapp.models.Weather;
import com.google.gson.JsonObject;

import rx.Subscription;

/**
 * Created by ceosilvajr on 14/12/15.
 */
public class GetWeatherPresenterImplementation implements GetWeatherPresenter,
        GetWeatherContract.Listener {

    private GetWeatherInteractor mGetWeatherInteractor;
    private GetWeatherContract.View mView;

    public GetWeatherPresenterImplementation(GetWeatherContract.View view) {
        this.mView = view;
        this.mGetWeatherInteractor = new GetWeatherInteractorImplementation();
    }

    @Override
    public void getWeather() {
        mView.showGetWeatherProgress();
        mGetWeatherInteractor.getWeather(this);
    }

    @Override
    public void killSubscription() {
        Subscription subscription = mGetWeatherInteractor.getSubscription();
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void onSuccess(Weather weather) {
        mView.showWeather(weather);
    }

    @Override
    public void onError(Throwable throwable) {
        mView.showGetWeatherMessage(throwable.getMessage());
    }

    @Override
    public void onFinish() {
        mView.hideGetWeatherProgress();
    }
}
