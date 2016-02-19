package com.ceosilvajr.android.weatherapp.modules.getweather;

import com.ceosilvajr.android.weatherapp.models.Weather;
import com.ceosilvajr.android.weatherapp.networks.services.WeatherService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ceosilvajr on 13/12/15.
 */
public class GetForecastInteractorImplementation implements GetForecastInteractor {

    private Subscription mSubscription;

    @Override
    public void getForecast(GetForecastContract.Listener listener) {

        if (mSubscription != null) {
            if (!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();
            }
            mSubscription = null;
        }

        mSubscription = WeatherService.getInstance().getForecast()
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
                            List<Weather> weathers = new ArrayList<>();
                            JsonObject jsonObject = jsonObjectResponse.body();
                            JsonArray jsonArray = jsonObject.get("list").getAsJsonArray();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                weathers.add(new Weather(jsonArray.get(i).getAsJsonObject()));
                            }
                            listener.onSuccess(weathers);
                        }
                    }
                });
    }

    @Override
    public Subscription getSubscription() {
        return mSubscription;
    }
}
