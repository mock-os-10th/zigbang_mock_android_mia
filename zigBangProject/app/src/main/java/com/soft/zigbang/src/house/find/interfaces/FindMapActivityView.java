package com.soft.zigbang.src.house.find.interfaces;

import com.soft.zigbang.src.house.find.models.FindResponse;

import java.util.List;

public interface FindMapActivityView {
    // 아파트 목록 조회
    void getApartListSuccess(String text, List<FindResponse.Result> apartList);
    void getApartListFailure(String message);

    // 아파트 개별 조회
    void getApartSuccess(List<FindResponse.Result> apartList);
    void getApartFailure(String message);


    // 아파트 검색 조회
    void getSearchApartSuccess(List<FindResponse.Result> apartList);
    void getSearchApartFailure(String message);

    // 매물 조회
}
