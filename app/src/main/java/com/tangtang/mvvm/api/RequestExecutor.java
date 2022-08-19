package com.tangtang.mvvm.api;

import androidx.lifecycle.LifecycleOwner;

import com.tangtang.mvvm.utils.CommonUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RequestExecutor {
    private static final String TAG = RequestExecutor.class.getSimpleName();
    private static final String NETWORK_ERROR_MSG = "没有网络连接,请打开你的网络连接";
    public static Builder request(Observable observable){
        return new Builder(observable);
    }

    public static class Builder {

        private Observable request;

        //priority 1
        private Observer observer;

        //priority 2
        private Consumer onNext = o->{};
        private Consumer<? extends Throwable> onError = throwable -> {};
        private Action onComplete = ()->{};

        private Builder(Observable request) {
            this.request = request;
        }

        /**
         * 线程切换 设置
         */
        private ObservableTransformer threadTransform = Rx.IO_TRANSFORM;
        /**
         * 数据结构适配器 设置
         */
        private ObservableTransformer dataTransform;

        /**
         * 重试操作 设置
         */
        private Function retryFunction;

        /**
         * 延迟obr
         */
        private Long delay;

        /**
         * 是否需要网络
         */
        private boolean needOnline = false;

        public Builder io(){
            this.threadTransform = Rx.IO_TRANSFORM;
            return this;
        }

        public Builder main(){
            this.threadTransform = Rx.MAIN_TRANSFORM;
            return this;
        }

        public Builder retry(Function function){
            this.retryFunction = function;
            return this;
        }

        public Builder retry(int times, int interval){
            this.retryFunction = Rx.retry(times, interval);
            return this;
        }

        public Builder delay(long delay){
            this.delay = delay;
            return this;
        }

        public Builder retry3Time(){
            this.retryFunction = Rx.RETRY_THREE_TIMES;
            return this;
        }

        public Builder online(){
            this.needOnline = true;
            return this;
        }

        public Builder dataTransform(ObservableTransformer dataTransform){
            this.dataTransform = dataTransform;
            return this;
        }

        /**
         * 跟随生命周期
         * @param lifecycleOwner
         * @return
         */
        public Builder attach(LifecycleOwner lifecycleOwner){
            return this;
        }

        public Observable buildRequest(){
            if(request == null){
                return Observable.never();
            }

            if(delay != null && delay > 0){
                request = request.delay(delay, TimeUnit.MILLISECONDS);
            }

            if(threadTransform != null){
                request = request.compose(threadTransform);
            }

            if(retryFunction != null){
                request = request.retryWhen(retryFunction);
            }

            if(dataTransform != null){
                request = request.compose(dataTransform);
            }

            return request;
        }

        /**
         * 真实执行
         */
        private Disposable realExecute(){
            buildRequest();

            if(onNext == null && onError == null && onComplete == null){
                return Rx.NOTHING();
            }

            Disposable disposable = null;

            if(observer != null){
                disposable = request.subscribe(
                        o -> observer.onNext(o),
                        throwable -> observer.onError((Throwable) throwable),
                        () -> observer.onComplete()
                );
            }else{
                disposable = request.subscribe(onNext, onError, onComplete);
            }

            return disposable;
        }

        public Disposable execute(Observer observer){
            this.observer = observer;

            if(needOnline && !checkNetwork(observer)){
                return Rx.NOTHING();
            }

            return realExecute();
        }

        public Disposable execute(Consumer consumer){
            this.onNext = consumer;

            if(needOnline && !checkNetwork(null, null)){
                return Rx.NOTHING();
            }

            return realExecute();
        }

        public Disposable execute(Consumer next, Consumer error){
            this.onNext = next;
            this.onError = error;

            if(needOnline && !checkNetwork(error, null)){
                return Rx.NOTHING();
            }

            return realExecute();
        }

        public Disposable execute(Consumer next, Consumer error, Action complete){
            this.onNext = next;
            this.onError = error;
            this.onComplete = complete;

            if(needOnline && !checkNetwork(error, complete)){
                return Rx.NOTHING();
            }

            return realExecute();
        }
    }

    private static boolean checkNetwork(Observer subscriber) {
        if (!CommonUtils.isNetConnected()) {
            if(subscriber != null){
                subscriber.onError(new ApiException(NETWORK_ERROR_MSG));
                subscriber.onComplete();
            }
            return false;
        }
        return true;
    }

    private static boolean checkNetwork(Consumer consumer, Action action) {
        if (!CommonUtils.isNetConnected()) {
            if(consumer != null){
                try {
                    consumer.accept(new ApiException(NETWORK_ERROR_MSG));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(action != null){
                try {
                    action.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }
}
