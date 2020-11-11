
package com.soft.zigbang.src.house.find.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FindResponse2 {

    @SerializedName("code")
    private int mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private List<Result> mResult;
    @SerializedName("school")
    private List<School> mSchool;

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

    public List<School> getSchool() {
        return mSchool;
    }

    public void setSchool(List<School> school) {
        mSchool = school;
    }

    public static class Result {
    
        @SerializedName("address")
        private String mAddress;
        @SerializedName("apartIndex")
        private int mApartIndex;
        @SerializedName("content1")
        private String mContent1;
        @SerializedName("dong")
        private String mDong;
        @SerializedName("dongNum")
        private int mDongNum;
        @SerializedName("dongRank")
        private int mDongRank;
        @SerializedName("enterAt")
        private String mEnterAt;
        @SerializedName("gu")
        private String mGu;
        @SerializedName("guNum")
        private int mGuNum;
        @SerializedName("guRank")
        private int mGuRank;
        @SerializedName("image")
        private String mImage;
        @SerializedName("isLike")
        private String mIsLike;
        @SerializedName("latitude")
        private Double mLatitude;
        @SerializedName("like")
        private String mLike;
        @SerializedName("liveNum")
        private int mLiveNum;
        @SerializedName("longitude")
        private Double mLongitude;
        @SerializedName("name")
        private String mName;
        @SerializedName("perA")
        private int mPerA;
        @SerializedName("perB")
        private int mPerB;
        @SerializedName("reviewAvg")
        private Double mReviewAvg;
        @SerializedName("reviewNum")
        private int mReviewNum;
        @SerializedName("si")
        private String mSi;
    
        public String getAddress() {
            return mAddress;
        }
    
        public void setAddress(String address) {
            mAddress = address;
        }
    
        public int getApartIndex() {
            return mApartIndex;
        }
    
        public void setApartIndex(int apartIndex) {
            mApartIndex = apartIndex;
        }
    
        public String getContent1() {
            return mContent1;
        }
    
        public void setContent1(String content1) {
            mContent1 = content1;
        }
    
        public String getDong() {
            return mDong;
        }
    
        public void setDong(String dong) {
            mDong = dong;
        }
    
        public int getDongNum() {
            return mDongNum;
        }
    
        public void setDongNum(int dongNum) {
            mDongNum = dongNum;
        }
    
        public int getDongRank() {
            return mDongRank;
        }
    
        public void setDongRank(int dongRank) {
            mDongRank = dongRank;
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
    
        public int getGuNum() {
            return mGuNum;
        }
    
        public void setGuNum(int guNum) {
            mGuNum = guNum;
        }
    
        public int getGuRank() {
            return mGuRank;
        }
    
        public void setGuRank(int guRank) {
            mGuRank = guRank;
        }
    
        public String getImage() {
            return mImage;
        }
    
        public void setImage(String image) {
            mImage = image;
        }
    
        public String getIsLike() {
            return mIsLike;
        }
    
        public void setIsLike(String isLike) {
            mIsLike = isLike;
        }
    
        public Double getLatitude() {
            return mLatitude;
        }
    
        public void setLatitude(Double latitude) {
            mLatitude = latitude;
        }
    
        public String getLike() {
            return mLike;
        }
    
        public void setLike(String like) {
            mLike = like;
        }
    
        public int getLiveNum() {
            return mLiveNum;
        }
    
        public void setLiveNum(int liveNum) {
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
    
        public int getPerA() {
            return mPerA;
        }
    
        public void setPerA(int perA) {
            mPerA = perA;
        }
    
        public int getPerB() {
            return mPerB;
        }
    
        public void setPerB(int perB) {
            mPerB = perB;
        }
    
        public Double getReviewAvg() {
            return mReviewAvg;
        }
    
        public void setReviewAvg(Double reviewAvg) {
            mReviewAvg = reviewAvg;
        }
    
        public int getReviewNum() {
            return mReviewNum;
        }
    
        public void setReviewNum(int reviewNum) {
            mReviewNum = reviewNum;
        }
    
        public String getSi() {
            return mSi;
        }
    
        public void setSi(String si) {
            mSi = si;
        }
    
    }

    public static class School {
    
        @SerializedName("distance")
        private int mDistance;
        @SerializedName("isCoed")
        private String mIsCoed;
        @SerializedName("isPublic")
        private String mIsPublic;
        @SerializedName("name")
        private String mName;
        @SerializedName("placeIndex")
        private int mPlaceIndex;
    
        public int getDistance() {
            return mDistance;
        }
    
        public void setDistance(int distance) {
            mDistance = distance;
        }
    
        public String getIsCoed() {
            return mIsCoed;
        }
    
        public void setIsCoed(String isCoed) {
            mIsCoed = isCoed;
        }
    
        public String getIsPublic() {
            return mIsPublic;
        }
    
        public void setIsPublic(String isPublic) {
            mIsPublic = isPublic;
        }
    
        public String getName() {
            return mName;
        }
    
        public void setName(String name) {
            mName = name;
        }
    
        public int getPlaceIndex() {
            return mPlaceIndex;
        }
    
        public void setPlaceIndex(int placeIndex) {
            mPlaceIndex = placeIndex;
        }
    
    }
}
