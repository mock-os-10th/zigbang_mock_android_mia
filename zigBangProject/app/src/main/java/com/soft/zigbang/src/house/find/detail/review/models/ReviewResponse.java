
package com.soft.zigbang.src.house.find.detail.review.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ReviewResponse {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private List<Result> mResult;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public Boolean getIsSuccess() {
        return mIsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        mIsSuccess = isSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<Result> getResult() {
        return mResult;
    }

    public void setResult(List<Result> result) {
        mResult = result;
    }

    public static class Result {

        @SerializedName("review")
        private List<Review> mReview;
        @SerializedName("reviewsum")
        private List<Reviewsum> mReviewsum;

        public List<Review> getReview() {
            return mReview;
        }

        public void setReview(List<Review> review) {
            mReview = review;
        }

        public List<Reviewsum> getReviewsum() {
            return mReviewsum;
        }

        public void setReviewsum(List<Reviewsum> reviewsum) {
            mReviewsum = reviewsum;
        }

    }

    public static class Review {

        @SerializedName("apartReviewIndex")
        private Long mApartReviewIndex;
        @SerializedName("content1")
        private String mContent1;
        @SerializedName("content2")
        private String mContent2;
        @SerializedName("content3")
        private String mContent3;
        @SerializedName("content4")
        private String mContent4;
        @SerializedName("content5")
        private String mContent5;
        @SerializedName("gender")
        private String mGender;
        @SerializedName("isHaved")
        private String mIsHaved;
        @SerializedName("liveAt")
        private String mLiveAt;
        @SerializedName("score1")
        private Double mScore1;
        @SerializedName("score2")
        private Double mScore2;
        @SerializedName("score3")
        private Double mScore3;
        @SerializedName("score4")
        private Double mScore4;
        @SerializedName("score5")
        private Double mScore5;
        @SerializedName("uploadTime")
        private String mUploadTime;

        public Long getApartReviewIndex() {
            return mApartReviewIndex;
        }

        public void setApartReviewIndex(Long apartReviewIndex) {
            mApartReviewIndex = apartReviewIndex;
        }

        public String getContent1() {
            return mContent1;
        }

        public void setContent1(String content1) {
            mContent1 = content1;
        }

        public String getContent2() {
            return mContent2;
        }

        public void setContent2(String content2) {
            mContent2 = content2;
        }

        public String getContent3() {
            return mContent3;
        }

        public void setContent3(String content3) {
            mContent3 = content3;
        }

        public String getContent4() {
            return mContent4;
        }

        public void setContent4(String content4) {
            mContent4 = content4;
        }

        public String getContent5() {
            return mContent5;
        }

        public void setContent5(String content5) {
            mContent5 = content5;
        }

        public String getGender() {
            return mGender;
        }

        public void setGender(String gender) {
            mGender = gender;
        }

        public String getIsHaved() {
            return mIsHaved;
        }

        public void setIsHaved(String isHaved) {
            mIsHaved = isHaved;
        }

        public String getLiveAt() {
            return mLiveAt;
        }

        public void setLiveAt(String liveAt) {
            mLiveAt = liveAt;
        }

        public Double getScore1() {
            return mScore1;
        }

        public void setScore1(Double score1) {
            mScore1 = score1;
        }

        public Double getScore2() {
            return mScore2;
        }

        public void setScore2(Double score2) {
            mScore2 = score2;
        }

        public Double getScore3() {
            return mScore3;
        }

        public void setScore3(Double score3) {
            mScore3 = score3;
        }

        public Double getScore4() {
            return mScore4;
        }

        public void setScore4(Double score4) {
            mScore4 = score4;
        }

        public Double getScore5() {
            return mScore5;
        }

        public void setScore5(Double score5) {
            mScore5 = score5;
        }

        public String getUploadTime() {
            return mUploadTime;
        }

        public void setUploadTime(String uploadTime) {
            mUploadTime = uploadTime;
        }

    }

    public static class Reviewsum {

        @SerializedName("apartIndex")
        private Long mApartIndex;
        @SerializedName("reviewAvg1")
        private Double mReviewAvg1;
        @SerializedName("reviewAvg2")
        private Double mReviewAvg2;
        @SerializedName("reviewAvg3")
        private Double mReviewAvg3;
        @SerializedName("reviewAvg4")
        private Double mReviewAvg4;
        @SerializedName("reviewAvg5")
        private Double mReviewAvg5;
        @SerializedName("reviewNum")
        private Long mReviewNum;

        public Long getApartIndex() {
            return mApartIndex;
        }

        public void setApartIndex(Long apartIndex) {
            mApartIndex = apartIndex;
        }

        public Double getReviewAvg1() {
            return mReviewAvg1;
        }

        public void setReviewAvg1(Double reviewAvg1) {
            mReviewAvg1 = reviewAvg1;
        }

        public Double getReviewAvg2() {
            return mReviewAvg2;
        }

        public void setReviewAvg2(Double reviewAvg2) {
            mReviewAvg2 = reviewAvg2;
        }

        public Double getReviewAvg3() {
            return mReviewAvg3;
        }

        public void setReviewAvg3(Double reviewAvg3) {
            mReviewAvg3 = reviewAvg3;
        }

        public Double getReviewAvg4() {
            return mReviewAvg4;
        }

        public void setReviewAvg4(Double reviewAvg4) {
            mReviewAvg4 = reviewAvg4;
        }

        public Double getReviewAvg5() {
            return mReviewAvg5;
        }

        public void setReviewAvg5(Double reviewAvg5) {
            mReviewAvg5 = reviewAvg5;
        }

        public Long getReviewNum() {
            return mReviewNum;
        }

        public void setReviewNum(Long reviewNum) {
            mReviewNum = reviewNum;
        }

    }
}
