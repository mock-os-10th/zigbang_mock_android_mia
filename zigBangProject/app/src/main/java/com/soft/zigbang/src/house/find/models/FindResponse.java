package com.soft.zigbang.src.house.find.models;

import com.google.gson.annotations.SerializedName;
import com.soft.zigbang.src.house.models.HouseResponse;

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

    public static class Result implements Serializable{

        @SerializedName("apartIndex")
        private Long mApartIndex;
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

        public Long getApartIndex() {
            return mApartIndex;
        }

        public void setApartIndex(Long apartIndex) {
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

    }
}
