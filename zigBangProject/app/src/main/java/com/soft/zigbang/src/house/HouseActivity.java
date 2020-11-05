package com.soft.zigbang.src.house;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.find.FindMapActivity;
import com.soft.zigbang.src.house.interfaces.HouseActivityView;


public class HouseActivity extends BaseActivity implements HouseActivityView{

    private HouseService mHouseService;
    private TextView mHouseTvTitle, mHouseTvDesc, mHouseTvFirst, mHouseTvSecond;
    private ImageView mHouseIvFirst, mHouseIvSecond;
    private LinearLayout mHouseLinearBigData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        mHouseTvTitle = findViewById(R.id.house_tv_title);
        mHouseTvDesc = findViewById(R.id.house_tv_desc);
        mHouseTvFirst = findViewById(R.id.house_tv_first);
        mHouseTvSecond = findViewById(R.id.house_tv_second);
        mHouseIvFirst = findViewById(R.id.house_iv_first);
        mHouseIvSecond = findViewById(R.id.house_iv_second);
        mHouseLinearBigData = findViewById(R.id.house_linear_big_data);

//        RelativeLayout houseRelFirst = findViewById(R.id.house_rel_first);
//        ImageView mHouseIvBack = findViewById(R.id.house_iv_back);
//        houseRelFirst.setOnClickListener(this);
//        mHouseIvBack.setOnClickListener(this);

        setHouseView();
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.house_rel_first:
//                Intent intent = new Intent(this, FindMapActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.house_rel_second:
//                showCustomToast(getString(R.string.noImpl));
//                break;
//            case R.id.house_iv_back:
//                finish();
//                break;
//        }
//    }

    public void houseOnClick(View view) {
        switch (view.getId()) {
            case R.id.house_rel_first:
                Intent intent = new Intent(this, FindMapActivity.class);
                startActivity(intent);
                break;
            case R.id.house_rel_second:
                showCustomToast(getString(R.string.noImpl));
                break;
            case R.id.house_iv_back:
                finish();
                break;
        }
    }

    /**
     * 타입에 따른 뷰 설정
     */
    private void setHouseView() {
        String type = getIntent().getStringExtra("type");
        switch (type) {
            case "ONE":
                mHouseTvTitle.setText(getString(R.string.oneRoom_title));
                mHouseTvDesc.setText(getString(R.string.oneRoom_desc));
                mHouseTvFirst.setText(getString(R.string.oneRoom_first));
                mHouseTvSecond.setText(getString(R.string.oneRoom_second));
                mHouseLinearBigData.setVisibility(View.GONE);
                break;
        }
    }
}