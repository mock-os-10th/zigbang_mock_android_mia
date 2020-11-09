package com.soft.zigbang.src.house.find;

import com.soft.zigbang.src.house.find.interfaces.FindMapActivityView;
import com.soft.zigbang.src.house.find.interfaces.FindMapRetrofitInterface;
import com.soft.zigbang.src.house.find.models.FindResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soft.zigbang.src.ApplicationClass.getRetrofit;

public class FindMapService {
    private final FindMapActivityView mFindMapActivityView;

    public FindMapService(FindMapActivityView mFindMapActivityView) {
        this.mFindMapActivityView = mFindMapActivityView;
    }

    /**
     * 아파트 목록 조회
     */
    void getApartList() {
        final FindMapRetrofitInterface findMapRetrofitInterface = getRetrofit().create(FindMapRetrofitInterface.class);
        findMapRetrofitInterface.getApartList("A").enqueue(new Callback<FindResponse>() {
            @Override
            public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {
                final FindResponse findResponse = response.body();
                if(findResponse == null) {
                    mFindMapActivityView.getApartListFailure(null);
                    return;
                }
                mFindMapActivityView.getApartListSuccess(findResponse.getMessage(), findResponse.getResult());
            }

            @Override
            public void onFailure(Call<FindResponse> call, Throwable t) {
                mFindMapActivityView.getApartListFailure(null);
            }
        });
    }

    /**
     * 아파트 개별 조회
     */
    void getApart(int apartIndex) {
        final FindMapRetrofitInterface findMapRetrofitInterface = getRetrofit().create(FindMapRetrofitInterface.class);
        findMapRetrofitInterface.getApart(apartIndex).enqueue(new Callback<FindResponse>() {
            @Override
            public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {
                final FindResponse findResponse = response.body();
                if(findResponse == null) {
                    mFindMapActivityView.getApartFailure(null);
                    return;
                }
                mFindMapActivityView.getApartSuccess(findResponse.getResult(), findResponse.getSchool());
            }

            @Override
            public void onFailure(Call<FindResponse> call, Throwable t) {
                mFindMapActivityView.getApartFailure(null);
            }
        });
    }

    /**
     * 아파트 검색 조회
     */
    void getSearchApartList(String sellType, int acreage, int enterAt, int liveNum) {
        final FindMapRetrofitInterface findMapRetrofitInterface = getRetrofit().create(FindMapRetrofitInterface.class);
        findMapRetrofitInterface.getSearchApartList(sellType,acreage,enterAt,liveNum).enqueue(new Callback<FindResponse>() {
            @Override
            public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {
                final FindResponse findResponse = response.body();
                if(findResponse == null) {
                    mFindMapActivityView.getSearchApartFailure(null);
                    return;
                }
                mFindMapActivityView.getSearchApartSuccess(findResponse.getResult());
            }

            @Override
            public void onFailure(Call<FindResponse> call, Throwable t) {
                mFindMapActivityView.getSearchApartFailure(null);
            }
        });
    }

    /**
     * 매물 조회
     */
}
