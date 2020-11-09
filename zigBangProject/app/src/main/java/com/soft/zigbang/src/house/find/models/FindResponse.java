package com.soft.zigbang.src.house.find.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Preconditions;

import java.io.Serializable;
import java.util.List;

public class FindResponse implements Serializable {
    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private List<FindResponse.Result> mResult;
    @SerializedName("school")
    private List<FindResponse.School> mSchool;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
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

    public List<FindResponse.Result> getResult() {
        return mResult;
    }

    public void setResult(List<FindResponse.Result> result) {
        mResult = result;
    }

    public List<FindResponse.School> getSchool() {
        return mSchool;
    }

    public void setSchool(List<FindResponse.School> school) {
        mSchool = school;
    }

    public static class Result implements Serializable {

        @SerializedName("apartIndex")
        private int mApartIndex;
        @SerializedName("dong")
        private String mDong;
        @SerializedName("enterAt")
        private String mEnterAt;
        @SerializedName("gu")
        private String mGu;
        @SerializedName("latitude")
        private Double mLatitude;
        @SerializedName("liveNum")
        private Long mLiveNum;
        @SerializedName("longitude")
        private Double mLongitude;
        @SerializedName("name")
        private String mName;
        @SerializedName("si")
        private String mSi;
        @SerializedName("address")
        private String mAddress;

        @SerializedName("image")
        private String mImage;
        @SerializedName("reviewAvg")
        private float mReviewAvg;
        @SerializedName("content1")
        private String mContent1;
        @SerializedName("reviewNum")
        private int mReviewNum;
        @SerializedName("perA")
        private int mPerA;
        @SerializedName("perB")
        private int mPerB;
        @SerializedName("guNum")
        private int mGuNum;
        @SerializedName("dongNum")
        private int mDongNum;
        @SerializedName("guRank")
        private String mGuRank;
        @SerializedName("dongRank")
        private String mDongRank;
        @SerializedName("isLike")
        private String mIsLike;

        public int getApartIndex() {
            return mApartIndex;
        }

        public void setApartIndex(int apartIndex) {
            mApartIndex = apartIndex;
        }

        public String getDong() {
            return mDong;
        }

        public void setDong(String dong) {
            mDong = dong;
        }

        public String getEnterAt() {
            return mEnterAt;
        }

        public void setEnterAt(String enterAt) {
            mEnterAt = enterAt;
        }

        public String getGu() {
            return mGu;
        }

        public void setGu(String gu) {
            mGu = gu;
        }

        public Double getLatitude() {
            return mLatitude;
        }

        public void setLatitude(Double latitude) {
            mLatitude = latitude;
        }

        public Long getLiveNum() {
            return mLiveNum;
        }

        public void setLiveNum(Long liveNum) {
            mLiveNum = liveNum;
        }

        public Double getLongitude() {
            return mLongitude;
        }

        public void setLongitude(Double longitude) {
            mLongitude = longitude;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getSi() {
            return mSi;
        }

        public void setSi(String si) {
            mSi = si;
        }

        public String getAddress() {
            return mAddress;
        }

        public void setAddress(String address) {
            mAddress = address;
        }

        public String getImage() {
            return mImage;
        }

        public void setImage(String image) {
            this.mImage = image;
        }

        public float getReviewAvg() {
            return mReviewAvg;
        }

        public void setReviewAvg(float reviewAvg) {
            this.mReviewAvg = reviewAvg;
        }

        public String getContent1() {
            return mContent1;
        }

        public void setContent1(String content1) {
            this.mContent1 = content1;
        }

        public int getReviewNum() {
            return mReviewNum;
        }

        public void setReviewNum(int reviewNum) {
            this.mReviewNum = reviewNum;
        }

        public int getPerA() {
            return mPerA;
        }

        public void setPerA(int perA) {
            this.mPerA = perA;
        }

        public int getPerB() {
            return mPerB;
        }

        public void setPerB(int perB) {
            this.mPerB = perB;
        }

        public int getGuNum() {
            return mGuNum;
        }

        public void setGuNum(int guNum) {
            this.mGuNum = guNum;
        }

        public int getDongNum() {
            return mDongNum;
        }

        public void setDongNum(int dongNum) {
            this.mDongNum = dongNum;
        }

        public String getGuRank() {
            return mGuRank;
        }

        public void setGuRank(String guRank) {
            this.mGuRank = guRank;
        }

        public String getDongRank() {
            return mDongRank;
        }

        public void setDongRank(String dongRank) {
            this.mDongRank = dongRank;
        }

        public String getIsLike() {
            return mIsLike;
        }

        public void setIsLike(String isLike) {
            this.mIsLike = isLike;
        }
    }

    public static class School implements Serializable {
        @SerializedName("placeIndex")
        private int mPlaceIndex;
        @SerializedName("name")
        private String mName;
        @SerializedName("distance")
        private int mDistance;

        public int getPlaceIndex() {
            return mPlaceIndex;
        }

        public void setPlaceIndex(int placeIndex) {
            this.mPlaceIndex = placeIndex;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            this.mName = name;
        }

        public int getDistance() {
            return mDistance;
        }

        public void setDistance(int distance) {
            this.mDistance = distance;
        }
    }
}
