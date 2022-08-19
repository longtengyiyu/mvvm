package com.tangtang.mvvm.db;

import com.tangtang.mvvm.entity.DayWeather;

import io.reactivex.Observable;

public interface IRepository {

    /**
     * 获取本地保存的天气
     * @param city
     * @return
     */
    Observable<DayWeather> getWeather(String city);
}
