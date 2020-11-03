package com.soft.zigbang.src.house.interfaces;

import com.soft.zigbang.src.house.models.HouseResponse;

import java.util.List;

public interface HouseActivityView {
    void getApartListSuccess(String text, List<HouseResponse.Result> apartList);

    void getApartListFailure(String message);
}
