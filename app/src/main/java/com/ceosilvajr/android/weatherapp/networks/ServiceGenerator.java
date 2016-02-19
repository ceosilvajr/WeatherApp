package com.ceosilvajr.android.weatherapp.networks;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by ceosilvajr on 03/11/15.
 */
public class ServiceGenerator {

    private static ServiceGenerator sServiceGenerator = new ServiceGenerator();
    private static HttpLoggingInterceptor sLogging = new HttpLoggingInterceptor();
    private static OkHttpClient sOkHttpClient = new OkHttpClient();
    private static final int TIMEOUT = 20;
    private static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private ServiceGenerator() {

    }

    public static ServiceGenerator getInstance() {
        sLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        sOkHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        sOkHttpClient.interceptors().add(sLogging);
        return sServiceGenerator;
    }

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public <S> S createService(Class<S> serviceClass) {
        sOkHttpClient.interceptors().add(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        Retrofit retrofit = sBuilder.client(sOkHttpClient).build();
        return retrofit.create(serviceClass);
    }
}
