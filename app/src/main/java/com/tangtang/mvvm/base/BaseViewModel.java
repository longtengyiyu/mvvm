package com.tangtang.mvvm.base;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tangtang.mvvm.app.AppApplication;
import com.tangtang.mvvm.db.AppCache;
import com.tangtang.mvvm.utils.ReflectUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observer;

/**
 * Author:
 * Version    V1.0
 * Date:      2021/7/30
 * Description:ViewModel基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2021/7/30                  1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseViewModel<T, P> extends ViewModel {
    protected final String TAG = BaseViewModel.class.getSimpleName();

    /**
     * 数据源
     */
    private MutableLiveData<T> mData = new MutableLiveData<>();
    /**
     * 错误信息
     */
    private MutableLiveData<String> mErrorMsg = new MutableLiveData<>();

    private final Map<String, AbstractBaseModel> models = new HashMap<>();

    public MutableLiveData<T> getData(){
        return mData;
    }

    public MutableLiveData<String> getErrorMsg(){
        return mErrorMsg;
    }

    public BaseViewModel(){
        initPreModels();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private void initPreModels() {
        Set<AbstractBaseModel> preModels = new HashSet<>();
        prepareModels(preModels);
        if(!preModels.isEmpty()){
            for(AbstractBaseModel item: preModels){
                models.put(item.getClass().getSimpleName(), item);
            }
        }
    }

    public <M extends AbstractBaseModel> M getModel(Class<M> clz){
        String className = clz.getSimpleName();

        if(models.get(className) != null){
            return (M) models.get(className);
        }

        M ins = ReflectUtils.getIns(clz);
        ins.setContext(AppApplication.getInstance());
        models.put(className, ins);
        return ins;
    }

    protected void prepareModels(Set<AbstractBaseModel> list){ }

    public void destroy(){
        if(models.size() > 0){
            for(AbstractBaseModel model: models.values()){
                model.destroy();
            }
        }
        models.clear();
    }

    //load data
    public void loadData(P param){
        if (AppCache.isNetConnected()){
            loadDataByNetwork(param);
        }else{
            loadDataByDb(param);
        }
    }

    //load network data
    protected abstract void loadDataByNetwork(P param);

    //load db data
    protected abstract void loadDataByDb(P param);

}
