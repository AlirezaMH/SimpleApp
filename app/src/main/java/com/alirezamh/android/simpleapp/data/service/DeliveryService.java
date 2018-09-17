package com.alirezamh.android.simpleapp.data.service;

import com.alirezamh.android.simpleapp.data.model.Delivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public interface DeliveryService {

    /**
     * Get List of deliveries
     * @param limit
     * @param offset
     * @return
     */
    @GET("deliveries")
    Call<List<Delivery>> getDeliveries(@Query("limit") int limit, @Query("offset") int offset);
}
