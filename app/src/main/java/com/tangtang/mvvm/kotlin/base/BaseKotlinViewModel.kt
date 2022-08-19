package com.tangtang.mvvm.kotlin.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
open abstract class BaseKotlinViewModel<T> : ViewModel(){
    open val TAG :String = BaseKotlinViewModel::class.java.name

    var mData: LiveData<T>? = MutableLiveData()
    var mErrorMsg: LiveData<String>? = MutableLiveData()
//    var mutableStateFlow = MutableStateFlow(2)

    override fun onCleared() {
        super.onCleared()
    }

    /**
     * 默认构造函，用于请求数据
     */
    abstract fun requestData()

}