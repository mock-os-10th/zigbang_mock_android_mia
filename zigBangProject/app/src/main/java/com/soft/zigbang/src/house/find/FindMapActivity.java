package com.soft.zigbang.src.house.find;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private FragmentManager fm = getSupportFragmentManager();

    private MapView mMapView;
    private RelativeLayout mFindRelApart;
    private TextView mFindTvApartName, mFindTvApartAddress, mFindTvApartDate;

    private boolean isShowApart;
    private int orgIndex = 0;
    private HashMap<String, Object> mFilterMap;
    private List<FindResponse.Result> mApartList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_map);

        settingMapView(37.5642135, 127.0016985, 7);

        mMapView.setPOIItemEventListener(this);
        mFindRelApart = findViewById(R.id.find_rel_apart);
        mFindTvApartName = findViewById(R.id.find_tv_apart_name);
        mFindTvApartAddress = findViewById(R.id.find_tv_apart_address);
        mFindTvApartDate = findViewById(R.id.find_tv_apart_date);

        isShowApart = false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        showProgressDialog();
        // 필터 적용되면 여기서 처리
        // HashMap 이용
        mFindMapService.getApartList();

    }

    /**
     * 카카오 맵 생성
     */
    private void settingMapView(double lat, double lnt, int zoom) {
        mMapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mMapView);

        mFindMapService = new FindMapService(this);
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lnt), true);
        mMapView.setZoomLevel(zoom, true);
    }

    /**
     * 마커 생성
     */
    private void createMarker(FindResponse.Result apart) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(apart.getName());
        marker.setTag(apart.getApartIndex().intValue());
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(apart.getLatitude(), apart.getLongitude()));
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        marker.setCustomImageResourceId(R.drawable.marker_2);
        marker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        mMapView.addPOIItem(marker);
    }

    /**
     * 아파트 목록 조회 성공
     */
    @Override
    public void getApartListSuccess(String text, List<FindResponse.Result> apartList) {
        hideProgressDialog();
        mApartList = apartList;
        for (FindResponse.Result apart : apartList) {
            createMarker(apart);
        }
    }

    @Override
    public void getApartListFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    /**
     * 아파트 개별 조회
     */
    @Override
    public void getApartSuccess(List<FindResponse.Result> apartList) {
        hideProgressDialog();
        FindResponse.Result result = apartList.get(0);
        Intent intent = new Intent(this, FindDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("apart", (Serializable)result);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getApartFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }


    /**
     * 뷰 클릭
     */
    public void findOnClick(View view) {
        switch (view.getId()) {
            case R.id.find_iv_back:
                finish();
                break;
            case R.id.find_tv_search:
                mFindListFragment = FindListFragment.newInstance(mApartList);
                fm.beginTransaction().add(R.id.rel_container, mFindListFragment).commit();
                break;
            case R.id.find_rel_sell:
                // 다이얼로그
                break;
            case R.id.find_linear_filter:
                Intent intent = new Intent(this, FilterActivity.class);
                startActivityForResult(intent, 100);
                // 필터 액티비티
                break;
            case R.id.find_rel_apart:
                showProgressDialog();
                mFindMapService.getApart(orgIndex);
                break;
        }
    }

    /**
     * 필터
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
            mFilterMap = new HashMap<>();

        }
    }

    /**
     * 마커 클릭
     */
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

        if (orgIndex == 0) {
            orgIndex = mapPOIItem.getTag();
        }

        if(orgIndex != mapPOIItem.getTag()) {
            orgIndex = mapPOIItem.getTag();
            isShowApart = false;
        }

        FindResponse.Result apart = mApartList.get(mapPOIItem.getTag() - 1);
        mFindTvApartName.setText(apart.getName());
        mFindTvApartAddress.setText(apart.getSi() + " " + apart.getGu() + " " + apart.getDong() + " " + apart.getLiveNum() + "세대");
        // 날짜도 같이 받아와야 할 듯

        TranslateAnimation animate;
        if (!isShowApart) {
            mFindRelApart.setVisibility(View.VISIBLE);
            animate = new TranslateAnimation(0, 0, mFindRelApart.getHeight(), 0);
        } else {
            animate = new TranslateAnimation(0, 0, 0, mFindRelApart.getHeight());
        }
        animate.setDuration(500);
        animate.setFillAfter(true);
        mFindRelApart.startAnimation(animate);
        isShowApart = !isShowApart;
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
        fm.beginTransaction().remove(mFindListFragment).commit();
    }
}