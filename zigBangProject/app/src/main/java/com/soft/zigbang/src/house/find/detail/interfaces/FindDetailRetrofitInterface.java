package com.soft.zigbang.src.house.find.detail.interfaces;

import com.soft.zigbang.src.house.find.detail.models.LikeResponse;
import com.soft.zigbang.src.house.find.models.FindResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface FindDetailRetrofitInterface {
//    @GET("/apart/{apartIndex}")
//    Call<FindResponse> getApart(@Path("apartIndex") int apartIndex);

    @PATCH("/apart/{apartIndex}/like")
    Call<LikeResponse> patchApartLike(@Path("apartIndex") int apartIndex);
}
