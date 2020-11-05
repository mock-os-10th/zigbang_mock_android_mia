package com.soft.zigbang.src.house.find.interfaces;

import com.soft.zigbang.src.house.find.models.FindResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FindMapRetrofitInterface {
    @GET("/apart")
    Call<FindResponse> getApartList(@Query("sellType") String sellType);

    @GET("/apart/{apartIndex}")
    Call<FindResponse> getApart(@Path("apartIndex") int apartIndex);
}
