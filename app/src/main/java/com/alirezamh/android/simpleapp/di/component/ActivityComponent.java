package com.alirezamh.android.simpleapp.di.component;

import com.alirezamh.android.simpleapp.ui.deliveryDetails.DeliveryDetailsActivity;
import com.alirezamh.android.simpleapp.ui.main.MainActivity;
import com.alirezamh.android.simpleapp.di.PerActivity;
import com.alirezamh.android.simpleapp.di.module.ActivityModule;

import dagger.Component;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/19/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(DeliveryDetailsActivity activity);
}