package com.tangtang.mvvm.entity;

import com.google.gson.annotations.SerializedName;
import com.tangtang.mvvm.utils.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
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
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> win;
    private String city;

    @Generated(hash = 432809456)
    public DayWeather(String tem, String tem1, String tem2, String wea, String date,
            String week, String winSpeed, String weaDay, String weaNight,
            List<String> win, String city) {
        this.tem = tem;
        this.tem1 = tem1;
        this.tem2 = tem2;
        this.wea = wea;
        this.date = date;
        this.week = week;
        this.winSpeed = winSpeed;
        this.weaDay = weaDay;
        this.weaNight = weaNight;
        this.win = win;
        this.city = city;
    }

    @Generated(hash = 1601723443)
    public DayWeather() {
    }

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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
