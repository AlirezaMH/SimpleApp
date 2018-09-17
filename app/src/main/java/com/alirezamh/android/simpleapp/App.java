package com.alirezamh.android.simpleapp;

import android.app.Application;

import com.alirezamh.android.simpleapp.data.DataManager;
import com.alirezamh.android.simpleapp.di.component.ApplicationComponent;
import com.alirezamh.android.simpleapp.di.component.DaggerApplicationComponent;
import com.alirezamh.android.simpleapp.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class App extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
