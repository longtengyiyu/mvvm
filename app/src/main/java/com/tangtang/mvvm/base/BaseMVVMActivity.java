package com.tangtang.mvvm.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tangtang.mvvm.json.RYJson;
import com.tangtang.mvvm.utils.LogUtils;
import com.tangtang.mvvm.utils.ToastUtils;


/**
 * Author:
 * Version    V1.0
 * Date:      2021/7/30
 * Description:MVVMActivity基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2021/7/30                  1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseMVVMActivity<V extends BaseViewModel, B extends ViewDataBinding, D> extends BaseAbstractActivity implements Observer<D>{
    protected final String TAG = BaseMVVMActivity.class.getSimpleName();
    protected V viewModel;
    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        setViewModel();
        setUpComponent();
    }

    private void initViewModel(){
        binding = DataBindingUtil.setContentView(this, getContentLayoutRes());
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(getModelClass());
//        viewModel = new ViewModelProvider(this).get(getModelClass());
        viewModel.getData().observe(this, this);
        viewModel.getErrorMsg().observe(this, (Observer<String>) msg -> ToastUtils.showToastMessage(msg));
        binding.setLifecycleOwner(this);
    }

    protected abstract Class<V> getModelClass();

    /**
     * 更新view model
     */
    protected void setViewModel(){

    }

    protected abstract void setUpComponent();

    @Override
    public void onChanged(D d) {
        LogUtils.d(TAG, RYJson.get().toJson(d));
        updateData(d);
    }

    protected void updateData(D d){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null){
            viewModel.getData().removeObservers(this);
            viewModel.destroy(); //可以不用调用destroy
        }
    }
}
