package com.alirezamh.android.simpleapp.ui.deliveryDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alirezamh.android.simpleapp.R;
import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.ui.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class DeliveryDetailsActivity extends BaseActivity implements DeliveryDetailsView, OnMapReadyCallback {
    @Inject
    DeliveryDetailsPresenter presenter;

    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.title) TextView title;

    SupportMapFragment mapView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        mapView = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            presenter.setDelivery(extras.getParcelable("delivery"));
        }else {
            finish();
        }

        initialize(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        presenter.onViewPrepared();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        presenter.initializeMap(googleMap);
    }

    @Override
    public void updateDate(Delivery delivery) {
        getSupportActionBar().setTitle(getString(R.string.delivery_details));
        title.setText(getString(R.string.deliveries_list_item_title, delivery.getDescription(), delivery.getLocation().getAddress()));
        Glide.with(imageView.getContext())
                .load(delivery.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    @Override
    public void initializeMap(GoogleMap googleMap, Delivery delivery) {
        LatLng latLng = new LatLng(delivery.getLocation().getLat(), delivery.getLocation().getLng());
        googleMap.addMarker(new MarkerOptions().position(latLng).title(delivery.getLocation().getAddress()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}
