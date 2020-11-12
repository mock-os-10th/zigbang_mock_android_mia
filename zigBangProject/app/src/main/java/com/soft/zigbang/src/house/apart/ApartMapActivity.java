package com.soft.zigbang.src.house.apart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
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
import com.soft.zigbang.src.house.apart.models.MyItem;
import com.soft.zigbang.src.house.find.FilterActivity;
import com.soft.zigbang.src.house.find.FindListFragment;
import com.soft.zigbang.src.house.find.FindMapService;
import com.soft.zigbang.src.house.find.detail.FindDetailActivity;
import com.soft.zigbang.src.house.find.interfaces.FindMapActivityView;
import com.soft.zigbang.src.house.find.models.FindResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApartMapActivity extends BaseActivity implements OnMapReadyCallback, FindMapActivityView {
    private final String TAG = "ApartMap";

    GoogleMap mGoogleMap;
    ClusterManager clusterManager;
    View markerView;

    private FindMapService mFindMapService;
    private FindListFragment mFindListFragment;
    private SupportMapFragment mapFragment;

    private ImageView mFindIvApart;
    private RelativeLayout mFindRelApart;
    private TextView mFindTvApartName, mFindTvApartAddress, mFindTvApartDate, mFindTvType;

    private boolean isShowApart;
    private int orgIndex = 0;
    private int from, to;

    private HashMap<String, Object> mFilterMap;
    private List<FindResponse.Result> mApartList = null;
    private List<FindResponse.Result> mSearchList = null;
    private List<FindResponse.Result> markerList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apart_map);

        mFindMapService = new FindMapService(this);

        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_view);
        markerView = getLayoutInflater().inflate(R.layout.info_window, null);
        mFindRelApart = findViewById(R.id.find_rel_apart);
        mFindTvApartName = findViewById(R.id.find_tv_apart_name);
        mFindTvApartAddress = findViewById(R.id.find_tv_apart_address);
        mFindTvApartDate = findViewById(R.id.find_tv_apart_date);
        mFindTvType = findViewById(R.id.find_tv_type);
        mFindIvApart = findViewById(R.id.find_iv_apart);

//        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFindRelApart.setVisibility(View.INVISIBLE);
        showProgressDialog();

        if (mFilterMap != null && mFilterMap.size() > 0) {
            String sellType = mFilterMap.get("sellType").toString();
            int acreage = Integer.parseInt(mFilterMap.get("acreage").toString());
            int enterAt = Integer.parseInt(mFilterMap.get("enterAt").toString());
            int liveNum = Integer.parseInt(mFilterMap.get("liveNum").toString());
            mFindMapService.getSearchApartList(sellType, acreage, enterAt, liveNum);
        } else {
            mFindMapService.getApartList();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {

        mGoogleMap = map;
        clusterManager = new ClusterManager<MyItem>(getApplicationContext(), mGoogleMap);

        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLng latLng = new LatLng(37.49419489574496, 126.98111549517787);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
            }
        });


        clusterManager.setRenderer(new CustomIconRenderer(getApplicationContext(), mGoogleMap, clusterManager));
        markerList = (mSearchList != null && mSearchList.size() > 0) ? mSearchList : mApartList;
        for (FindResponse.Result apart : markerList) {
            MyItem myItem = new MyItem(apart.getLatitude(), apart.getLongitude(), apart.getName(), apart.getMinPrice(), apart.getMaxPrice(), apart.getApartIndex());
            clusterManager.addItem(myItem);
        }

        mGoogleMap.setInfoWindowAdapter(clusterManager.getMarkerManager());
        clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new ItemAdapter(LayoutInflater.from(this)));
        mGoogleMap.setOnCameraIdleListener(clusterManager);
        mGoogleMap.setOnMarkerClickListener(clusterManager);


        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mFindRelApart.setVisibility(View.INVISIBLE);
            }
        });

        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLngBounds bounds = mGoogleMap.getProjection().getVisibleRegion().latLngBounds;
                bounds.getCenter();
            }
        });
//        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener() {
//            @Override
//            public boolean onClusterClick(Cluster cluster) {
//                showCustomToast("cluster");
//                return false;
//            }
//        });
//
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                Log.d("cluster", "clicked");
                return true;
            }
        });

        /**
         * 마커 클릭
         */
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem myItem) {
                int arrayIdx = 0;

                if (orgIndex == 0) {
                    orgIndex = myItem.getApartIndex();
                }

                if (orgIndex != myItem.getApartIndex()) {
                    orgIndex = myItem.getApartIndex();
                    isShowApart = false;
                }

                for(int i = 0; i < markerList.size(); i++) {
                    if(markerList.get(i).getApartIndex() == myItem.getApartIndex()) {
                        arrayIdx = i;
                        break;
                    }
                }

                FindResponse.Result apart = markerList.get(arrayIdx);
                TranslateAnimation animate;

                if(apart.getImage() != null && apart.getImage() != "") {
                    Glide.with(ApartMapActivity.this)
                            .load(Uri.parse(apart.getImage()))
                            .into(mFindIvApart);
                }
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

                return false;
            }
        });
    }

    /**
     * 아파트 목록 조회 성공
     */
    @Override
    public void getApartListSuccess(String text, List<FindResponse.Result> apartList) {
        hideProgressDialog();
        mApartList = apartList;
        mapFragment.getMapAsync(this);
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
    public void getApartSuccess(List<FindResponse.Result> apartList) {
        hideProgressDialog();
        if (apartList != null && apartList.size() > 0) {
            Intent intent = new Intent(this, FindDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("apartList", (Serializable) apartList);
//            bundle.putSerializable("schools", (Serializable) schools);
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
//        mMapView.removeAllPOIItems();
        mGoogleMap.clear();

//        mApartList = apartList;
        mSearchList = apartList;
        mapFragment.getMapAsync(this);
//        onMapReady(mGoogleMap);
        if (apartList.size() == 0) {
            showCustomToast("해당 조건에 맞는 아파트가 없습니다.");
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
        mFilterMap = new HashMap<>();
        mSearchList = new ArrayList<>();
        if (resultCode == RESULT_OK) {
            mFilterMap = (HashMap<String, Object>) data.getExtras().getSerializable("filterMap");
        }
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
//            Bitmap bm =iconGenerator.makeIcon("1"); //
//            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bm)); // 여기까지 원룸
            TextView tvApartName = markerView.findViewById(R.id.tv_apart_name);
            TextView tvApartPriceMin = markerView.findViewById(R.id.tv_apart_price_min);
            TextView tvApartPriceMax = markerView.findViewById(R.id.tv_apart_price_max);

            tvApartPriceMin.setText(item.getMinPrice() + "천만");
            tvApartPriceMax.setText("~" + item.getMaxPrice() + "천만");
            tvApartName.setText(item.getApartName());

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(mContext, markerView)));
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
//            if (cluster.getSize() < 10) {
//                iconGenerator.setContentPadding(80, 60, 80, 60);
//            } else {
//                iconGenerator.setContentPadding(30, 20, 0, 0);
//            }

            Bitmap icon = iconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        private Bitmap createDrawableFromView(Context context, View view) {

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
            view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
            view.buildDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);

            return bitmap;
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
