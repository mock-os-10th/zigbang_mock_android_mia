package com.soft.zigbang.src.house.oneroom;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.apart.ApartMapActivity;
import com.soft.zigbang.src.house.apart.models.MyItem;
import com.soft.zigbang.src.house.find.models.FindResponse;
import com.soft.zigbang.src.house.oneroom.interfaces.OneRoomMapActivityView;
import com.soft.zigbang.src.house.oneroom.list.OneRoomListActivity;
import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OneRoomMapActivity extends BaseActivity implements OneRoomMapActivityView, OnMapReadyCallback, View.OnTouchListener {

    private OneRoomService mOneRoomService;
    private GoogleMap mGoogleMap;
    private ClusterManager mClusterManager;
    private List<OneRoomResponse.Result> markerList;
    private SupportMapFragment mapFragment;
    private TextView mOneRoomTvSee;
    private ProgressBar progressBar;

    private double minLat = 0, maxLat = 0, minLnt = 0, maxLnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_room_map);
        View view = new View(this);
        view.setOnTouchListener(this);
        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_view);
        mOneRoomTvSee = findViewById(R.id.one_room_tv_see);
        LinearLayout linear = findViewById(R.id.linear);
        progressBar = findViewById(R.id.progress_bar);

        mOneRoomService = new OneRoomService(this);
//        showProgressDialog();
        mOneRoomService.getOneRoomList("B", "map", "A");

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Double> location = new ArrayList<>();
                location.add(minLnt);
                location.add(maxLnt);
                location.add(minLat);
                location.add(maxLat);

                Bundle bundle = new Bundle();
                bundle.putSerializable("location", location);
                Intent intent = new Intent(getApplicationContext(), OneRoomListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getOneRoomListSuccess(int code, List<OneRoomResponse.Result> results) {
        hideProgressDialog2(progressBar);
        markerList = results;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void getOneRoomListFailure(String message) {
        hideProgressDialog2(progressBar);
        showCustomToast("조회할 수 없습니다.");
    }

    @Override
    public void getOneRoomMapCntSuccess(int code, List<OneRoomResponse.Result> results) {
//        hideProgressDialog2(progressBar);
        mOneRoomTvSee.setText(String.valueOf(results.get(0).getNum()));
    }

    @Override
    public void getOneRoomMapCntFailure(String message) {
        hideProgressDialog2(progressBar);
        showCustomToast("failure");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mClusterManager = new ClusterManager<MyItem>(getApplicationContext(), mGoogleMap);

        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLng latLng = new LatLng(37.50631800000000, 126.94389700000000);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));//9
            }
        });


        mClusterManager.setRenderer(new CustomIconRenderer(getApplicationContext(), mGoogleMap, mClusterManager));

        for (OneRoomResponse.Result oneRoom : markerList) {
            mClusterManager.addItem(new MyItem(oneRoom.getLatitude(), oneRoom.getLongitude()));
        }

        mGoogleMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new ItemAdapter(LayoutInflater.from(this)));
        mGoogleMap.setOnCameraIdleListener(mClusterManager);
        mGoogleMap.setOnMarkerClickListener(mClusterManager);

        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLngBounds bounds = mGoogleMap.getProjection().getVisibleRegion().latLngBounds;
                Log.d("OneRoom! northeast", String.valueOf(bounds.northeast));
                Log.d("OneRoom! southwest", String.valueOf(bounds.southwest));

                String northEast = String.valueOf(bounds.northeast);
                String southWest = String.valueOf(bounds.southwest);

                String str1 = northEast.substring(10, northEast.lastIndexOf(")"));
                String str2 = southWest.substring(10, southWest.lastIndexOf(")"));

                String[] value1 = str1.split(",");
                String[] value2 = str2.split(",");

                maxLat = Double.parseDouble(value1[0]);
                maxLnt = Double.parseDouble(value1[1]);
                minLat = Double.parseDouble(value2[0]);
                minLnt = Double.parseDouble(value2[1]);

//                showProgressDialog2(progressBar);
                mOneRoomService.getOneRoomCnt(minLnt, maxLnt, minLat, maxLat);

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
            final Drawable clusterIcon = mContext.getDrawable(R.drawable.cluster_marker);
            iconGenerator.setBackground(clusterIcon);
            iconGenerator.setTextAppearance(com.google.maps.android.R.style.amu_ClusterIcon_TextAppearance);

            Bitmap icon = iconGenerator.makeIcon(String.valueOf(1));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected void onClusterRendered(Cluster<MyItem> cluster, Marker marker) {
            Log.d("OneRoom!", "onClusterRendered");
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {

            final Drawable clusterIcon = mContext.getDrawable(R.drawable.cluster_marker);
            iconGenerator.setBackground(clusterIcon);
            iconGenerator.setTextAppearance(com.google.maps.android.R.style.amu_ClusterIcon_TextAppearance);

            if (cluster.getSize() >= 10) {
                iconGenerator.setContentPadding(80, 60, 80, 60);
            } else {
                iconGenerator.setContentPadding(80, 55, 80, 55);
            }

            Bitmap icon = iconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected void onClusterItemRendered(MyItem clusterItem, Marker marker) {
            super.onClusterItemRendered(clusterItem, marker);
            Log.d("OneRoom!", "onClusterItemRendered");
        }
    }

    class ItemAdapter implements GoogleMap.InfoWindowAdapter {
        private final LayoutInflater mInflater;

        public ItemAdapter(LayoutInflater mInflater) {
            this.mInflater = mInflater;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            View view = mInflater.inflate(R.layout.no_window, null);
            return view;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    public void oneRoomOnClick(View view) {

    }

    @Override
    public void getOneRoomsSuccess(int code, List<OneRoomResponse.Result> results) {

    }

    @Override
    public void getOneRoomsFailure(String message) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("OneRoom!", "A");
        return false;
    }
}