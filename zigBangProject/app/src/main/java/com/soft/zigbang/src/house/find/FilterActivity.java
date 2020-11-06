package com.soft.zigbang.src.house.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;

import java.util.HashMap;

public class FilterActivity extends BaseActivity {

    private HashMap<String, Object> filterMap;
    private int typeIndex, areaIndex, dateIndex, ageIndex;
    private TextView filterTvSell, filterTvMonth,
            filterTvAreaAll,filterTvAreaLow10,filterTvArea10, filterTvArea20,
            filterTvArea30,filterTvArea40,filterTvArea50,filterTvArea60,
            filterTvDateAll,filterTvDate5years,filterTvDate10years,
            filterTvDate15years,filterTvDate20years,filterTvDate25years,
            filterTvAgeAll,filterTvAge200,filterTvAge500,filterTvAge1000,filterTvAge2000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterMap = new HashMap<>();

        typeIndex = areaIndex = dateIndex = ageIndex = 0;
    }


    public void filterType(View view) {
        TextView filterTv = (TextView) view;
        switch (view.getId()) {
            case R.id.filter_tv_sell:
                typeIndex = 0;
                unSelect(filterTv);
                break;
            case R.id.filter_tv_month:
                typeIndex = 1;
                select(filterTv);
                break;
        }
    }

    private void select(TextView textView) {
        textView.setBackgroundResource(R.drawable.input_back_fliter);
        textView.setTextColor(getColor(R.color.white));
    }

    private void unSelect(TextView textView) {
        textView.setBackgroundColor(getColor(R.color.white));
        textView.setBackgroundResource(R.drawable.input_back);
        textView.setTextColor(getColor(R.color.black));
    }

    public void filterArea(View view) {
        TextView filterTv = (TextView) view;
        switch (view.getId()) {
            case R.id.filter_tv_area_all:
                ((TextView)view).setTextColor(getColor(R.color.white));
                break;
            case R.id.filter_tv_area_low_10:
                break;
            case R.id.filter_tv_area_10:
                break;
            case R.id.filter_tv_area_20:
                break;
            case R.id.filter_tv_area_30:
                break;
            case R.id.filter_tv_area_40:
                break;
            case R.id.filter_tv_area_50:
                break;
            case R.id.filter_tv_area_60:
                break;
        }
    }
    public void filterDate(View view) {
        TextView filterTv = (TextView) view;
        switch (view.getId()) {
            case R.id.filter_date_all:
                break;
            case R.id.filter_date_5_years:
                break;
            case R.id.filter_date_10_years:
                break;
            case R.id.filter_date_15_years:
                break;
            case R.id.filter_date_20_years:
                break;
        }
    }
    public void filterAge(View view) {
        TextView filterTv = (TextView) view;
        switch (view.getId()) {
            case R.id.filter_tv_age_all:
                break;
            case R.id.filter_tv_age_200:
                break;
            case R.id.filter_tv_age_500:
                break;
            case R.id.filter_tv_age_1000:
                break;
            case R.id.filter_tv_age_2000:
                break;
        }
    }

    public void filterOnClick(View view) {
        switch (view.getId()) {
            case R.id.filter_btn_ok:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                filterMap.put("sellType", "B");
                filterMap.put("acreage", "0");
                filterMap.put("enterAt", "10");
                filterMap.put("liveNum", "100");

                bundle.putSerializable("filterMap", filterMap);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.filter_iv_close:
                finish();
                break;
        }
    }

}