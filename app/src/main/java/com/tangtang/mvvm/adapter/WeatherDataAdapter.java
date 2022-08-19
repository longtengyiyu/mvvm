package com.tangtang.mvvm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.tangtang.mvvm.R;
import com.tangtang.mvvm.base.BaseAbstractAdapter;
import com.tangtang.mvvm.base.BaseViewHolder;
import com.tangtang.mvvm.databinding.ItemWeatherBinding;
import com.tangtang.mvvm.entity.DayWeather;

import java.util.List;

public class WeatherDataAdapter extends BaseAbstractAdapter<BaseViewHolder<DayWeather, ItemWeatherBinding>, DayWeather, ItemWeatherBinding> {

    private int l;
    private int h;

    public WeatherDataAdapter(Context context) {
        super(context);
    }

    public void setLowAndHigh(int low, int high){
        this.l = low;
        this.h = high;
    }

    @NonNull
    @Override
    public BaseViewHolder<DayWeather, ItemWeatherBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWeatherBinding binding = ItemWeatherBinding.inflate(layoutInflater);
        return new ViewHolder(binding.getRoot(), binding);
    }

    public class ViewHolder extends BaseViewHolder<DayWeather, ItemWeatherBinding>{
        public ViewHolder(View itemView, ItemWeatherBinding binding) {
            super(itemView, binding);
        }

        @Override
        public void bindViewData(DayWeather data) {
            binding.dateText.setText(data.getDate());
            binding.weekText.setText(data.getWeek());
            List<String> wins = data.getWin();
            String windy = "";
            if (wins != null && !wins.isEmpty()){
                for (String w : wins) {
                    windy += w;
                }
            }
            binding.windText.setText(windy);
            binding.windLevelText.setText(data.getWinSpeed());
            String weatherDay = data.getWeaDay();
            if (weatherDay.contains("多云")) {
                binding.ivDayIcon.setImageResource(R.drawable.ic_cloud);
            } else if (weatherDay.contains("晴")) {
                binding.ivDayIcon.setImageResource(R.drawable.ic_sun);
            } else if (weatherDay.contains("阴")) {
                binding.ivDayIcon.setImageResource(R.drawable.ic_overcast);
            } else if (weatherDay.contains("雨")) {
                binding.ivDayIcon.setImageResource(R.drawable.ic_rain);
            } else if (weatherDay.contains("雪")) {
                binding.ivDayIcon.setImageResource(R.drawable.ic_snow);
            } else if (weatherDay.contains("雾")) {
                binding.ivDayIcon.setImageResource(R.drawable.ic_fog);
            }
            String weatherNight = data.getWeaNight();
            if (weatherNight.contains("多云")) {
                binding.ivNightIcon.setImageResource(R.drawable.ic_cloud);
            } else if (weatherNight.contains("晴")) {
                binding.ivNightIcon.setImageResource(R.drawable.ic_sun);
            } else if (weatherNight.contains("阴")) {
                binding.ivNightIcon.setImageResource(R.drawable.ic_overcast);
            } else if (weatherNight.contains("雨")) {
                binding.ivNightIcon.setImageResource(R.drawable.ic_rain);
            } else if (weatherNight.contains("雪")) {
                binding.ivNightIcon.setImageResource(R.drawable.ic_snow);
            } else if (weatherNight.contains("雾")) {
                binding.ivNightIcon.setImageResource(R.drawable.ic_fog);
            }
            binding.weaLine.setLowHighestData(l, h);
            int low[] = new int[3];
            int high[] = new int[3];
            low[1] = Integer.valueOf(data.getTem2());
            high[1] = Integer.valueOf(data.getTem1());
            int position = getLayoutPosition();
            if (position <= 0) {
                low[0] = 100;
                high[0] = 100;
            } else {
                DayWeather dayWeather = listData.get(position - 1);
                low[0] = (Integer.valueOf(dayWeather.getTem2()) + Integer.valueOf(data.getTem2())) / 2;
                high[0] = (Integer.valueOf(dayWeather.getTem1()) + Integer.valueOf(data.getTem1())) / 2;

            }
            if (position >= listData.size() - 1) {
                low[2] = 100;
                high[2] = 100;
            } else {
                DayWeather dayWeather = listData.get(position + 1);
                low[2] = (Integer.valueOf(dayWeather.getTem2()) + Integer.valueOf(data.getTem2())) / 2;
                high[2] = (Integer.valueOf(dayWeather.getTem1()) + Integer.valueOf(data.getTem1())) / 2;
            }
            binding.weaLine.setLowHighData(low, high);
        }
    }
}
