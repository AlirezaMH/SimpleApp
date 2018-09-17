package com.alirezamh.android.simpleapp.ui.main;


import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;

import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.ui.base.BaseView;

import java.util.List;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public interface MainView extends BaseView {
    void setItems(List<Delivery> deliveries);
    void showLoading();
    void hideLoading();
    Snackbar getSnackbar(@StringRes int resId, int duration);

}
