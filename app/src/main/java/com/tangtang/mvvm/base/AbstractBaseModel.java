package com.tangtang.mvvm.base;

import android.content.Context;

import com.tangtang.mvvm.api.RequestExecutor;
import com.tangtang.mvvm.utils.CommonUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AbstractBaseModel {
    protected final String TAG = getClass().getSimpleName();

    private Context context;

    /**
     * 用来管理需要自动解绑的RX组件
     */
    private CompositeDisposable mCompositeDisposable;

    public AbstractBaseModel() { }

    public AbstractBaseModel(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected void execute(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        if(disposable != null){
            mCompositeDisposable.add(disposable);
        }
    }

    protected <O, T> void executeHTTP(Observable<O> request, Observer<T> apiCallback){
        execute(RequestExecutor.request(request).io().retry3Time().online().execute(apiCallback));
    }

    protected <O, T> void executeHTTP(Observable<O> request, ObservableTransformer<O,T> dataTransform, Observer<T> apiCallback){
        execute(RequestExecutor.request(request).io().retry3Time().online().dataTransform(dataTransform).execute(apiCallback));
    }

    protected <O, T> void execute(Observable<O> request, Observer<T> apiCallback){
        execute(RequestExecutor.request(request).io().execute(apiCallback));
    }

    protected <O, T> void execute(Observable<O> request, ObservableTransformer<O,T> dataTransform, Observer<T> apiCallback){
        execute(RequestExecutor.request(request).io().dataTransform(dataTransform).execute(apiCallback));
    }

    //是否有网络
    protected boolean isOnline() {
        return CommonUtils.isNetConnected();
    }

    public void destroy(){
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
}
