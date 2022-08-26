package com.tangtang.mvvm.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tangtang.mvvm.R;
import com.tangtang.mvvm.adapter.WeatherDataAdapter;
import com.tangtang.mvvm.base.BaseMVVMActivity;
import com.tangtang.mvvm.bean.Weather;
import com.tangtang.mvvm.databinding.ActivityWeatherListBinding;
import com.tangtang.mvvm.db.AppCache;
import com.tangtang.mvvm.entity.DayWeather;
import com.tangtang.mvvm.json.RYJson;
import com.tangtang.mvvm.utils.CommonUtils;
import com.tangtang.mvvm.utils.LogUtils;
import com.tangtang.mvvm.viewmodel.WeatherViewModel;
import com.tangtang.proto.UserOuterClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherListActivity extends BaseMVVMActivity<WeatherViewModel, ActivityWeatherListBinding, List<DayWeather>> {

    private WeatherDataAdapter adapter;

    @Override
    protected Class<WeatherViewModel> getModelClass() {
        return WeatherViewModel.class;
    }

    @Override
    protected int getContentLayoutRes() {
        return R.layout.activity_weather_list;
    }

    @Override
    protected void setUpComponent() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerView.setLayoutManager(manager);
        adapter = new WeatherDataAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        UserOuterClass.City city = UserOuterClass.City.newBuilder().setCity("上海").build();
        LogUtils.d(TAG, "city -->" + RYJson.get().toJson(city));
        viewModel.loadData(city.getCity());
    }

    @Override
    public void onChanged(List<DayWeather> dayWeathers) {
        super.onChanged(dayWeathers);
        if (dayWeathers != null && !dayWeathers.isEmpty()){
            //同一个数组使用Collections.sort不同字段排序会有bug,具体来说就是最地温度不正确
            Collections.sort(dayWeathers, (t1, t2) -> {
                // 排序找到温度最低的，按照最低温度升序排列
                LogUtils.d(TAG, "t1=" + t1.getTem2() + " t2:" + t2.getTem2());
                return Integer.valueOf(t1.getTem2()) - Integer.valueOf(t2.getTem2());
            });
            int low = Integer.valueOf(dayWeathers.get(0).getTem2());
            Collections.sort(dayWeathers, (t1, t2) -> {
                // 排序找到温度最高的，按照最高温度降序排序
                return Integer.valueOf(t2.getTem1()) - Integer.valueOf(t1.getTem1());
            });
            int high = Integer.valueOf(dayWeathers.get(0).getTem1());
            LogUtils.d(TAG, "low=" + low + " high:" + high);
            adapter.setLowAndHigh(27, high);
            adapter.addItems(dayWeathers);
        }
    }
}
