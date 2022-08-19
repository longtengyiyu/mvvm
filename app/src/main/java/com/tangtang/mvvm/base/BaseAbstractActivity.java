package com.tangtang.mvvm.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.tangtang.mvvm.utils.AppDpiUtils;

public abstract class BaseAbstractActivity extends AppCompatActivity {
    protected String TAG = BaseAbstractActivity.class.getSimpleName();
    private boolean activityResumed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView(savedInstanceState);
        onStartIntent(true, getIntent());
        hideSystemActionBar();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onStartIntent(false, intent);
    }

    protected void beforeSetContentView(Bundle savedInstanceState) {
        AppDpiUtils.setCustomDensity(getResources());
    }

    protected abstract int getContentLayoutRes();

    protected void onStartIntent(boolean firstCreate, Intent intent) {
        if(intent != null){
            onStartIntentAvailable(firstCreate, intent);
        }
    }

    protected void onStartIntentAvailable(boolean firstCreate, Intent intent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void hideSystemActionBar(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityResumed = false;
    }

    public boolean isActivityResumed(){
        return activityResumed;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
