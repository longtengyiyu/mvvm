package com.tangtang.mvvm.model

import com.tangtang.mvvm.bean.Weather
import com.tangtang.mvvm.kotlin.base.BaseKotlinViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Author:
 * Version    V1.0
 * Date:      2022/1/6
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2022/1/6                  1.0                    1.0
 * Why & What is modified:
 */
class TestViewModel : BaseKotlinViewModel<Weather>() {

    override fun requestData() {
        getWeather()
    }

    private fun getWeather() : Flow<Weather> = callbackFlow {

    }
}