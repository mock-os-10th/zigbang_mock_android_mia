package com.soft.zigbang.src.splash.interfaces;

import com.soft.zigbang.src.splash.models.SplashResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface SplashRetrofitInterface {
    @GET("/jwt")
    Call<SplashResponse> getJwt();
}
