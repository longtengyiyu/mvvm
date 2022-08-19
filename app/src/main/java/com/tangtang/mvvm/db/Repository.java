package com.tangtang.mvvm.db;

import com.tangtang.mvvm.api.ApiException;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.entity.DayWeather;
import com.tangtang.mvvm.param.WeatherParam;

import io.reactivex.Emitter;
import io.reactivex.Observable;

public class Repository implements IRepository{

  private static final String TAG = Repository.class.getSimpleName();
  private volatile static Repository INSTANCE = null;
//  private DaoSession daoSession;

  private Repository(){
//    daoSession = DaoHelper.instance().getDaoSession();
  }

  public static Repository getInstance(){
    if (INSTANCE == null){
      synchronized (Repository.class){
        INSTANCE = new Repository();
      }
    }
    return INSTANCE;
  }


  private <T> void objNextOrErr(Emitter<T> emitter, T obj){
    if(obj != null){
      emitter.onNext(obj);
      emitter.onComplete();
    }else{
      emitter.onError(new ApiException("result is null"));
    }
  }

  @Override
  public Observable<DayWeather> getWeather(String city) {
    return null;
  }
}