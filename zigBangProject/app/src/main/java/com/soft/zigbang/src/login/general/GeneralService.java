package com.soft.zigbang.src.login.general;

import com.soft.zigbang.src.login.general.interfaces.GeneralFragmentView;
import com.soft.zigbang.src.login.general.interfaces.GeneralRetrofitInterface;
import com.soft.zigbang.src.login.general.models.LoginBody;
import com.soft.zigbang.src.login.general.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

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
                mGeneralFragmentView.generalLoginSuccess(loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mGeneralFragmentView.generalLoginFailure(null);
            }
        });
    }
}
