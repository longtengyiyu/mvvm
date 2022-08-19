package com.tangtang.mvvm.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {
    public static <M> M getIns(Class<M> clz){
        return getIns(clz, null, null);
    }

    public static <M> M getIns(Class<M> clz, Class<?>[] clzs, Object[] args){

        try {
            if(clzs == null || clzs.length == 0){
                //无参构造
                return clz.newInstance();
            }else{
                //有参构造
                Constructor constructors = clz.getConstructor(clzs);
                return (M) constructors.newInstance(args);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("反射实例化失败！请确认" + clz.getSimpleName());
    }
}
