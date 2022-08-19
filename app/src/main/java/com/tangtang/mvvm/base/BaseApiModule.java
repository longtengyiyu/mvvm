package com.tangtang.mvvm.base;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;

public abstract class BaseApiModule {
    //默认超时时间
    private static final long DEFAULT_TIMEOUT = 61;

    //默认缓存区大小
    public static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;//10MB

    /**
     * 通用拦截器
     */
    private final List<Interceptor> interceptors = new ArrayList<>();

    /**
     * 缓存配置
     */
    private final Cache cacheConfig;

    protected BaseApiModule() {
        initInterceptors(interceptors);
        cacheConfig = initCacheConfig(cacheSize());
    }

    protected abstract void initInterceptors(List<Interceptor> interceptors);

    protected abstract Cache initCacheConfig(long cacheSize);

    public abstract String apiHost();

    public List<Interceptor> getInterceptors(){
        return interceptors;
    }

    public Cache getCacheConfig() {
        return cacheConfig;
    }

    public long connectTimeout(){
        return DEFAULT_TIMEOUT;
    }

    public long readTimeout(){
        return DEFAULT_TIMEOUT;
    }

    protected long cacheSize(){
        return HTTP_RESPONSE_DISK_CACHE_MAX_SIZE;
    }
}
