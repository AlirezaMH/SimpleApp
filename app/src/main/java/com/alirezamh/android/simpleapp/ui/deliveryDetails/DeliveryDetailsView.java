package com.alirezamh.android.simpleapp.ui.deliveryDetails;


import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.ui.base.BaseView;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public interface DeliveryDetailsView extends BaseView {
    void updateDate(Delivery delivery);
    void initializeMap(GoogleMap googleMap, Delivery delivery);
}
