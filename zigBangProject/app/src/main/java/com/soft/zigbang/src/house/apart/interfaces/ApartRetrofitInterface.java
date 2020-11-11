package com.soft.zigbang.src.house.apart.interfaces;

import com.soft.zigbang.src.house.apart.models.UserResponse;
import com.soft.zigbang.src.main.models.ApartInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApartRetrofitInterface {
    @GET("/user/{userno}/info")
    Call<UserResponse> getUserInfo(@Path("userno") int userNo);

    @GET("/apartinfo")
    Call<ApartInfoResponse> getApartInfo();

}
