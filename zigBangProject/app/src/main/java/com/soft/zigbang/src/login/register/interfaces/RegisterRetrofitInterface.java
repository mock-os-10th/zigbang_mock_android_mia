package com.soft.zigbang.src.login.register.interfaces;

import com.soft.zigbang.src.login.register.models.SignUpBody;
import com.soft.zigbang.src.login.register.models.SignUpResponse;
import com.soft.zigbang.src.main.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RegisterRetrofitInterface {
//    @GET("/jwt")
//    Call<DefaultResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<DefaultResponse> postTest(@Body RequestBody params);

    @POST("/user")
    Call<SignUpResponse> postRegister(@Body SignUpBody params);
}
