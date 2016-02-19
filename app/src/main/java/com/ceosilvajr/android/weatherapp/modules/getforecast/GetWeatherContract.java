package com.ceosilvajr.android.weatherapp.modules.getforecast;

import com.ceosilvajr.android.weatherapp.models.Weather;

/**
 * Created by ceosilvajr on 02/02/16.
 */
public interface GetWeatherContract {

    interface View {

        void showGetWeatherProgress();

        void hideGetWeatherProgress();

        void showWeather(Weather weather);

        void showGetWeatherMessage(String message);
    }

    interface Listener {
        void onSuccess(Weather weather);

        void onError(Throwable throwable);

        void onFinish();
    }
}
