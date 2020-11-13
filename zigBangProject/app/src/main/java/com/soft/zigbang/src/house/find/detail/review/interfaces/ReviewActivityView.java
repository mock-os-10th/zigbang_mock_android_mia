package com.soft.zigbang.src.house.find.detail.review.interfaces;

import com.soft.zigbang.src.house.find.detail.review.models.ReviewResponse;

import java.util.List;

public interface ReviewActivityView {
    void getReviewListSuccess(int code, List<ReviewResponse.Result> results);
    void getReviewListFailure(String message);
}
