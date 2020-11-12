package com.soft.zigbang.src.house.oneroom;

import com.soft.zigbang.src.house.oneroom.interfaces.OneRoomMapActivityView;
import com.soft.zigbang.src.house.oneroom.interfaces.OneRoomMapRetrofitInterface;
import com.soft.zigbang.src.house.oneroom.models.OneRoomCntBody;
import com.soft.zigbang.src.house.oneroom.models.OneRoomCntResponse;
import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.SUCCESS_CODE;
import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class OneRoomService {
    private final OneRoomMapActivityView oneRoomMapActivityView;

    public OneRoomService(OneRoomMapActivityView oneRoomMapActivityView) {
        this.oneRoomMapActivityView = oneRoomMapActivityView;
    }

    void getOneRoomList(String type, String how, String sellType) {
        final OneRoomMapRetrofitInterface oneRoomMapRetrofitInterface = getRetrofit().create(OneRoomMapRetrofitInterface.class);
        oneRoomMapRetrofitInterface.getOneRoomList(type, how, sellType).enqueue(new Callback<OneRoomResponse>() {
            @Override
            public void onResponse(Call<OneRoomResponse> call, Response<OneRoomResponse> response) {
                final OneRoomResponse oneRoomResponse = response.body();
                if(oneRoomResponse == null) {
                    oneRoomMapActivityView.getOneRoomListFailure(null);
                    return;
                }
                if(oneRoomResponse.getCode() == SUCCESS_CODE) {
                    oneRoomMapActivityView.getOneRoomListSuccess(oneRoomResponse.getCode(), oneRoomResponse.getResult());
                } else {
                    oneRoomMapActivityView.getOneRoomListFailure(null);
                }
            }

            @Override
            public void onFailure(Call<OneRoomResponse> call, Throwable t) {
                oneRoomMapActivityView.getOneRoomListFailure(null);
            }
        });
    }

    void getOneRoomCnt(double minLnt, double maxLnt, double minLat, double maxLat) {
        OneRoomMapRetrofitInterface oneRoomMapRetrofitInterface = getRetrofit().create(OneRoomMapRetrofitInterface.class);
        oneRoomMapRetrofitInterface.getOneRoomCnt(minLnt,maxLnt,minLat,maxLat).enqueue(new Callback<OneRoomResponse>() {
            @Override
            public void onResponse(Call<OneRoomResponse> call, Response<OneRoomResponse> response) {
                final OneRoomResponse oneRoomCntResponse = response.body();
                if(oneRoomCntResponse == null) {
                    oneRoomMapActivityView.getOneRoomMapCntFailure(null);
                }
                oneRoomMapActivityView.getOneRoomMapCntSuccess(oneRoomCntResponse.getCode(), oneRoomCntResponse.getResult());
            }

            @Override
            public void onFailure(Call<OneRoomResponse> call, Throwable t) {
                oneRoomMapActivityView.getOneRoomMapCntFailure(null);
            }
        });

    }
}
