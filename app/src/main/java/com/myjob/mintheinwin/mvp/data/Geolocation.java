package com.myjob.mintheinwin.mvp.data;

import com.google.gson.annotations.SerializedName;

public class Geolocation {

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
