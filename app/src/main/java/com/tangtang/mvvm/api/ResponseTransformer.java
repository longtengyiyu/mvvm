package com.tangtang.mvvm.api;

/**
 * Author:
 * Version    V1.0
 * Date:      2017/11/8
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/8                  1.0                    1.0
 * Why & What is modified:
 */

public class ResponseTransformer<T> extends BaseRedirectResponseTransformer<T> {
    @Override
    public ApiException getApiException(String msg) {
        return new ApiException(msg);
    }

    @Override
    public ApiException getApiException(String msg, int code) { return new ApiException(msg, code); }
}
