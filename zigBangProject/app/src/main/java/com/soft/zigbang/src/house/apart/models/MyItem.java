package com.soft.zigbang.src.house.apart.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;

    private String mApartName;
    private int mMinPrice;
    private int mMaxPrice;

//    public MyItem(double lat, double lng, String title, String snippet) {
//        this.mPosition = new LatLng(lat, lng);
//        this.mTitle = title;
//        this.mSnippet = snippet;
//    }

    public MyItem(double lat, double lng, String apartName, int minPrice, int maxPrice) {
        this.mPosition = new LatLng(lat, lng);
        this.mApartName = apartName;
        this.mMinPrice = minPrice;
        this.mMaxPrice = maxPrice;
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


    public int getMinPrice() {
        return mMinPrice;
    }


    public int getMaxPrice() {
        return mMaxPrice;
    }

}
