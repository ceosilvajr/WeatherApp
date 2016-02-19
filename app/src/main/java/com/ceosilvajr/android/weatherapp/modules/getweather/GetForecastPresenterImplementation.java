package com.ceosilvajr.android.weatherapp.modules.getweather;

import com.ceosilvajr.android.weatherapp.models.Weather;

import java.util.List;

import rx.Subscription;

/**
 * Created by ceosilvajr on 14/12/15.
 */
public class GetForecastPresenterImplementation implements GetForecastPresenter,
        GetForecastContract.Listener {

    private GetForecastInteractor mGetForecastInteractor;
    private GetForecastContract.View mView;

    public GetForecastPresenterImplementation(GetForecastContract.View view) {
        this.mView = view;
        this.mGetForecastInteractor = new GetForecastInteractorImplementation();
    }

    @Override
    public void getForecast() {
        mView.showGetForecastProgress();
        mGetForecastInteractor.getForecast(this);
    }

    @Override
    public void killSubscription() {
        Subscription subscription = mGetForecastInteractor.getSubscription();
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void onSuccess(List<Weather> weathers) {
        mView.showForecast(weathers);
    }

    @Override
    public void onError(Throwable throwable) {
        mView.showGetForecastMessage(throwable.getMessage());
    }

    @Override
    public void onFinish() {
        mView.hideGetForecastProgress();
    }
}
