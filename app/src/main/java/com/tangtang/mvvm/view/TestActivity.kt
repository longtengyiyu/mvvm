package com.tangtang.mvvm.view

import android.Manifest
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.tangtang.mvvm.databinding.ActivityWeatherListBinding
import com.tangtang.mvvm.entity.DayWeather
import com.tangtang.mvvm.kotlin.base.BaseKotlinActivity
import com.tangtang.mvvm.kotlin.base.WeatherViewModel

/**
 * Author:
 * Version    V1.0
 * Date:      2022/1/5
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2022/1/5                  1.0                    1.0
 * Why & What is modified:
 */
class TestActivity: BaseKotlinActivity<WeatherViewModel, ActivityWeatherListBinding, List<DayWeather>>(){
    var city:String = "上海"

    override val binding: ActivityWeatherListBinding by lazy {
        ActivityWeatherListBinding.inflate(layoutInflater)
    }

    override fun getContentLayoutRes(): Int {
        return 0
    }

    override fun getModelClass(): Class<WeatherViewModel> {
        return WeatherViewModel::class.java
    }

    override fun setViewModel() {
//        binding.weatherViewModel = viewModel
    }

    override fun setUpComponent() {
//        binding.city = city
        loadWeather(city)
//        binding.btnClick.setOnClickListener{
//            city = if ("上海" == city) "北京" else "上海"
//            loadWeather(city)

//            val intent = Intent(this, WeatherListActivity::class.java)
//            requestDataLauncher.launch(intent)

//            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

//            var permissions: Array<String> = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE)
//            requestPermissionsLauncher.launch(permissions)

//            requestTakePhotoLauncher.launch(null)
//        }
    }

    private fun loadWeather(city: String){
        viewModel.loadDataByNetwork(city)
    }

//    private fun getWeatherParam(city: String): WeatherParam {
//        var param = WeatherParam()
//        param.city = city
//        return param
//    }

    private val requestDataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it != null && it.resultCode == RESULT_OK){
            val data = it.data?.getIntExtra("data", 0);
            Log.d(TAG, "回传数据为：${data}");
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){

    }

    private val requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        if (it[Manifest.permission.READ_EXTERNAL_STORAGE]!!){

        }
        if (it[Manifest.permission.CALL_PHONE]!!){

        }
    }

    private val requestTakePhotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){

    }

    override fun updateData(weather: List<DayWeather>) {
//        binding.city = city
//        binding.tvWeatherInfo.text = weather.type
//        binding.tvWeatherTemp.text = weather.temp
    }

}