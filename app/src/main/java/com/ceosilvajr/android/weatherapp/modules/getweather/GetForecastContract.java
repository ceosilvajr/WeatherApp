package com.ceosilvajr.android.weatherapp.modules.getweather;

import com.ceosilvajr.android.weatherapp.models.Weather;

import java.util.List;

/**
 * Created by ceosilvajr on 02/02/16.
 */
public interface GetForecastContract {

    interface View {

        void showGetForecastProgress();

        void hideGetForecastProgress();

        void showForecast(List<Weather> weathers);

        void showGetForecastMessage(String message);
    }

    interface Listener {
        void onSuccess(List<Weather> weathers);

        void onError(Throwable throwable);

        void onFinish();
    }
}
