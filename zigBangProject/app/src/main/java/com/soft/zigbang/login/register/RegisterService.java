package com.soft.zigbang.src.login.register;

import com.soft.zigbang.src.login.register.interfaces.RegisterFragmentView;
import com.soft.zigbang.src.login.register.interfaces.RegisterRetrofitInterface;
import com.soft.zigbang.src.login.register.models.SignUpBody;
import com.soft.zigbang.src.login.register.models.SignUpResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;


public class RegisterService  {
    private final RegisterFragmentView mRegisterFragmentView;

    RegisterService(final RegisterFragmentView mRegisterFragmentView) {
        this.mRegisterFragmentView = mRegisterFragmentView;
    }

    void postRegister(String email, String pw) {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        registerRetrofitInterface.postRegister(new SignUpBody(email, pw)).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mRegisterFragmentView.signUpFailure(null);
                    return;
                }

                mRegisterFragmentView.signUpSuccess(signUpResponse.getMessage(), signUpResponse.getCode());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mRegisterFragmentView.signUpFailure(null);
            }
        });
    }
}
