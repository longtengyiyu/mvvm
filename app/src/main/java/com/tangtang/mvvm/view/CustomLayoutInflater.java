package com.tangtang.mvvm.view;

import android.content.Context;
import android.view.LayoutInflater;

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
public class CustomLayoutInflater extends LayoutInflater {

    protected CustomLayoutInflater(Context context) {
        super(context);
        setFactory2(new CustomLayoutInflaterFactory(cloneInContext(context)));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return LayoutInflater.from(newContext);
    }


}
