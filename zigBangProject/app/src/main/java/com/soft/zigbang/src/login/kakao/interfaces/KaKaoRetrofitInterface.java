package com.soft.zigbang.src.login.kakao.interfaces;

import com.soft.zigbang.src.login.kakao.models.KaKaoBody;
import com.soft.zigbang.src.login.kakao.models.KaKaoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KaKaoRetrofitInterface {
    @POST("/kakao")
    Call<KaKaoResponse> postKaKaoLogin(@Body KaKaoBody params);
}
