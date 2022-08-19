package com.tangtang.mvvm.api;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Rx {
    //IO transform
    public static final ObservableTransformer IO_TRANSFORM = upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    //main transform
    public static final ObservableTransformer MAIN_TRANSFORM = upstream -> upstream.subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());

    //default retry setting
    public static final Function RETRY_THREE_TIMES = new RetryWithDelay(3/*重试3次*/, 3000/*重试间隔*/);

    /**
     * 生成retry
     * @param times
     * @param interval
     * @return
     */
    public static Function retry(int times, int interval){
        return new RetryWithDelay(times, interval);
    }

    /**
     * 无意义dispose对象
     */
    public static Disposable NOTHING(){
        return new Disposable() {
            @Override
            public void dispose() { }

            @Override
            public boolean isDisposed() { return true; }
        };
    }
}
