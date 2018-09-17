package com.alirezamh.android.simpleapp.ui.base;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

import com.alirezamh.android.simpleapp.App;
import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.di.component.ActivityComponent;
import com.alirezamh.android.simpleapp.di.component.DaggerActivityComponent;
import com.alirezamh.android.simpleapp.di.module.ActivityModule;
import com.alirezamh.android.simpleapp.ui.deliveryDetails.DeliveryDetailsActivity;
import com.alirezamh.android.simpleapp.utils.PermissionRequest;
import com.alirezamh.android.simpleapp.utils.Utility;

import butterknife.Unbinder;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent();
        Utility.init(this);
    }

    public ActivityComponent getActivityComponent() {
        if(mActivityComponent == null){
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(((App) getApplication()).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public boolean isNetworkConnected(){
        return Utility.isNetworkConnected(getApplicationContext());
    }

    public void onFragmentAttached() {

    }

    public void onFragmentDetached(String tag) {

    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public void openDeliveryDetailsActivity(Delivery delivery, View view){
        PermissionRequest permissionRequest = new PermissionRequest(this, "android.permission.ACCESS_FINE_LOCATION");
        Runnable runnable = () -> {
            Intent intent = new Intent(this, DeliveryDetailsActivity.class);
            intent.putExtra("delivery", delivery);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(view, ViewCompat.getTransitionName(view))).toBundle());
            } else {
                startActivity(intent);
            }
        };

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            permissionRequest.setOnGrantListener(runnable);
        }

        if(permissionRequest.isGranted()){
            runnable.run();
        }else{
            permissionRequest.send();
        }
    }



    protected abstract void initialize(@Nullable Bundle savedInstanceState);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
