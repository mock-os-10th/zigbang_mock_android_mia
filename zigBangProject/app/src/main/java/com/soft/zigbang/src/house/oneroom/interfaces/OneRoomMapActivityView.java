package com.soft.zigbang.src.house.oneroom.interfaces;

import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import java.util.List;

public interface OneRoomMapActivityView {
    void getOneRoomListSuccess(int code, List<OneRoomResponse.Result> results);
    void getOneRoomListFailure(String message);

    void getOneRoomMapCntSuccess(int code, List<OneRoomResponse.Result> results);
    void getOneRoomMapCntFailure(String message);

    void getOneRoomsSuccess(int code, List<OneRoomResponse.Result> results);
    void getOneRoomsFailure(String message);
}
