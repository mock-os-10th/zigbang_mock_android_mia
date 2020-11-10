package com.soft.zigbang.src.splash.interfaces;

public interface SplashActivityView {
    void getJwtSuccess(int code, String message);
    void getJwtFailure(String message);
}
