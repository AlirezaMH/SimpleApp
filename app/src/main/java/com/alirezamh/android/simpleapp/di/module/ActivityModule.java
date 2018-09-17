package com.alirezamh.android.simpleapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.alirezamh.android.simpleapp.data.DataManager;
import com.alirezamh.android.simpleapp.di.ActivityContext;
import com.alirezamh.android.simpleapp.ui.deliveryDetails.DeliveryDetailsPresenter;
import com.alirezamh.android.simpleapp.ui.main.DeliveriesAdapter;
import com.alirezamh.android.simpleapp.ui.main.MainPresenter;

import java.util.ArrayList;
import java.util.Collections;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/19/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    DeliveriesAdapter provideDeliveriesAdapter() {
        return new DeliveriesAdapter(new ArrayList<>());
    }

    @Provides
    MainPresenter provideMainPresenter(DataManager dataManager){
        return new MainPresenter(dataManager);
    }

    @Provides
    DeliveryDetailsPresenter provideDeliveryDetailsPresenter(DataManager dataManager){
        return new DeliveryDetailsPresenter(dataManager);
    }
}
