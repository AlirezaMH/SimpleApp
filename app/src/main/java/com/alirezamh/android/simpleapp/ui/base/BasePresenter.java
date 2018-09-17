package com.alirezamh.android.simpleapp.ui.base;


import com.alirezamh.android.simpleapp.data.DataManager;

import javax.inject.Inject;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/18/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class BasePresenter<V extends BaseView> {
    private final DataManager mDataManager;

    private V view;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public V getView() {
        return view;
    }

    public void onAttach(V view){
        this.view = view;
    }

    public void onDetach(){
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public void onViewPrepared(){

    }
}
