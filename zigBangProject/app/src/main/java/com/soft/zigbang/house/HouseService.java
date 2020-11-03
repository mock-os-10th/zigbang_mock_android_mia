package com.soft.zigbang.src.house;

import com.soft.zigbang.src.house.interfaces.HouseActivityView;
import com.soft.zigbang.src.house.interfaces.HouseRetrofitInterface;
import com.soft.zigbang.src.house.models.HouseResponse;
import com.soft.zigbang.src.login.general.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class HouseService {
    private final HouseActivityView mHouseActivityView;

    public HouseService(HouseActivityView mHouseActivityView) {
        this.mHouseActivityView = mHouseActivityView;
    }

    void getApartList() {
        final HouseRetrofitInterface houseRetrofitInterface = getRetrofit().create(HouseRetrofitInterface.class);
        houseRetrofitInterface.getApartList("A").enqueue(new Callback<HouseResponse>() {
            @Override
            public void onResponse(Call<HouseResponse> call, Response<HouseResponse> response) {
                final HouseResponse houseResponse = response.body();
                if(houseResponse == null) {
                    mHouseActivityView.getApartListFailure(null);
                    return;
                }
                mHouseActivityView.getApartListSuccess(houseResponse.getMessage(), houseResponse.getResult());
            }

            @Override
            public void onFailure(Call<HouseResponse> call, Throwable t) {
                mHouseActivityView.getApartListFailure(null);
            }
        });
    }
}
