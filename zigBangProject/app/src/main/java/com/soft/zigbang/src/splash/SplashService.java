package com.soft.zigbang.src.splash;

import com.soft.zigbang.src.splash.interfaces.SplashActivityView;
import com.soft.zigbang.src.splash.interfaces.SplashRetrofitInterface;
import com.soft.zigbang.src.splash.models.SplashResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.SUCCESS_CODE;
import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class SplashService {
    private final SplashActivityView splashActivityView;

    public SplashService(SplashActivityView splashActivityView) {
        this.splashActivityView = splashActivityView;
    }

    void getJwt() {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.getJwt().enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {
                final SplashResponse splashResponse = response.body();
                if(splashResponse == null) {
                    splashActivityView.getJwtFailure(null);
                    return;
                }

                if(splashResponse.getCode() == SUCCESS_CODE) {
                    splashActivityView.getJwtSuccess(splashResponse.getCode(), splashResponse.getMessage());
                } else {
                    splashActivityView.getJwtFailure(splashResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                splashActivityView.getJwtFailure(null);
            }
        });
    }
}
