package com.soft.zigbang.src.house.find.detail.review.interfaces;

import com.soft.zigbang.src.house.find.detail.review.models.ReviewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReviewRetrofitInterface {
    @GET("/apart/{apartIndex}/review")
    Call<ReviewResponse> getReviewList(@Path("apartIndex") int apartIndex, @Query("pageNo") int pageNo);

}
