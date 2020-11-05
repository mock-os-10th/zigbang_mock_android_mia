package com.soft.zigbang.src.house.interfaces;

import com.soft.zigbang.src.house.find.models.FindResponse;
import com.soft.zigbang.src.house.models.HouseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HouseRetrofitInterface {
    @GET("/apart")
    Call<FindResponse> getApartList(@Query("sellType") String sellType);
}