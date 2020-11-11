package com.soft.zigbang.src.house.apart;

import com.soft.zigbang.src.house.apart.interfaces.ApartRetrofitInterface;
import com.soft.zigbang.src.house.apart.interfaces.ApartActivityView;
import com.soft.zigbang.src.house.apart.models.UserResponse;
import com.soft.zigbang.src.main.interfaces.MainRetrofitInterface;
import com.soft.zigbang.src.main.models.ApartInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class ApartService {
    private final ApartActivityView apartActivityView;

    public ApartService(ApartActivityView apartActivityView) {
        this.apartActivityView = apartActivityView;
    }

    /**
     * 사용자 정보 조회
     */
    void getUserInfo(int userno) {
        final ApartRetrofitInterface apartRetrofitInterface = getRetrofit().create(ApartRetrofitInterface.class);
        apartRetrofitInterface.getUserInfo(userno).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                final UserResponse userResponse = response.body();
                if(userResponse == null) {
                    apartActivityView.getUserInfoFailure(null);
                    return;
                }
                apartActivityView.getUserInfoSuccess(userResponse.getCode(), userResponse.getResult());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                apartActivityView.getUserInfoFailure(null);
            }
        });
    }

    /**
     * 아파트 정보 조회
     */
    void getApartInfo() {
        final ApartRetrofitInterface apartRetrofitInterface = getRetrofit().create(ApartRetrofitInterface.class);
        apartRetrofitInterface.getApartInfo().enqueue(new Callback<ApartInfoResponse>() {
            @Override
            public void onResponse(Call<ApartInfoResponse> call, Response<ApartInfoResponse> response) {
                final ApartInfoResponse apartInfoResponse = response.body();
                if(apartInfoResponse == null) {
                    apartActivityView.getApartInfoFailure(null);
                    return;
                }
                apartActivityView.getApartInfoSuccess(apartInfoResponse.getCode(), apartInfoResponse.getResult());
            }

            @Override
            public void onFailure(Call<ApartInfoResponse> call, Throwable t) {
                apartActivityView.getApartInfoFailure(null);
            }
        });
    }
}
