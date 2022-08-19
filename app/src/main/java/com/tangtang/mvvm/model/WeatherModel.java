package com.tangtang.mvvm.model;

import com.tangtang.mvvm.api.API;
import com.tangtang.mvvm.api.ApiCallback;
import com.tangtang.mvvm.api.ApiStore;
import com.tangtang.mvvm.api.ResponseTransformer;
import com.tangtang.mvvm.base.AbstractBaseModel;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.param.WeatherParam;

public class WeatherModel extends AbstractBaseModel {

    public void getWeather(String city, ApiCallback<Weather> callback) {
        executeHTTP(
                API.getInstance().create(ApiStore.class).getWeather(city), new ResponseTransformer(),
                        callback
        );
    }

    public void getData(WeatherParam param, ApiCallback<Weather> callback) {

    }

}
