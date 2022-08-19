package com.tangtang.mvvm.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ApiCallback<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    @Override
    public void onError(Throwable e) {
        onFailed(e.getMessage());
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailed(String message);

}

