package com.ceosilvajr.android.weatherapp.modules.getforecast;

import com.ceosilvajr.android.weatherapp.models.Weather;
import com.ceosilvajr.android.weatherapp.networks.services.WeatherService;
import com.google.gson.JsonObject;

import retrofit.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ceosilvajr on 13/12/15.
 */
public class GetWeatherInteractorImplementation implements GetWeatherInteractor {

    private Subscription mSubscription;

    @Override
    public void getWeather(GetWeatherContract.Listener listener) {

        if (mSubscription != null) {
            if (!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();
            }
            mSubscription = null;
        }

        mSubscription = WeatherService.getInstance().getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<JsonObject>>() {
                    @Override
                    public void onCompleted() {
                        listener.onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                        listener.onFinish();
                    }

                    @Override
                    public void onNext(Response<JsonObject> jsonObjectResponse) {
                        int responseCode = jsonObjectResponse.code();
                        if (!(responseCode >= 200 && responseCode < 300)) {
                            listener.onError(new Throwable("Something went wrong. Please try again"));
                            listener.onFinish();
                        } else {
                            JsonObject jsonObject = jsonObjectResponse.body();
                            listener.onSuccess(new Weather(jsonObject));
                        }
                    }
                });
    }

    @Override
    public Subscription getSubscription() {
        return mSubscription;
    }
}
