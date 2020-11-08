package com.soft.zigbang.src.login.kakao.interfaces;

public interface KaKaoActivityView {
    void kakaoLoginSuccess(int userNo);

    void kakaoLoginFailure(String message);
}
