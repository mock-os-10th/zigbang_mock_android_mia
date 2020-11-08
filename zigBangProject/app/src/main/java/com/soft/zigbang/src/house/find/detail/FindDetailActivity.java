package com.soft.zigbang.src.house.find.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.models.FindResponse;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class FindDetailActivity extends AppCompatActivity {

    private FindResponse.Result mApart;
    private MapView mMapView;
    private ViewGroup mMapContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);

        mMapContainer = (ViewGroup) findViewById(R.id.find_detail_rel_map);

        Bundle args = getIntent().getExtras();
        if(args != null) {
            ArrayList<FindResponse.Result> list = (ArrayList<FindResponse.Result>) args.getSerializable("apartList");
            mApart = list.get(0);
        }

        settingMapView();
    }

    private void settingMapView() {
        mMapView = new MapView(this);
        mMapContainer.addView(mMapView);
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5642135, 127.0016985), true);
        mMapView.setZoomLevel(1, true);

        createMarker();

    }

    private void createMarker() {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(mApart.getName());
        marker.setTag(mApart.getApartIndex().intValue());
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.5642135, 127.0016985));
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setCustomImageAutoscale(false); // 지도 라이브러리의 스케일 기능을 꺼줌.
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
}