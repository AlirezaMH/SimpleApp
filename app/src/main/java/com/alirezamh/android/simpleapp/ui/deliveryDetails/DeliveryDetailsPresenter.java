package com.alirezamh.android.simpleapp.ui.deliveryDetails;


import com.alirezamh.android.simpleapp.data.DataManager;
import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.ui.base.BasePresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class DeliveryDetailsPresenter extends BasePresenter<DeliveryDetailsView> {
    private Delivery delivery;
    public DeliveryDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewPrepared() {
        getView().updateDate(delivery);
        super.onViewPrepared();
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
    }

    public void initializeMap(GoogleMap googleMap){
        getView().initializeMap(googleMap, delivery);
    }
}
