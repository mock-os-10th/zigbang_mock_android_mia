package com.soft.zigbang.src.house.apart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ApartMapActivity extends BaseActivity implements OnMapReadyCallback {
    GoogleMap mgoogleMap;
    ClusterManager clusterManager;
//    private NaverMap naverMap;
//    private Vector<LatLng> markersPosition;
//    private Vector<Marker> activeMarkers;
//    public final static double REFERANCE_LAT = 1 / 109.958489129649955;
//    public final static double REFERANCE_LNG = 1 / 88.74;
//    public final static double REFERANCE_LAT_X3 = 3 / 109.958489129649955;
//    public final static double REFERANCE_LNG_X3 = 3 / 88.74;
//    public boolean withinSightMarker(LatLng currentPosition, LatLng markerPosition) {
//        boolean withinSightMarkerLat = Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT_X3;
//        boolean withinSightMarkerLng = Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG_X3;
//        return withinSightMarkerLat && withinSightMarkerLng;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apart_map);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mgoogleMap = map;
        clusterManager = new ClusterManager<MyItem>(getApplicationContext(), mgoogleMap);

        ArrayList<Double> latList = new ArrayList<>();
        ArrayList<Double> lntList = new ArrayList<>();
        latList.add(37.5274819);
        latList.add(37.49419489574496);
        latList.add(37.4828021050624);
        latList.add(37.49649605358077);
        latList.add(37.48548922235278);
        latList.add(37.50928012482078);

        lntList.add(127.1405937);
        lntList.add(126.98111549517787);
        lntList.add(126.96375177982968);
        lntList.add(127.04046157504413);
        lntList.add(126.88342717602305);
        lntList.add(126.88791433089162);

        mgoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLng latLng = new LatLng(37.5274819, 127.1405937);
                mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        });
        mgoogleMap.setOnMarkerClickListener(clusterManager);
        mgoogleMap.setOnCameraIdleListener(clusterManager);
        mgoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                showCustomToast("aaaa");
            }
        });

        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener() {
            @Override
            public boolean onClusterClick(Cluster cluster) {
                showCustomToast("cluster");
                return false;
            }
        });

        clusterManager.setRenderer(new CustomIconRenderer(getApplicationContext(), mgoogleMap, clusterManager));
        for(int i = 0; i < latList.size(); i++) {
            MyItem myItem = new MyItem(latList.get(i), lntList.get(i), "A");
            clusterManager.addItem(myItem);
        }

        mgoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.d("ApartMap", "setOnInfoWindowClickListener");

            }
        });

        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener() {
            @Override
            public boolean onClusterItemClick(ClusterItem clusterItem) {
                showCustomToast("cluster item");
                return false;
            }
        });

    }
    class CustomIconRenderer extends DefaultClusterRenderer<MyItem> {
        private final IconGenerator iconGenerator;
        Context mContext;
        public CustomIconRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);
            iconGenerator = new IconGenerator(context);
            mContext = context;
        }

        @Override
        protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_2));
            markerOptions.snippet(item.getSnippet());
            markerOptions.title(item.getTitle());
            super.onBeforeClusterItemRendered(item, markerOptions);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
            final Drawable clusterIcon = mContext.getDrawable(R.drawable.cluster_marker);
            iconGenerator.setBackground(clusterIcon);
            iconGenerator.setTextAppearance(com.google.maps.android.R.style.amu_ClusterIcon_TextAppearance);

            if (cluster.getSize() < 10) {
                iconGenerator.setContentPadding(80, 60, 80, 60);
            }
            else {
                iconGenerator.setContentPadding(30, 20, 0, 0);
            }

            Bitmap icon = iconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

    }
}

























        //    private void freeActiveMarkers() {
//        if (activeMarkers == null) {
//            activeMarkers = new Vector<Marker>();
//            return;
//        }
//        for (Marker activeMarker: activeMarkers) {
//            activeMarker.setMap(null);
//        }
//        activeMarkers = new Vector<Marker>();
//    }

//    @Override
//    public void onMapReady(@NonNull NaverMap naverMap) {
//        Marker marker = new Marker();
//        marker.setPosition(new LatLng(37.5670135, 126.9783740));
//        marker.setMap(naverMap);
//        marker.setIcon(OverlayImage.fromResource(R.drawable.marker_2));
//        this.naverMap = naverMap;

//        LatLng initialPosition = new LatLng(37.506855, 127.066242);
//        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(initialPosition);
//        naverMap.moveCamera(cameraUpdate);

//         마커들 위치 정의 (대충 1km 간격 동서남북 방향으로 만개씩, 총 4만개)
//        markersPosition = new Vector<LatLng>();
//        for (int x = 0; x < 100; ++x) {
//            for (int y = 0; y < 100; ++y) {
//                markersPosition.add(new LatLng(
//                        initialPosition.latitude - (REFERANCE_LAT * x),
//                        initialPosition.longitude + (REFERANCE_LNG * y)
//                ));
//                markersPosition.add(new LatLng(
//                        initialPosition.latitude + (REFERANCE_LAT * x),
//                        initialPosition.longitude - (REFERANCE_LNG * y)
//                ));
//                markersPosition.add(new LatLng(
//                        initialPosition.latitude + (REFERANCE_LAT * x),
//                        initialPosition.longitude + (REFERANCE_LNG * y)
//                ));
//                markersPosition.add(new LatLng(
//                        initialPosition.latitude - (REFERANCE_LAT * x),
//                        initialPosition.longitude - (REFERANCE_LNG * y)
//                ));
//            }
//        }
//        freeActiveMarkers();
//        for (LatLng markerPosition : markersPosition) {
//            Marker marker = new Marker();
//            marker.setPosition(markerPosition);
//            marker.setMap(naverMap);
//            activeMarkers.add(marker);
//        }
//
//        // 카메라 이동 되면 호출 되는 이벤트
//        naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {
//            @Override
//            public void onCameraChange(int reason, boolean animated) {
//                freeActiveMarkers();
//                // 정의된 마커위치들중 가시거리 내에있는것들만 마커 생성
//                LatLng currentPosition = getCurrentPosition(naverMap);
//                for (LatLng markerPosition: markersPosition) {
//                    if (!withinSightMarker(currentPosition, markerPosition))
//                        continue;
//                    Marker marker = new Marker();
//                    marker.setPosition(markerPosition);
//                    marker.setMap(naverMap);
//                    activeMarkers.add(marker);
//                }
//            }
//        });

//        naverMap.moveCamera(
//                CameraUpdate.toCameraPosition(
//                        new CameraPosition(NaverMap.DEFAULT_CAMERA_POSITION.target, NaverMap.DEFAULT_CAMERA_POSITION.zoom))
//        );
//
//        TedNaverClustering.with(this, naverMap)
//                .items(getItems())
//                .make();
//    }

    // 현재 카메라가 보고있는 위치
//    public LatLng getCurrentPosition(NaverMap naverMap) {
//        CameraPosition cameraPosition = naverMap.getCameraPosition();
//        return new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
//    }
//
//    public List<JavaItem> getItems() {
//        LatLngBounds bounds = naverMap.getContentBounds();
//        ArrayList<JavaItem> list = new ArrayList<>();
//        for(int i = 0; i < 50; i++) {
//            JavaItem item = new JavaItem(new LatLng(bounds.getNorthLatitude() - bounds.getSouthLatitude() * Math.random() + bounds.getSouthLatitude(),
//                    (bounds.getEastLongitude() - bounds.getWestLongitude()) * Math.random() + bounds.getWestLongitude()));
//            list.add(item);
//        }
//        return list;
//    }
//}
//}
