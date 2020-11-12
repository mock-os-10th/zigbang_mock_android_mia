package com.soft.zigbang.src.house.oneroom;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import java.util.List;

public class OneRoomMapActivity extends BaseActivity implements OneRoomMapActivityView, OnMapReadyCallback {

    private OneRoomService mOneRoomService;
    private GoogleMap mGoogleMap;
    private ClusterManager mClusterManager;
    private List<OneRoomResponse.Result> markerList;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_room_map);

        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_view);

        mOneRoomService = new OneRoomService(this);
        showProgressDialog();
        mOneRoomService.getOneRoomList("B", "map", "A");
//        mOneRoomService.getOneRoomCnt(128,129,38,39);
    }

    @Override
    public void getOneRoomListSuccess(int code, List<OneRoomResponse.Result> results) {
        hideProgressDialog();
        markerList = results;
        showCustomToast("success");
        mapFragment.getMapAsync(this);
    }

    @Override
    public void getOneRoomListFailure(String message) {
        hideProgressDialog();
        showCustomToast("failure");
    }

    @Override
    public void getOneRoomMapCntSuccess(int code, List<OneRoomResponse.Result> results) {
        hideProgressDialog();
        showCustomToast("success");
    }

    @Override
    public void getOneRoomMapCntFailure(String message) {
        hideProgressDialog();
        showCustomToast("failure");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mClusterManager = new ClusterManager<MyItem>(getApplicationContext(), mGoogleMap);

        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLng latLng = new LatLng(37.565166394574, 126.97878855758);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
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
            Bitmap bm = iconGenerator.makeIcon("1"); //
            iconGenerator.setBackground(mContext.getDrawable(R.drawable.cluster_marker));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bm)); // 여기까지 원룸
//                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(mContext, markerView)));
            markerOptions.snippet(item.getSnippet());
            markerOptions.title(item.getTitle());
            super.onBeforeClusterItemRendered(item, markerOptions);
        }

        @Override
        protected void onClusterRendered(Cluster<MyItem> cluster, Marker marker) {
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {

            final Drawable clusterIcon = mContext.getDrawable(R.drawable.cluster_marker);
            iconGenerator.setBackground(clusterIcon);
            iconGenerator.setTextAppearance(com.google.maps.android.R.style.amu_ClusterIcon_TextAppearance);
            iconGenerator.setContentPadding(80, 45, 80, 45);

            Bitmap icon = iconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
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
}