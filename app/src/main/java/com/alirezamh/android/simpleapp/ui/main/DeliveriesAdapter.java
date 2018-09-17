package com.alirezamh.android.simpleapp.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.alirezamh.android.simpleapp.R;
import com.alirezamh.android.simpleapp.data.model.Delivery;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class DeliveriesAdapter extends RecyclerView.Adapter<DeliveriesAdapter.ViewHolder> {
    private List<Delivery> items;
    private Callback callback;

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.title) TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public DeliveriesAdapter(List<Delivery> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Delivery item = items.get(position);
        holder.name.setText(holder.imageView.getContext().getString(R.string.deliveries_list_item_title, item.getDescription(), item.getLocation().getAddress()));
        holder.itemView.setOnClickListener(view -> {
            if(callback != null) callback.onItemClick(item, view);
        });
        Glide.with(holder.imageView.getContext())
                .load(item.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Delivery> items){
        if(items == null) return;
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void appendItems(List<Delivery> items){
        if(items == null) return;
        int startIndex = this.items.size();
        for (Delivery delivery: items) {
            if(!this.items.contains(delivery)){
                this.items.add(delivery);
            }
        }
        notifyItemRangeInserted(startIndex, this.items.size() - startIndex);
    }

    public interface Callback {
        void onItemClick(Delivery item, View view);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
