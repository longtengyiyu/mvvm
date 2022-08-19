package com.tangtang.mvvm.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseAbstractFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getArgumentsForFragmentIntent(getArguments());
        }
    }


    protected abstract int getFragmentLayoutRes();

    /**
     * 获取 Fragment 传递的数据
     *
     * @param bundle Fragment Bundle
     */
    protected void getArgumentsForFragmentIntent(Bundle bundle) {
    }
}
