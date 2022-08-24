package com.tangtang.mvvm.viewmodel;

import com.tangtang.mvvm.api.ApiCallback;
import com.tangtang.mvvm.base.AbstractBaseModel;
import com.tangtang.mvvm.base.BaseViewModel;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.entity.DayWeather;
import com.tangtang.mvvm.model.WeatherModel;
import com.tangtang.mvvm.utils.LogUtils;

import java.util.List;
import java.util.Set;

/**
 * Author:
 * Version    V1.0
 * Date:      2021/7/30
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2021/7/30                  1.0                    1.0
 * Why & What is modified:
 */
public class WeatherViewModel extends BaseViewModel<List<DayWeather>> {

//    public MutableLiveData<Weather> mWeather = new MutableLiveData<>();

    @Override
    protected void prepareModels(Set<AbstractBaseModel> list) {
        super.prepareModels(list);
        list.add(new WeatherModel());
    }

    public void loadWeather(String city){
        getModel(WeatherModel.class).getWeather(city, new ApiCallback<Weather>() {
            @Override
            protected void onSuccess(Weather weather) {
                List<DayWeather> dayWeathers = weather.getData();
                if (dayWeathers != null && !dayWeathers.isEmpty()){
                    for (DayWeather dayWeather : dayWeathers) {
                        dayWeather.setCity(weather.getCity());
                    }
                    saveWeathers(dayWeathers);
                    getData().setValue(dayWeathers);
                }else {
                    getErrorMsg().setValue("Data is empty.");
                }
            }

            @Override
            protected void onFailed(String message) {
                getErrorMsg().setValue(message);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void loadWeatherByDB(String city){
        getModel(WeatherModel.class).getWeatherByDB(city, new ApiCallback<List<DayWeather>>() {
            @Override
            protected void onSuccess(List<DayWeather> dayWeathers) {
                getData().setValue(dayWeathers);
            }

            @Override
            protected void onFailed(String message) {
                getErrorMsg().setValue(message);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void saveWeathers(List<DayWeather> dayWeathers){
        getModel(WeatherModel.class).saveWeather2DB(dayWeathers, new ApiCallback<Boolean>() {
            @Override
            protected void onSuccess(Boolean aBoolean) {
                LogUtils.d(TAG, "save weathers is successful");
            }

            @Override
            protected void onFailed(String message) {
                LogUtils.d(TAG, "save weathers is failed");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
