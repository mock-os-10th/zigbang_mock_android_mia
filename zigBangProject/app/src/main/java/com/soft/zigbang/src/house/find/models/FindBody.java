package com.soft.zigbang.src.house.find.models;

import com.google.gson.annotations.SerializedName;

public class FindBody {
    @SerializedName("sellType")
    private String sellType;
    @SerializedName("acreage")
    private int acreage;
    @SerializedName("enterAt")
    private int enterAt;
    @SerializedName("liveNum")
    private int liveNum;

    public FindBody(String sellType) {
        this.sellType = sellType;
    }

    public FindBody(String sellType, int acreage, int enterAt, int liveNum) {
        this.sellType = sellType;
        this.acreage = acreage;
        this.enterAt = enterAt;
        this.liveNum = liveNum;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public int getAcreage() {
        return acreage;
    }

    public void setAcreage(int acreage) {
        this.acreage = acreage;
    }

    public int getEnterAt() {
        return enterAt;
    }

    public void setEnterAt(int enterAt) {
        this.enterAt = enterAt;
    }

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }
}
