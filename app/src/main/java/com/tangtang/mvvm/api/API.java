package com.tangtang.mvvm.api;

import com.tangtang.mvvm.base.BaseApiModule;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class API {
    private static final API INSTANCE = new API();

    public static API getInstance(){
        return INSTANCE;
    }

    /**
     * 接口提供器实例
     * key - 类名
     */
    private final Map<String, Object> stores = new ConcurrentHashMap<>();

    private API(){}

    /**
     * 调用
     * {@link this#create(BaseApiModule, Class)} 后，再次调用可以不传 module
     * @param clz
     * @param <ApiStore>
     * @return
     */
    public <ApiStore> ApiStore create(Class<ApiStore> clz){
        return create(null, clz);
    }

    public <ApiStore> ApiStore create(BaseApiModule module, Class<ApiStore> clz){
        Object cache = stores.get(clz.getSimpleName());
        if(cache != null){
            return (ApiStore) cache;
        }
        return reCreate(module, clz);
    }

    public <ApiStore> ApiStore reCreate(BaseApiModule module, Class<ApiStore> clz){
        ApiStore apiStore = make(module, clz);
        stores.put(clz.getSimpleName(), apiStore);
        return apiStore;
    }

    private <ApiStore> ApiStore make(BaseApiModule module, Class<ApiStore> clz){
        if(module == null){
            throw new RuntimeException("应用内请至少设置一次数据接口模板！！调用 API.create(BaseApiModule, Class)");
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //添加通用拦截器
        for(Interceptor interceptor: module.getInterceptors()){
            builder.addInterceptor(interceptor);
        }

        //通用log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        //设置超时时间等
        OkHttpClient okHttpClient = builder
                .connectTimeout(module.connectTimeout(), TimeUnit.SECONDS)
                .readTimeout(module.readTimeout(), TimeUnit.SECONDS)
                .cache(module.getCacheConfig())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(LenientGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(module.apiHost())
                .build();

        return retrofit.create(clz);
    }
}
