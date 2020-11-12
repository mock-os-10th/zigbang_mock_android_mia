package com.soft.zigbang.src.house.oneroom.models;

public class OneRoomCntBody {
    private double minLongitude;
    private double maxLongitude;
    private double minLatitude;
    private double maxLatitude;

    public OneRoomCntBody(double minLongitude, double maxLongitude, double minLatitude, double maxLatitude) {
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
    }
}
