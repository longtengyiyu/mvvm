package com.tangtang.mvvm.base;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder <T, B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected B binding;
    public BaseViewHolder(View itemView, B binding) {
        super(itemView);
        this.binding = binding;
    }

    public abstract void bindViewData(T data);

}
