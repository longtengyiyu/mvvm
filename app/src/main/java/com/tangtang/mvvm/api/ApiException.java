package com.tangtang.mvvm.api;

public class ApiException extends RuntimeException{
    public static final int TIME_OUT_ERROR_CODE = 1000;
    public static final int LOGOUT_ERROR_CODE = 1001; //退出登录

    private int errorCode;//错误码

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public ApiException(String detailMessage, int code) {
        super(detailMessage);
        errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isTimeOutError() {
        return errorCode == TIME_OUT_ERROR_CODE;
    }

    public boolean isLogoutError() {
        return errorCode == LOGOUT_ERROR_CODE;
    }
}
