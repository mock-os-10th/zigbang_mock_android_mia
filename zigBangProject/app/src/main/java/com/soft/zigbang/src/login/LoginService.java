package com.soft.zigbang.src.login;

import android.content.SharedPreferences;

import com.soft.zigbang.src.login.kakao.interfaces.KaKaoActivityView;
import com.soft.zigbang.src.login.kakao.interfaces.KaKaoRetrofitInterface;
import com.soft.zigbang.src.login.kakao.models.KaKaoBody;
import com.soft.zigbang.src.login.kakao.models.KaKaoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.SUCCESS_CODE;
import static com.soft.zigbang.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.soft.zigbang.src.ApplicationClass.getRetrofit;
import static com.soft.zigbang.src.ApplicationClass.sSharedPreferences;

public class LoginService {
    private final KaKaoActivityView mKaKaoActivityView;

    public LoginService(KaKaoActivityView mKaKaoActivityView) {
        this.mKaKaoActivityView = mKaKaoActivityView;
    }

    void postKaKaoLogin(String accessToken) {
        final KaKaoRetrofitInterface kaKaoRetrofitInterface = getRetrofit().create(KaKaoRetrofitInterface.class);
        kaKaoRetrofitInterface.postKaKaoLogin(new KaKaoBody(accessToken)).enqueue(new Callback<KaKaoResponse>() {
            @Override
            public void onResponse(Call<KaKaoResponse> call, Response<KaKaoResponse> response) {
                final KaKaoResponse loginResponse = response.body();
                if(loginResponse == null) {
                    mKaKaoActivityView.kakaoLoginFailure(null);
                    return;
                }

                if(loginResponse.getCode() == SUCCESS_CODE) {
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(X_ACCESS_TOKEN, loginResponse.getResult().getJwt());
                    editor.apply();
                    mKaKaoActivityView.kakaoLoginSuccess(loginResponse.getResult().getUserno());
                } else {
                    mKaKaoActivityView.kakaoLoginFailure(loginResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<KaKaoResponse> call, Throwable t) {
                mKaKaoActivityView.kakaoLoginFailure(null);
            }
        });
    }
}
