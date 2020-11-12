package com.soft.zigbang.src.house.oneroom.interfaces;

import com.soft.zigbang.src.house.oneroom.models.OneRoomCntBody;
import com.soft.zigbang.src.house.oneroom.models.OneRoomCntResponse;
import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OneRoomMapRetrofitInterface {
    @GET("/realestate")
    Call<OneRoomResponse> getOneRoomList(@Query("type") String type,
                                         @Query("how") String how,
                                         @Query("sellType") String sellType);

    @GET("/realestatenum")
    Call<OneRoomResponse> getOneRoomCnt(@Query("minLongitude") double minLongitude,
                                        @Query("maxLongitude") double maxLongitude,
                                        @Query("minLatitude") double minLatitude,
                                        @Query("maxLatitude") double maxLatitude);
}
