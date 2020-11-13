package com.soft.zigbang.src.house.oneroom.list;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.oneroom.OneRoomService;
import com.soft.zigbang.src.house.oneroom.interfaces.OneRoomMapActivityView;
import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import java.util.ArrayList;
import java.util.List;

public class OneRoomListActivity extends BaseActivity implements OneRoomMapActivityView {

    private TextView tvTitle;
    private RecyclerView rvOneRoomList;
    private OneRoomService mOneRoomService;
    private ArrayList<Double> mLocation;
    private OneRoomListAdapter mAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_room_list);

        mOneRoomService = new OneRoomService(this);

        tvTitle = findViewById(R.id.one_room_list_tv_title);
        rvOneRoomList = findViewById(R.id.rv_one_room_list);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvOneRoomList.setLayoutManager(manager);
        mAdapter = new OneRoomListAdapter();
        mLocation = (ArrayList<Double>) getIntent().getExtras().getSerializable("location");
        progressBar = findViewById(R.id.progress_bar);

    }

    @Override
    protected void onStart() {
        super.onStart();

        showProgressDialog2(progressBar);
        mOneRoomService.getOneRooms("B", "list", "A",
                mLocation.get(0), mLocation.get(1), mLocation.get(2), mLocation.get(3), 18);
    }

    @Override
    public void getOneRoomsSuccess(int code, List<OneRoomResponse.Result> results) {
        hideProgressDialog2(progressBar);

        tvTitle.setText("지역 목록 " + results.size() + "개");
        mAdapter.setOneRoomList((ArrayList<OneRoomResponse.Result>) results);
        rvOneRoomList.setAdapter(mAdapter);
    }

    @Override
    public void getOneRoomsFailure(String message) {
        hideProgressDialog2(progressBar);
        showCustomToast("success");
    }

    @Override
    public void getOneRoomListSuccess(int code, List<OneRoomResponse.Result> results) {

    }

    @Override
    public void getOneRoomListFailure(String message) {

    }

    @Override
    public void getOneRoomMapCntSuccess(int code, List<OneRoomResponse.Result> results) {

    }

    @Override
    public void getOneRoomMapCntFailure(String message) {

    }
}