
package com.soft.zigbang.src.house.oneroom.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OneRoomResponse {

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

        @SerializedName("acreage")
        private int mAcreage;
        @SerializedName("address")
        private String mAddress;
        @SerializedName("charter")
        private int mCharter;
        @SerializedName("comment")
        private String mComment;
        @SerializedName("createAt")
        private String mCreateAt;
        @SerializedName("direction")
        private String mDirection;
        @SerializedName("dong")
        private String mDong;
        @SerializedName("elevator")
        private String mElevator;
        @SerializedName("enterAt")
        private String mEnterAt;
        @SerializedName("fee")
        private int mFee;
        @SerializedName("floor")
        private int mFloor;
        @SerializedName("gu")
        private String mGu;
        @SerializedName("isOwner")
        private String mIsOwner;
        @SerializedName("latitude")
        private Double mLatitude;
        @SerializedName("longitude")
        private Double mLongitude;
        @SerializedName("park")
        private int mPark;
        @SerializedName("realIndex")
        private int mRealIndex;
        @SerializedName("sellType")
        private String mSellType;
        @SerializedName("si")
        private String mSi;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("type")
        private String mType;

//        @SerializedName("minLongitude")
//        private double mMinLongitude;
//        @SerializedName("maxLongitude")
//        private double mMaxLongitude;
//        @SerializedName("minLatitude")
//        private double mMinLatitude;
//        @SerializedName("maxLatitude")
//        private double mMaxLatitude;

//        @SerializedName("num")
//        private int mNum;
//
//        public int getNum() {
//            return mNum;
//        }
//
//        public void setNum(int num) {
//            mNum = num;
//        }

//
//        public double getMinLongitude() {
//            return mMinLongitude;
//        }
//
//        public void setMinLongitude(double minLongitude) {
//            this.mMinLongitude = minLongitude;
//        }
//
//        public double getMaxLongitude() {
//            return mMaxLongitude;
//        }
//
//        public void setMaxLongitude(double maxLongitude) {
//            this.mMaxLongitude = maxLongitude;
//        }
//
//        public double getMinLatitude() {
//            return mMinLatitude;
//        }
//
//        public void setMinLatitude(double minLatitude) {
//            this.mMinLatitude = minLatitude;
//        }
//
//        public double getMaxLatitude() {
//            return mMaxLatitude;
//        }
//
//        public void setMaxLatitude(double maxLatitude) {
//            this.mMaxLatitude = maxLatitude;
//        }

        public int getAcreage() {
            return mAcreage;
        }

        public void setAcreage(int acreage) {
            mAcreage = acreage;
        }

        public String getAddress() {
            return mAddress;
        }

        public void setAddress(String address) {
            mAddress = address;
        }

        public int getCharter() {
            return mCharter;
        }

        public void setCharter(int charter) {
            mCharter = charter;
        }

        public String getComment() {
            return mComment;
        }

        public void setComment(String comment) {
            mComment = comment;
        }

        public String getCreateAt() {
            return mCreateAt;
        }

        public void setCreateAt(String createAt) {
            mCreateAt = createAt;
        }

        public String getDirection() {
            return mDirection;
        }

        public void setDirection(String direction) {
            mDirection = direction;
        }

        public String getDong() {
            return mDong;
        }

        public void setDong(String dong) {
            mDong = dong;
        }

        public String getElevator() {
            return mElevator;
        }

        public void setElevator(String elevator) {
            mElevator = elevator;
        }

        public String getEnterAt() {
            return mEnterAt;
        }

        public void setEnterAt(String enterAt) {
            mEnterAt = enterAt;
        }

        public int getFee() {
            return mFee;
        }

        public void setFee(int fee) {
            mFee = fee;
        }

        public int getFloor() {
            return mFloor;
        }

        public void setFloor(int floor) {
            mFloor = floor;
        }

        public String getGu() {
            return mGu;
        }

        public void setGu(String gu) {
            mGu = gu;
        }

        public String getIsOwner() {
            return mIsOwner;
        }

        public void setIsOwner(String isOwner) {
            mIsOwner = isOwner;
        }

        public Double getLatitude() {
            return mLatitude;
        }

        public void setLatitude(Double latitude) {
            mLatitude = latitude;
        }

        public Double getLongitude() {
            return mLongitude;
        }

        public void setLongitude(Double longitude) {
            mLongitude = longitude;
        }

        public int getPark() {
            return mPark;
        }

        public void setPark(int park) {
            mPark = park;
        }

        public int getRealIndex() {
            return mRealIndex;
        }

        public void setRealIndex(int realIndex) {
            mRealIndex = realIndex;
        }

        public String getSellType() {
            return mSellType;
        }

        public void setSellType(String sellType) {
            mSellType = sellType;
        }

        public String getSi() {
            return mSi;
        }

        public void setSi(String si) {
            mSi = si;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

    }
}
