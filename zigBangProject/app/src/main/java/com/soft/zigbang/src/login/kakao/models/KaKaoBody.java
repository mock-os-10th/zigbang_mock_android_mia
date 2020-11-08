package com.soft.zigbang.src.login.kakao.models;

import com.google.gson.annotations.SerializedName;

public class KaKaoBody {
    @SerializedName("accessToken")
    private String mAccessToken;

    public KaKaoBody(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }
}
