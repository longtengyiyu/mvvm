package com.tangtang.mvvm.kotlin.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tangtang.mvvm.base.BaseAbstractActivity
import com.tangtang.mvvm.utils.ToastUtils

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
open abstract class BaseKotlinActivity<V : BaseViewModel<D>, B : ViewDataBinding, D > : BaseAbstractActivity(), Observer<D> {

    lateinit var viewModel: V
    abstract val binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setUpComponent()
    }

    private fun initViewModel(){
//        mBinding = DataBindingUtil.setContentView(this, contentLayoutRes)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[getModelClass()]
        viewModel.data.observe(this, this)
        viewModel.errorMsg.observe(this, { ToastUtils.showToastMessage(it) })
        binding.lifecycleOwner = this
    }

    override fun onChanged(d: D) {
        updateData(d)
    }

    open fun updateData(d: D){

    }

    /**
     * 设置ViewModel
     */
    abstract fun getModelClass():  Class<V>

    /**
     * 设置ViewModel
     */
    abstract fun setViewModel()

    open fun setUpComponent(){

    }

    override fun onDestroy() {
        super.onDestroy()
        if (viewModel != null){
            viewModel.data.removeObserver(this)
            viewModel.destroy()
        }
    }
}