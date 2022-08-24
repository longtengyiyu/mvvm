package com.tangtang.mvvm.db;

import com.tangtang.mvvm.entity.DayWeather;

import java.util.List;

import io.reactivex.Observable;

public interface IRepository {

    /**
     * 获取本地保存的天气
     * @param city
     * @return
     */
    Observable<List<DayWeather>> getWeather(String city);

    /**
     * 保存数据
     * @param weathers
     * @return
     */
    Observable<Boolean> saveWeather(List<DayWeather> weathers);
}
