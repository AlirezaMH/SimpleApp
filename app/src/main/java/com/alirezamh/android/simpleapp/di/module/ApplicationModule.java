package com.alirezamh.android.simpleapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.alirezamh.android.simpleapp.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/19/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs(){
        return mApplication.getSharedPreferences("APP_PREFS_NAME", Context.MODE_PRIVATE);
    }


}
