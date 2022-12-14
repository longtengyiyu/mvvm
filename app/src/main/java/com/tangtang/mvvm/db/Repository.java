package com.tangtang.mvvm.db;

import com.tangtang.mvvm.api.ApiException;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.dao.DaoMaster;
import com.tangtang.mvvm.dao.DaoSession;
import com.tangtang.mvvm.dao.DayWeatherDao;
import com.tangtang.mvvm.entity.DayWeather;
import com.tangtang.mvvm.param.WeatherParam;
import com.tangtang.mvvm.utils.LogUtils;

import java.util.List;

import io.reactivex.Emitter;
import io.reactivex.Observable;

public class Repository implements IRepository{

  private static final String TAG = Repository.class.getSimpleName();
  private volatile static Repository INSTANCE = null;
  private DaoSession daoSession;

  private Repository(){
    daoSession = DaoHelper.instance().getDaoSession();
  }

  public static Repository getInstance(){
    if (INSTANCE == null){
      synchronized (Repository.class){
        INSTANCE = new Repository();
      }
    }
    return INSTANCE;
  }

  @Override
  public Observable<List<DayWeather>> getWeather(String city) {
    return Observable.create(e -> {
      //注意，如果多次调用daoSession会有缓存问题。需要调用clear方法
      // daoSession has cache,should be used clear method.
      daoSession.clear();
      DayWeatherDao dao = daoSession.getDayWeatherDao();
      List<DayWeather> dayWeatherList = dao.queryBuilder().where(DayWeatherDao.Properties.City.eq(city)).list();
      objNextOrErr(e, dayWeatherList);
    });
  }

  @Override
  public Observable<Boolean> saveWeather(List<DayWeather> weathers) {
    return Observable.create(e -> {
      DayWeatherDao dao = daoSession.getDayWeatherDao();
      dao.deleteAll();
      //只保存当前最新的
      //save the new data
      dao.insertInTx(weathers);
    });
  }

  private <T> void objNextOrErr(Emitter<T> emitter, T obj){
    if(obj != null){
      emitter.onNext(obj);
      emitter.onComplete();
    }else{
      emitter.onError(new ApiException("result is null"));
    }
  }
}