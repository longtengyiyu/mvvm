package com.tangtang.mvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * Author:
 * Version    V1.0
 * Date:      2021/7/30
 * Description:MVVMFragment基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2021/7/30                  1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseMVVMFragment<V extends BaseViewModel, B extends ViewDataBinding, D> extends BaseAbstractFragment implements Observer<D> {
    protected final String TAG = BaseMVVMActivity.class.getSimpleName();
    protected B mBinding;
    protected V mViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getFragmentLayoutRes(), container, false);
        mViewModel = new ViewModelProvider(this).get(getModelClass());
        mViewModel.getData().observe(getViewLifecycleOwner(), this);
        mBinding.setLifecycleOwner(this);
        setViewModel();
        View view = mBinding.getRoot();
        return view;
    }

    /**
     * 更新view model
     */
    protected void setViewModel(){

    }

    @Override
    public void onChanged(D d) {
        updateData(d);
    }

    protected void updateData(D d){

    }

    protected abstract Class<V> getModelClass();

}
