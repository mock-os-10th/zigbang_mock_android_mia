package com.soft.zigbang.src.login.general.interfaces;

import com.soft.zigbang.src.login.general.models.LoginBody;
import com.soft.zigbang.src.login.general.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GeneralRetrofitInterface {
    @POST("/login")
    Call<LoginResponse> postLogin(@Body LoginBody params);
}
