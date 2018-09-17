package com.alirezamh.android.simpleapp.ui.main;


import android.support.design.widget.Snackbar;

import com.alirezamh.android.simpleapp.R;
import com.alirezamh.android.simpleapp.data.DataManager;
import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.ui.base.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewPrepared() {
        showItems(0);
        super.onViewPrepared();
    }

    public void showItems(int page){
        getView().showLoading();
        getDataManager().getDeliveries(page, new Callback<List<Delivery>>() {
            @Override
            public void onResponse(Call<List<Delivery>> call, Response<List<Delivery>> response) {
                if(response.isSuccessful()){
                    getView().setItems(response.body());
                }else{
                    getView().getSnackbar(R.string.server_error, 8000).setAction(R.string.retry, (view) -> showItems(page)).show();
                }
                getView().hideLoading();
            }

            @Override
            public void onFailure(Call<List<Delivery>> call, Throwable t) {
                getView().hideLoading();
                getView().getSnackbar(R.string.connection_error, Snackbar.LENGTH_LONG).setAction(R.string.retry, (view) -> showItems(page)).show();
            }
        });
    }
}
