package com.soft.zigbang.src.house;


import android.os.Bundle;
import android.view.ViewGroup;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.interfaces.HouseActivityView;
import com.soft.zigbang.src.house.models.HouseResponse;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class HouseActivity extends BaseActivity implements HouseActivityView {

    private HouseService mHouseService;
    private MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        mMapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mMapView);

        mHouseService = new HouseService(this);
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5642135, 127.0016985), true);
        mMapView.setZoomLevel(7, true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mHouseService.getApartList();

    }

    @Override
    public void getApartListSuccess(String text, List<HouseResponse.Result> apartList) {
        hideProgressDialog();
//        showCustomToast(text);
        for(HouseResponse.Result apart : apartList) {
            createMarker(apart);
        }
    }

    @Override
    public void getApartListFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    private void createMarker(HouseResponse.Result apart) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(apart.getName());
        marker.setTag(apart.getApartIndex().intValue());
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(apart.getLatitude(), apart.getLongitude()));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mMapView.addPOIItem(marker);

        //customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        //customMarker.setCustomImageResourceId(R.drawable.custom_marker_red); // 마커 이미지.
        //customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
    }
}