package com.alirezamh.android.simpleapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/16/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class Delivery implements Parcelable {
    private int id;
    private String description;
    private String imageUrl;
    private Location location;


    protected Delivery(Parcel in) {
        id = in.readInt();
        description = in.readString();
        imageUrl = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<Delivery> CREATOR = new Creator<Delivery>() {
        @Override
        public Delivery createFromParcel(Parcel in) {
            return new Delivery(in);
        }

        @Override
        public Delivery[] newArray(int size) {
            return new Delivery[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeParcelable(location, i);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Delivery && ((Delivery) obj).getId() == getId();
    }
}
