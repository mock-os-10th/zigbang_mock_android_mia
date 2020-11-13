package com.soft.zigbang.src.house.apart.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;

    private String mApartName;
    private double mMinPrice;
    private double mMaxPrice;
    private int mApartIndex;

//    public MyItem(double lat, double lng, String title, String snippet) {
//        this.mPosition = new LatLng(lat, lng);
//        this.mTitle = title;
//        this.mSnippet = snippet;
//    }

    public MyItem(double lat, double lng) {
        this.mPosition = new LatLng(lat, lng);
        this.mTitle = "title";
        this.mSnippet = "snippet";
    }
    public MyItem(double lat, double lng, String apartName, double minPrice, double maxPrice, int apartIndex) {
        this.mPosition = new LatLng(lat, lng);
        this.mApartName = apartName;
        this.mMinPrice = minPrice;
        this.mMaxPrice = maxPrice;
        this.mApartIndex = apartIndex;
        this.mTitle = "title";
        this.mSnippet = "snippet";
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public String getApartName() {
        return mApartName;
    }


    public double getMinPrice() {
        return mMinPrice;
    }


    public double getMaxPrice() {
        return mMaxPrice;
    }

    public int getApartIndex() {
        return mApartIndex;
    }
}
