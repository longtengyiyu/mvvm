package com.tangtang.mvvm.viewmodel;

import com.tangtang.mvvm.api.ApiCallback;
import com.tangtang.mvvm.base.AbstractBaseModel;
import com.tangtang.mvvm.base.BaseViewModel;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.model.WeatherModel;

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
public class WeatherViewModel extends BaseViewModel<Weather> {

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
                getData().setValue(weather);
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
}
