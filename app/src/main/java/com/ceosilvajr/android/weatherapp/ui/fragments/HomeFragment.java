package com.ceosilvajr.android.weatherapp.ui.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ceosilvajr.android.weatherapp.R;
import com.ceosilvajr.android.weatherapp.models.Weather;
import com.ceosilvajr.android.weatherapp.modules.getweather.GetForecastContract;
import com.ceosilvajr.android.weatherapp.modules.getweather.GetForecastPresenter;
import com.ceosilvajr.android.weatherapp.modules.getweather.GetForecastPresenterImplementation;
import com.ceosilvajr.android.weatherapp.ui.adapters.WeatherAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements GetForecastContract.View {

    @Bind(R.id.rv_forecast_list)
    RecyclerView mRecyclerViewForecastList;
    @Bind(R.id.pb_forecast)
    ProgressBar mProgressBarForecast;

    private GetForecastPresenter mGetForecastPresenter;
    private Context mContext;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mGetForecastPresenter = new GetForecastPresenterImplementation(this);
        this.mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mGetForecastPresenter.getForecast();
        return view;
    }

    @Override
    public void showGetForecastProgress() {
        mProgressBarForecast.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGetForecastProgress() {
        mProgressBarForecast.setVisibility(View.GONE);
    }

    @Override
    public void showForecast(List<Weather> weathers) {
        mRecyclerViewForecastList.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerViewForecastList.setAdapter(new WeatherAdapter(weathers));
    }

    @Override
    public void showGetForecastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mGetForecastPresenter.killSubscription();
    }
}
