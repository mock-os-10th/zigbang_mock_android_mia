package com.soft.zigbang.src.house.find.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.find.FindMapActivity;
import com.soft.zigbang.src.house.find.detail.interfaces.FindDetailActivityView;
import com.soft.zigbang.src.house.find.models.FindResponse;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FindDetailActivity extends BaseActivity implements FindDetailActivityView {

    private FindResponse.Result mApart;
    private ArrayList<FindResponse.School> mSchools;
    private MapView mMapView;
    private ViewGroup mMapContainer;
    private TextView mApartName, mApartSubName, mReviewScore, mReviewCnt, mReviewContent,
            mSellValue, mMonthValue, mDongRank, mDongCnt, mGuRank, mGuCnt, mAddress;

    private ImageView mFindDetailIvApart;
    private LinearLayout mLikeLayout;
    private RecyclerView rvSchoolList;
    private FindDetailService mFindDetailService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvSchoolList = findViewById(R.id.rv_school_list);
        rvSchoolList.setLayoutManager(manager);
        mMapContainer = (ViewGroup) findViewById(R.id.find_detail_rel_map);
        mFindDetailIvApart = findViewById(R.id.find_detail_iv_apart);
        mFindDetailService = new FindDetailService(this);

//        int apartIndex = getIntent().getIntExtra("apartIndex", 0);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            ArrayList<FindResponse.Result> list = (ArrayList<FindResponse.Result>) args.getSerializable("apartList");
            mSchools = (ArrayList<FindResponse.School>) args.getSerializable("schools");
            mApart = list.get(0);
        }
        FindDetailSchoolAdapter adapter = new FindDetailSchoolAdapter(mSchools);
        rvSchoolList.setAdapter(adapter);

        mApartName = findViewById(R.id.detail_apart_name);
        mApartSubName = findViewById(R.id.detail_apart_name_sub);
        mReviewScore = findViewById(R.id.detail_tv_review_score);
        mReviewCnt = findViewById(R.id.find_detail_tv_review_cnt);
        mReviewContent = findViewById(R.id.find_detail_tv_review_cont);
        mSellValue = findViewById(R.id.detail_tv_sell_value);
        mMonthValue = findViewById(R.id.detail_tv_month_value);
        mDongRank = findViewById(R.id.find_detail_tv_dong_rank);
        mDongCnt = findViewById(R.id.find_detail_tv_dong_cnt);
        mGuRank = findViewById(R.id.find_detail_tv_gu_rank);
        mGuCnt = findViewById(R.id.find_detail_tv_gu_cnt);
        mAddress = findViewById(R.id.find_detail_tv_address);
        mLikeLayout = findViewById(R.id.find_detail_linear_like);

        settingMapView();

        Glide.with(FindDetailActivity.this)
                .load(Uri.parse(mApart.getImage()))
                .into(mFindDetailIvApart);

        mApartName.setText(mApart.getName());
        mApartSubName.setText(getDate(mApart.getEnterAt()) + " 입주 · " + mApart.getLiveNum() + "세대");
        mReviewScore.setText(String.valueOf(mApart.getReviewAvg()));
        mReviewCnt.setText("+" + mApart.getReviewNum());
        mReviewContent.setText(mApart.getContent1());
        mSellValue.setText("매매 " + setNumUnit(mApart.getPerA()) + "만");
        mMonthValue.setText("전세 " + setNumUnit(mApart.getPerB()) + "만");
        mDongRank.setText(mApart.getDong() + " " + mApart.getDongRank() + "위");
        mDongCnt.setText("총 " + mApart.getDongNum() + "단지 중");
        mGuRank.setText(mApart.getSi() + " " + mApart.getGu() + " " + mApart.getGuRank() + "위");
        mGuCnt.setText("총 " + mApart.getGuNum() + "단지 중");
        mAddress.setText(mApart.getAddress());
//        showProgressDialog();
//        findDetailService.getApart(apartIndex);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void findDetailOnClick(View v) {
        switch (v.getId()) {
            case R.id.detail_tv_apart_like:
                showProgressDialog();
                mFindDetailService.patchApartLike(mApart.getApartIndex());
                break;
        }
    }

    /**
     * 아파트 관심 등록
     */
    @Override
    public void apartLikeSuccess() {
        hideProgressDialog();
        showCustomToast("관심 등록되었습니다.");
//        mLikeLayout.setVisibility(View.VISIBLE);
//        TranslateAnimation animate;
//        animate = new TranslateAnimation(0, 0, mLikeLayout.getHeight(), 0);
//        animate.setDuration(500);
//        animate.setFillAfter(true);
//        mLikeLayout.startAnimation(animate);
    }

    @Override
    public void apartLikeFailure() {
        hideProgressDialog();
    }

    private void settingMapView() {
        mMapView = new MapView(this);
        mMapContainer.addView(mMapView);
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mApart.getLatitude(), mApart.getLongitude()), true);
        mMapView.setZoomLevel(1, true);

        createMarker();

    }

    private void createMarker() {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(mApart.getName());
        marker.setTag(mApart.getApartIndex());
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(mApart.getLatitude(), mApart.getLongitude()));
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setCustomImageAutoscale(false);
        mMapView.addPOIItem(marker);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapContainer.removeView(mMapView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mMapContainer.removeView(mMapView);
    }

    private String getDate(String date) {
        String returnDate = "";
        String[] dateStr = date.split(" ");

        String[] enterAtStr = dateStr[0].split("-");
        returnDate += enterAtStr[0] + "." + enterAtStr[1];

        return returnDate;
    }

    private String setNumUnit(int num) {
        String numStr = NumberFormat.getInstance().format(num);
        return numStr;
    }

}