package com.alirezamh.android.simpleapp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.alirezamh.android.simpleapp.R;
import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.alirezamh.android.simpleapp.ui.base.BaseActivity;
import com.alirezamh.android.simpleapp.utils.EndlessRecyclerViewScrollListener;
import com.alirezamh.android.simpleapp.utils.Utility;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, DeliveriesAdapter.Callback {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    DeliveriesAdapter adapter;

    @Inject
    MainPresenter presenter;

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        adapter.setCallback(this);
        initialize(savedInstanceState);
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle(getString(R.string.main_activity_title));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d(TAG, "onLoadMore Page: " + page);
                presenter.showItems(page);
            }
        };
        list.addOnScrollListener(scrollListener);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if(!Utility.isNetworkConnected(this)){
                getSnackbar(R.string.connection_error, Snackbar.LENGTH_LONG);
            }
            presenter.showItems(0);
        });

        presenter.onViewPrepared();
    }

    @Override
    public void setItems(List<Delivery> deliveries) {
        if(!isFinishing()){
            adapter.appendItems(deliveries);
        }
    }

    @Override
    public void onItemClick(Delivery delivery, View view) {
        openDeliveryDetailsActivity(delivery, view);
    }

    @Override
    public void showLoading() {
        if(!isFinishing()){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if(!isFinishing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public Snackbar getSnackbar(@StringRes int resId, int duration) {
        return Snackbar.make(getCurrentFocus(), resId, duration);
    }
}
