package com.alirezamh.android.simpleapp.di.component;

import android.app.Application;
import android.content.Context;

import com.alirezamh.android.simpleapp.App;
import com.alirezamh.android.simpleapp.data.DataManager;
import com.alirezamh.android.simpleapp.di.ApplicationContext;
import com.alirezamh.android.simpleapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/19/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}