package com.tangtang.mvvm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tangtang.mvvm.app.AppApplication;
import com.tangtang.mvvm.db.AppCache;
import com.tangtang.mvvm.utils.LogUtils;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = NetworkBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            onNetStateChanged();
        }
    }

    private void onNetStateChanged(){
        ConnectivityManager connectionManager = (ConnectivityManager) AppApplication.getInstance().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        boolean isConnected = true;
        if (networkInfo != null && networkInfo.isAvailable()) {
            //有网
            LogUtils.d(TAG, "网络正常！");
            isConnected = true;
        } else {
            //无网
            LogUtils.d(TAG, "没有网络！");
            isConnected = false;
        }
        AppCache.setNetConnected(isConnected);
    }
}
