package com.tangtang.mvvm.api;

import com.tangtang.mvvm.bean.Weather;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * Author:
 * Version    V1.0
 * Date:      2021/5/8
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2021/5/8                  1.0                    1.0
 * Why & What is modified:
 */
public interface ApiStore {

    @GET("api?unescape=1&version=v91&appid=43656176&appsecret=I42og6Lm")
    Observable<Weather> getWeather(@Query("city") String city);

    @POST
    Observable<ResponseBody> report(@Url String url, @Body RequestBody body);

}
