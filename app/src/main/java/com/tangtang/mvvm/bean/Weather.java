package com.tangtang.mvvm.bean;

import com.google.gson.annotations.SerializedName;
import com.tangtang.mvvm.entity.DayWeather;

import java.util.List;

public class Weather {
    @SerializedName("cityid")
    private String cityId;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    @SerializedName("update_time")
    private String updateTime;
    private List<DayWeather> data;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<DayWeather> getData() {
        return data;
    }

    public void setData(List<DayWeather> data) {
        this.data = data;
    }
}
