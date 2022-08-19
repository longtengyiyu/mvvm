package com.tangtang.mvvm.base;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseAbstractAdapter <VH extends BaseViewHolder<T, B>, T, B extends ViewDataBinding> extends RecyclerView.Adapter<VH> {
    protected final String TAG = getClass().getSimpleName();

    protected final Context context;
    protected final LayoutInflater layoutInflater;

    protected List<T> listData = new ArrayList<>();

    public BaseAbstractAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindViewData(getItemData(position));
    }

    @Override
    public int getItemCount() {
        return getDataCount();
    }

    public final int getDataCount() {
        return listData == null ? 0 : listData.size();
    }

    protected Context getContext() {
        return context;
    }

    public List<T> getDataList() {
        return listData;
    }

    public T getItemData(int position) {
        return (position >= 0 && position < listData.size()) ? listData.get(position) : null;
    }

    /**
     * 移除某一条记录
     *
     * @param position 移除数据的position
     */
    public void removeItem(int position) {
        if (position >= 0 && position < listData.size()) {
            listData.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 添加一条记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItem(T data, int position) {
        if (position >= 0 && position <= listData.size()) {
            listData.add(position, data);
            notifyItemInserted(position);
        }
    }

    /**
     * 添加一条记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItem(T data) {
        addItem(data, listData.size());
    }

    /**
     * 移除所有记录
     * 常规使用中,移除内容后notify，马上又添加内容重新notify，容易导致绘制异常
     * 因此调用 {@link this#clearItemsWithoutNotify()} } 进行不刷新的内容清除
     */
    @Deprecated
    public void clearItems() {
        int size = listData.size();
        if (size > 0) {
            listData.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    /**
     * 批量添加记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItems(List<T> data, int position) {
        if (position >= 0 && position <= listData.size() && data != null && data.size() > 0) {
            listData.addAll(position, data);
            notifyItemRangeChanged(position, data.size());
        }
    }

    /**
     * 批量添加记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItems(List<T> data) {
        addItems(data, listData.size());
    }

    public void clearItemsWithoutNotify(){
        listData.clear();
    }

    public void resetItems(List<T> data) {
        listData.clear();
        listData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 交换两个数据的位置
     * @param fromPosition
     * @param toPosition
     */
    public void exchangePosition(int fromPosition, int toPosition) {
        Collections.swap(listData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
}
