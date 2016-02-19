package com.ceosilvajr.android.weatherapp.networks.services;

import com.ceosilvajr.android.weatherapp.extras.generics.AppConstants;
import com.ceosilvajr.android.weatherapp.networks.ServiceGenerator;
import com.ceosilvajr.android.weatherapp.networks.clients.WeatherClient;
import com.google.gson.JsonObject;

import retrofit.Response;
import rx.Observable;

/**
 * Created by ceosilvajr on 20/02/16.
 */
public class WeatherService {

    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }

    private WeatherService() {

    }

    public Observable<Response<JsonObject>> getWeather() {
        WeatherClient caperClient = ServiceGenerator.getInstance().createService(WeatherClient.class);
        return caperClient.getWeather(AppConstants.WEATHER_QUERY, AppConstants.METRIC, AppConstants.APP_ID);
    }

    public Observable<Response<JsonObject>> getForecast() {
        WeatherClient caperClient = ServiceGenerator.getInstance().createService(WeatherClient.class);
        return caperClient.getForecast(AppConstants.FORECAST_QUERY, AppConstants.METRIC, AppConstants.APP_ID);
    }
}
