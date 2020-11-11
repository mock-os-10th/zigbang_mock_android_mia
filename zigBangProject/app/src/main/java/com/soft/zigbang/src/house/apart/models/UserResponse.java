
package com.soft.zigbang.src.house.apart.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

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

        @SerializedName("email")
        private String mEmail;
        @SerializedName("nickName")
        private Object mNickName;
        @SerializedName("phone")
        private Object mPhone;

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            mEmail = email;
        }

        public Object getNickName() {
            return mNickName;
        }

        public void setNickName(Object nickName) {
            mNickName = nickName;
        }

        public Object getPhone() {
            return mPhone;
        }

        public void setPhone(Object phone) {
            mPhone = phone;
        }

    }
}
