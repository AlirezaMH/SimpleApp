package com.alirezamh.android.simpleapp.data;

import android.content.Context;

import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.data.network.RetrofitInstance;
import com.alirezamh.android.simpleapp.data.service.DeliveryService;
import com.alirezamh.android.simpleapp.di.ApplicationContext;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */

@Singleton
public class DataManager {
    private static final int DELIVERIES_ITEMS_PER_REQUEST = 20;
    private Context context;
    private SharedPrefsHelper sharedPrefsHelper;


    @Inject
    public DataManager(@ApplicationContext Context context, SharedPrefsHelper sharedPrefsHelper) {
        this.context = context;
        this.sharedPrefsHelper = sharedPrefsHelper;
    }

    /**
     * get Deliveries Data
     * @param page      Start from 0
     * @param callback  Callback
     */
    public void getDeliveries(int page, Callback<List<Delivery>> callback){
        DeliveryService service = RetrofitInstance.getRetrofitInstance(context).create(DeliveryService.class);
        Call<List<Delivery>> call = service.getDeliveries(DELIVERIES_ITEMS_PER_REQUEST, DELIVERIES_ITEMS_PER_REQUEST * page);
        call.enqueue(callback);
    }


}
