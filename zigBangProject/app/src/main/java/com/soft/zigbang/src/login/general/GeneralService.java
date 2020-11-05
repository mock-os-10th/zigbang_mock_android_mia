package com.soft.zigbang.src.login.general;

import android.content.SharedPreferences;

import com.soft.zigbang.src.login.general.interfaces.GeneralFragmentView;
import com.soft.zigbang.src.login.general.interfaces.GeneralRetrofitInterface;
import com.soft.zigbang.src.login.general.models.LoginBody;
import com.soft.zigbang.src.login.general.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.soft.zigbang.src.ApplicationClass.getRetrofit;
import static com.soft.zigbang.src.ApplicationClass.sSharedPreferences;

public class GeneralService {
    private final GeneralFragmentView mGeneralFragmentView;

    public GeneralService(GeneralFragmentView mGeneralFragmentView) {
        this.mGeneralFragmentView = mGeneralFragmentView;
    }

    void postLogin(String email, String pw) {
        final GeneralRetrofitInterface generalRetrofitInterface = getRetrofit().create(GeneralRetrofitInterface.class);
        generalRetrofitInterface.postLogin(new LoginBody(email, pw)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if(loginResponse == null) {
                    mGeneralFragmentView.generalLoginFailure(null);
                    return;
                }

                if(loginResponse.getCode() == 100) {
                    mGeneralFragmentView.generalLoginSuccess(loginResponse.getResult().getUserno());
//                    X_ACCESS_TOKEN = loginResponse.getResult().getJwt();
//                    SharedPreferences.Editor editor = sSharedPreferences.edit();
//                    editor.putString("X_ACCESS_TOKEN", X_ACCESS_TOKEN);
//                    editor.apply();
                } else {
                    mGeneralFragmentView.generalLoginFalse(loginResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mGeneralFragmentView.generalLoginFailure(null);
            }
        });
    }
}
