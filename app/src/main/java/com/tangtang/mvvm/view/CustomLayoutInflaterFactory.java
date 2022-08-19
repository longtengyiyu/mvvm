package com.tangtang.mvvm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tangtang.mvvm.R;

import java.lang.reflect.Constructor;

/**
 * Author:
 * Version    V1.0
 * Date:      2022/1/17
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2022/1/17                  1.0                    1.0
 * Why & What is modified:
 */
public class CustomLayoutInflaterFactory implements LayoutInflater.Factory2 {

    private static final String TAG = CustomLayoutInflaterFactory.class.getSimpleName();
    private String[] sClassPrefix = {"android.widget.","android.view.", "androidx.appcompat.widget."};
    private LayoutInflater inflater;

    public CustomLayoutInflaterFactory(LayoutInflater inflater){
        this.inflater = inflater;
    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = null;
        try {
            if (name.contains(".")){
                Class clazz = Class.forName(name);
                Constructor con = clazz.getDeclaredConstructor(Context.class,AttributeSet.class);
                view = (View) con.newInstance(context, attrs);
            }else {
                for (String prefix : sClassPrefix) {
                    view = inflater.createView(name, prefix, attrs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (view != null){
            TypedArray a = context.obtainStyledAttributes(attrs,new int[]{R.attr.x_start, R.attr.y_start});
            if (a != null && a.length() > 0){
                Float xStart = a.getFloat(0, 1f);
                Float yStart = a.getFloat(1, 1f); }
        }
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = null;
        try {
            if (name.contains(".")){
                Class clazz = Class.forName(name);
                Constructor con = clazz.getDeclaredConstructor(Context.class,AttributeSet.class);
                view = (View) con.newInstance(context, attrs);
            }else {
                for (String prefix : sClassPrefix) {
                    view = inflater.createView(name, prefix, attrs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (view != null){
            TypedArray a = context.obtainStyledAttributes(attrs,new int[]{R.attr.x_start, R.attr.y_start});
            if (a != null && a.length() > 0){
                Float xStart = a.getFloat(0, 1f);
                Float yStart = a.getFloat(1, 1f);
            }
        }
        return view;
    }
}
