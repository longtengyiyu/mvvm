package com.tangtang.mvvm.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayWeather {
    private String tem; //当前
    private String tem1; //最高
    private String tem2; //最低
    private String wea;
    private String date;
    private String week;
    @SerializedName("win_speed")
    private String winSpeed;
    @SerializedName("wea_day")
    private String weaDay;
    @SerializedName("wea_night")
    private String weaNight;
    private List<String> win;

    public List<String> getWin() {
        return win;
    }

    public void setWin(List<String> win) {
        this.win = win;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public String getWeaDay() {
        return weaDay;
    }

    public void setWeaDay(String weaDay) {
        this.weaDay = weaDay;
    }

    public String getWeaNight() {
        return weaNight;
    }

    public void setWeaNight(String weaNight) {
        this.weaNight = weaNight;
    }
}
