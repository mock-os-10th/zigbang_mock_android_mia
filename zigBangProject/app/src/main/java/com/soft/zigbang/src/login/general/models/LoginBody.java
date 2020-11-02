package com.soft.zigbang.src.login.general.models;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPw")
    private String userPw;

    public LoginBody(String userEmail, String userPw) {
        this.userEmail = userEmail;
        this.userPw = userPw;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
}
