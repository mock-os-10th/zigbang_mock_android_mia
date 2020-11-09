package com.soft.zigbang.src.house.find.detail.interfaces;

import com.soft.zigbang.src.house.find.models.FindResponse;

import java.util.List;

public interface FindDetailActivityView {
    // 아파트 개별 조회
//    void getApartSuccess(List<FindResponse.Result> apartList, List<FindResponse.School> schools);
//    void getApartFailure(String message);

    // 아파트 관심 등록
    void apartLikeSuccess();
    void apartLikeFailure();
}
