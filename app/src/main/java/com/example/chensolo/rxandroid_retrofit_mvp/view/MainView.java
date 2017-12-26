package com.example.chensolo.rxandroid_retrofit_mvp.view;

import java.util.List;

import model.Place;
import service.response.WeatherResponse;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface MainView {
    void showProgress();
    void hideProgress();
    void setupPlaceData(List<Place> placeList);
    void setupWeatherData(WeatherResponse response);
}
