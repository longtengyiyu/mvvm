package com.tangtang.mvvm.model;

import com.tangtang.mvvm.api.API;
import com.tangtang.mvvm.api.ApiCallback;
import com.tangtang.mvvm.api.ApiStore;
import com.tangtang.mvvm.api.ResponseTransformer;
import com.tangtang.mvvm.base.AbstractBaseModel;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.db.Repository;
import com.tangtang.mvvm.entity.DayWeather;
import com.tangtang.mvvm.param.WeatherParam;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherModel extends AbstractBaseModel {

    public void getWeather(String city, ApiCallback<Weather> callback) {
        executeHTTP(
                API.getInstance().create(ApiStore.class).getWeather(city), new ResponseTransformer(),
                        callback
        );
    }

    public void getWeatherByDB(String city, ApiCallback<List<DayWeather>> callback) {
        Repository.getInstance().getWeather(city).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DayWeather>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<DayWeather> dayWeathers) {
                        callback.onNext(dayWeathers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

    public void saveWeather2DB(List<DayWeather> dayWeathers, ApiCallback<Boolean> callback){
        Repository.getInstance().saveWeather(dayWeathers).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        callback.onNext(aBoolean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

}
