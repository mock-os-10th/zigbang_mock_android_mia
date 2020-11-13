package com.soft.zigbang.src.house.find.detail.review;


import android.os.Bundle;
import android.view.View;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.find.detail.review.interfaces.ReviewActivityView;
import com.soft.zigbang.src.house.find.detail.review.models.ReviewResponse;

import java.util.List;

public class ReviewActivity extends BaseActivity implements ReviewActivityView {

    private int mApartIndex = 0;
    private ReviewService mReviewService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mApartIndex = getIntent().getIntExtra("apartIndex", 0);
        mReviewService = new ReviewService(this);

        showProgressDialog();
        mReviewService.getReviewList(mApartIndex, 10);
    }

    @Override
    public void getReviewListSuccess(int code, List<ReviewResponse.Result> results) {
        hideProgressDialog();
        results.toString();
    }

    @Override
    public void getReviewListFailure(String message) {
        hideProgressDialog();
        showCustomToast("댓글을 조회할 수 없습니다.");
        finish();
    }

    public void reviewOnClick(View view) {
        switch (view.getId()) {
            case R.id.review_btn_post:
                break;
        }
    }
}