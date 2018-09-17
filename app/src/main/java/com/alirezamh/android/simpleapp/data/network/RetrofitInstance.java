package com.alirezamh.android.simpleapp.data.network;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.alirezamh.android.simpleapp.utils.Utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class RetrofitInstance {
    private static final String TAG = RetrofitInstance.class.getSimpleName();
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_PRAGMA = "Pragma";
    private static final String BASE_URL = "https://mock-api-mobile.dev.lalamove.com/";
    private static Retrofit retrofit;


    public RetrofitInstance(Context context){

    }

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(provideOfflineCacheInterceptor(context))
                    .addNetworkInterceptor(provideCacheInterceptor(context))
                    .cache(provideCache(context))
                    .build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }

    private static Cache provideCache(Context context) {
        Cache cache = null;

        try {
            cache = new Cache(new File(context.getCacheDir(), "http-cache"),
                    10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Log.e(TAG, "Could not create Cache!");
        }

        return cache;
    }

    private static Interceptor provideCacheInterceptor(Context context) {
        return chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl;

            if (Utility.isNetworkConnected(context)) {
                cacheControl = new CacheControl.Builder()
                        .maxAge(0, TimeUnit.SECONDS)
                        .build();
            } else {
                cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();
            }

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();

        };
    }

    private static Interceptor provideOfflineCacheInterceptor(Context context) {
        return chain -> {
            Request request = chain.request();

            if (!Utility.isNetworkConnected(context)) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build();
            }

            return chain.proceed(request);
        };
    }
}
