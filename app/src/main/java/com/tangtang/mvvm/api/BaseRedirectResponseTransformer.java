package com.tangtang.mvvm.api;

import com.tangtang.mvvm.json.RYJson;
import com.tangtang.mvvm.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:
 * Version    V1.0
 * Date:      2022/1/13
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2022/1/13                  1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseRedirectResponseTransformer<T> implements ObservableTransformer<T, T>{

    //空数据错误类型
    private static final int CODE_DATA_EMPTY = -1;
    public static final String MSG_DATA_EMPTY = "返回业务数据为空";

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .lift(observer -> new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        observer.onSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull T httpResult) {
                        LogUtils.d("BaseRedirectResponseTransformer", RYJson.get().toJson(httpResult));
                        if (httpResult != null) {
                            observer.onNext(httpResult);
                            observer.onComplete();
                        }else{
                            observer.onError(getApiException(MSG_DATA_EMPTY, CODE_DATA_EMPTY));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public abstract ApiException getApiException(String msg);

    public abstract ApiException getApiException(String msg, int code);
}
