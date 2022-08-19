package com.tangtang.mvvm.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
    private final int mMaxRetries;//重试次数
    private final int mRetryDelayMillis;//每次重试延时时间
    private int mRetryCount;//当前重试次数

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        mMaxRetries = maxRetries;
        mRetryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> apply(@NonNull Observable<? extends Throwable> observable) throws Exception {
        return observable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
            Throwable e = throwable;
            while (e.getCause() != null) {
                throwable = e;
                e = e.getCause();
            }
            if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                if (apiException.isTimeOutError()) {
                    if (++mRetryCount <= mMaxRetries) {
                        return Observable.timer(mRetryDelayMillis, TimeUnit.MILLISECONDS);
                    }
                }
            }
            return Observable.error(throwable);
        });
    }
}
