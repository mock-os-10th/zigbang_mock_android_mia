package com.soft.zigbang.src.house.apart.interfaces;

import com.soft.zigbang.src.house.apart.models.UserResponse;
import com.soft.zigbang.src.main.models.ApartInfoResponse;

import java.util.List;

public interface ApartActivityView {
    // 사용자 정보 조회
    void getUserInfoSuccess(int code, List<UserResponse.Result> result);
    void getUserInfoFailure(String message);

    // 아파트 정보 (뉴스,광고,관심)
    void getApartInfoSuccess(int code, List<ApartInfoResponse.Result> results);
    void getApartInfoFailure(String message);
}
