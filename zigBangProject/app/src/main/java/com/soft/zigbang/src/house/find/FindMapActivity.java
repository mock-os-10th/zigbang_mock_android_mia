package com.soft.zigbang.src.house.find;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.find.detail.FindDetailActivity;
import com.soft.zigbang.src.house.find.interfaces.FindMapActivityView;
import com.soft.zigbang.src.house.find.models.FindResponse;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class FindMapActivity extends BaseActivity implements FindMapActivityView, MapView.POIItemEventListener {

    private String TAG = "FindMapActivity";

    private FindMapService mFindMapService;
    private FindListFragment mFindListFragment;

    private MapView mMapView;
    private ViewGroup mMapContainer;

    private ImageView mFindIvApart;
    private RelativeLayout mFindRelApart;
    private TextView mFindTvApartName, mFindTvApartAddress, mFindTvApartDate, mFindTvType;

    private boolean isShowApart;
    private int orgIndex = 0;
    private int from, to;

    private HashMap<String, Object> mFilterMap;
    private List<FindResponse.Result> mApartList = null;
//    private FirebaseStorageManager fbsManager = new FirebaseStorageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_map);

        mMapContainer = (ViewGroup) findViewById(R.id.map_view);
//        settingMapView(mMapContainer,37.5642135, 127.0016985, 7);

        mFindMapService = new FindMapService(this);
        mFindRelApart = findViewById(R.id.find_rel_apart);
        mFindTvApartName = findViewById(R.id.find_tv_apart_name);
        mFindTvApartAddress = findViewById(R.id.find_tv_apart_address);
        mFindTvApartDate = findViewById(R.id.find_tv_apart_date);
        mFindTvType = findViewById(R.id.find_tv_type);
        mFindIvApart = findViewById(R.id.find_iv_apart);

        isShowApart = false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        settingMapView(37.5642135, 127.0016985, 7);

        mMapContainer.addView(mMapView);
        mMapView.removeAllPOIItems();

        showProgressDialog();

        if (mFilterMap != null) {
            String sellType = mFilterMap.get("sellType").toString();
            int acreage = Integer.parseInt(mFilterMap.get("acreage").toString());
            int enterAt = Integer.parseInt(mFilterMap.get("enterAt").toString());
            int liveNum = Integer.parseInt(mFilterMap.get("liveNum").toString());
            mFindMapService.getSearchApartList(sellType, acreage, enterAt, liveNum);
        } else {
            mFindMapService.getApartList();
        }

//        mapCircle();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mMapContainer.removeView(mMapView);
    }


    /**
     * 카카오 맵 생성
     */
    private void settingMapView(double lat, double lnt, int zoom) {
        mMapView = new MapView(this);
//        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
//        mMapContainer.addView(mMapView);

        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lnt), true);
        mMapView.setZoomLevel(zoom, true);
        mMapView.setPOIItemEventListener(this);
    }

    /**
     * 마커 생성
     */
    private void createMarker(FindResponse.Result apart) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(apart.getName());
        marker.setTag(apart.getApartIndex());
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(apart.getLatitude(), apart.getLongitude()));
        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        marker.setCustomImageResourceId(R.drawable.marker_2);
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
//        marker.setCustomSelectedImageResourceId(R.drawable.sss);
        marker.setCustomImageAutoscale(false); // 지도 라이브러리의 스케일 기능을 꺼줌.
        mMapView.addPOIItem(marker);
    }

    /**
     * 아파트 목록 조회 성공
     */
    @Override
    public void getApartListSuccess(String text, List<FindResponse.Result> apartList) {
        hideProgressDialog();
        mApartList = apartList;
        if (mApartList != null && mApartList.size() > 0) {
            for (FindResponse.Result apart : apartList) {
                createMarker(apart);
            }
        }
    }

    @Override
    public void getApartListFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_error));
    }

    /**
     * 아파트 개별 조회
     */
    @Override
    public void getApartSuccess(List<FindResponse.Result> apartList, List<FindResponse.School> schools) {
        hideProgressDialog();
        if (apartList != null && apartList.size() > 0) {
            Intent intent = new Intent(this, FindDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("apartList", (Serializable) apartList);
            bundle.putSerializable("schools", (Serializable) schools);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            showCustomToast("상세정보가 없습니다.");
        }
    }

    @Override
    public void getApartFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    /**
     * 아파트 검색 조회
     */
    @Override
    public void getSearchApartSuccess(List<FindResponse.Result> apartList) {
        mMapView.removeAllPOIItems();
//        mApartList = apartList;
        if(apartList.size() == 0) {
            showCustomToast("해당 조건에 맞는 아파트가 없습니다.");
        }

        for (FindResponse.Result apart : apartList) {
            createMarker(apart);
        }

        hideProgressDialog();
    }

    @Override
    public void getSearchApartFailure(String message) {
        hideProgressDialog();
    }

    /**
     * 뷰 클릭
     */
    @SuppressLint("NonConstantResourceId")
    public void findOnClick(View view) {
        switch (view.getId()) {
            case R.id.find_iv_back: // 뒤로가기
                finish();
                break;
            case R.id.find_tv_search: // 아파트, 지역, 지하철역, 학교 검색
                mFindListFragment = FindListFragment.newInstance(mApartList);
                getSupportFragmentManager().beginTransaction().add(R.id.rel_container, mFindListFragment).commit();
                break;
            case R.id.find_rel_type: // 매매 or 전세가
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(R.layout.apart_dialog);
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setLayout(1180, 925);
                break;
            case R.id.find_linear_filter: // 필터 적용 검색
                startActivityForResult(new Intent(this, FilterActivity.class), 100);
                break;
            case R.id.find_rel_apart:
                showProgressDialog();
                mFindMapService.getApart(orgIndex);
                break;
        }
    }

    /**
     * 필터
     *
     * @param requestCode 필터 요청 코드
     * @param resultCode  RESULT_OK
     * @param data        필터값
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 필터 적용
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult");
            mFilterMap = (HashMap<String, Object>) data.getExtras().getSerializable("filterMap");
        }
    }

    /**
     * 마커 클릭
     */
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        FindResponse.Result apart = mApartList.get(mapPOIItem.getTag() - 1);
        TranslateAnimation animate;

        if (orgIndex == 0) {
            orgIndex = mapPOIItem.getTag();
        }

        if (orgIndex != mapPOIItem.getTag()) {
            orgIndex = mapPOIItem.getTag();
            isShowApart = false;
        }

        Glide.with(FindMapActivity.this)
                .load(Uri.parse(apart.getImage()))
                .into(mFindIvApart);

        mFindTvApartName.setText(apart.getName());
        mFindTvApartAddress.setText(apart.getAddress());
        mFindTvApartDate.setText(getDate(apart.getEnterAt()));
        mFindRelApart.setVisibility(View.VISIBLE);

        if (!isShowApart)
            animate = new TranslateAnimation(from, to, mFindRelApart.getHeight(), to);
        else
            animate = new TranslateAnimation(from, to, from, mFindRelApart.getHeight());

        animate.setDuration(500);
        animate.setFillAfter(true);
        mFindRelApart.startAnimation(animate);
        isShowApart = !isShowApart;

//        fbsManager.getDownloadImageUrl(apart.getImage()); // url 정보 얻기

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
    }

    public void removeFragment() {
        getSupportFragmentManager().beginTransaction().remove(mFindListFragment).commit();
    }

    private String getDate(String date) {
        String returnDate = "";
        String[] dateStr = date.split(" ");

        String[] enterAtStr = dateStr[0].split("-");
        returnDate += enterAtStr[0] + "." + enterAtStr[1] + " 입주";

        return returnDate;
    }
}