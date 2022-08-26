package com.tangtang.mvvm.model;

import com.tangtang.mvvm.api.API;
import com.tangtang.mvvm.api.ApiCallback;
import com.tangtang.mvvm.api.ApiStore;
import com.tangtang.mvvm.api.ResponseTransformer;
import com.tangtang.mvvm.base.AbstractBaseModel;
import com.tangtang.mvvm.json.RYJson;
import com.tangtang.mvvm.param.WeatherParam;
import com.tangtang.mvvm.utils.ApiUtils;

public class ReportModel extends AbstractBaseModel {
    //post a byte[] data
    public void report(String url, byte[] json, ApiCallback<Boolean> callback){
        executeHTTP(
                API.getInstance().create(ApiStore.class).report(url, ApiUtils.createRequestBody(json)),
                new ResponseTransformer(),
                callback
        );
    }

    //post a json data
    public void report(String url, WeatherParam param, ApiCallback<Boolean> callback){
        executeHTTP(
                API.getInstance().create(ApiStore.class).report(url, ApiUtils.createRequestBody(RYJson.get().toJson(param))),
                new ResponseTransformer(),
                callback
        );
    }
}
