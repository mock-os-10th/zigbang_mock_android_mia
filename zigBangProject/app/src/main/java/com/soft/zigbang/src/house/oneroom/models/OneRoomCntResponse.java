
package com.soft.zigbang.src.house.oneroom.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OneRoomCntResponse {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("isSuccess")
    private Boolean mIsSuccess;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private List<Result> mResult;

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

    public List<Result> getResult() {
        return mResult;
    }

    public void setResult(List<Result> result) {
        mResult = result;
    }

    public static class Result {

        @SerializedName("num")
        private Long mNum;

        public Long getNum() {
            return mNum;
        }

        public void setNum(Long num) {
            mNum = num;
        }

    }
}
