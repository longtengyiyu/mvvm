package com.tangtang.mvvm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.tangtang.mvvm.R;

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
public class CustomView extends FrameLayout {
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.widget_view, this, true);
        CustomLayoutInflater layoutInflater = new CustomLayoutInflater(getContext());
        layoutInflater.inflate(R.layout.custom_view,this, true);
//        addView();
//        View view = CustomLayoutInflater.from(getContext()).inflate(R.layout.custom_view, this, false);
        setOnClickListener(v->{
//            float xStart = v
//            ViewHelper.setTranslationX();
//            ViewHelper.setTranslationY();
        });
    }
}
