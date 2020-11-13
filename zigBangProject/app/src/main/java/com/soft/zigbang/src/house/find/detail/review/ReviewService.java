package com.soft.zigbang.src.house.find.detail.review;

import com.soft.zigbang.src.house.find.detail.review.interfaces.ReviewActivityView;
import com.soft.zigbang.src.house.find.detail.review.interfaces.ReviewRetrofitInterface;
import com.soft.zigbang.src.house.find.detail.review.models.ReviewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class ReviewService {
    private final ReviewActivityView reviewActivityView;

    public ReviewService(ReviewActivityView reviewActivityView) {
        this.reviewActivityView = reviewActivityView;
    }

    void getReviewList(int apartIndex, int pageNo) {
        final ReviewRetrofitInterface reviewRetrofitInterface = getRetrofit().create(ReviewRetrofitInterface.class);
        reviewRetrofitInterface.getReviewList(apartIndex, pageNo).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                final ReviewResponse reviewResponse = response.body();
                if(reviewResponse == null) {
                    reviewActivityView.getReviewListFailure(null);
                    return;
                }
                reviewActivityView.getReviewListSuccess(reviewResponse.getCode(), reviewResponse.getResult());
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                reviewActivityView.getReviewListFailure(null);
            }
        });
    }
}
