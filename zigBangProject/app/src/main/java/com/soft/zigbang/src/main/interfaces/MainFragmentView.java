package com.soft.zigbang.src.main.interfaces;

import com.soft.zigbang.src.main.models.ApartInfoResponse;

import java.util.List;

public interface MainFragmentView {
    // 아파트 정보 (뉴스,광고,관심)
    void getApartInfoSuccess(int code, List<ApartInfoResponse.Result> results);
    void getApartInfoFailure(String message);
}
