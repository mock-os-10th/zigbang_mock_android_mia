package com.soft.zigbang.src.house.find.detail;

import com.soft.zigbang.src.house.find.detail.interfaces.FindDetailActivityView;
import com.soft.zigbang.src.house.find.detail.interfaces.FindDetailRetrofitInterface;
import com.soft.zigbang.src.house.find.detail.models.LikeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class FindDetailService {
    private final FindDetailActivityView findDetailActivityView;

    public FindDetailService(FindDetailActivityView findDetailActivityView) {
        this.findDetailActivityView = findDetailActivityView;
    }

    /**
     * 아파트 개별 조회
     */
//    void getApart(int apartIndex) {
//        final FindDetailInterface findDetailInterface = getRetrofit().create(FindDetailInterface.class);
//        findDetailInterface.getApart(apartIndex).enqueue(new Callback<FindResponse>() {
//            @Override
//            public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {
//                final FindResponse findResponse = response.body();
//                if(findResponse == null) {
//                    findDetailActivityView.getApartFailure(null);
//                    return;
//                }
//                findDetailActivityView.getApartSuccess(findResponse.getResult(), findResponse.getSchool());
//            }
//
//            @Override
//            public void onFailure(Call<FindResponse> call, Throwable t) {
//                findDetailActivityView.getApartFailure(null);
//            }
//        });
//    }

    /**
     * 아파트 관심 등록
     */
    void patchApartLike(int apartIndex) {
        final FindDetailRetrofitInterface findDetailRetrofitInterface = getRetrofit().create(FindDetailRetrofitInterface.class);
        findDetailRetrofitInterface.patchApartLike(apartIndex).enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                final LikeResponse likeResponse = response.body();
                if(likeResponse == null) {
                    findDetailActivityView.apartLikeFailure();
                    return;
                }
                findDetailActivityView.apartLikeSuccess();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                findDetailActivityView.apartLikeFailure();
            }
        });
    }
}
