package com.ceosilvajr.android.weatherapp.networks.clients;

import com.google.gson.JsonObject;

import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by ceosilvajr on 20/02/16.
 */
public interface WeatherClient {

    @GET("weather")
    Observable<Response<JsonObject>> getWeather(@Query("q") String q, @Query("units") String metric,
                                                @Query("appid") String appId);

    @GET("forecast")
    Observable<Response<JsonObject>> getForecast(@Query("id") String id, @Query("units") String metric,
                                                 @Query("appid") String appId);

}