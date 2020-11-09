package com.soft.zigbang.src.house.find;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;

import java.util.HashMap;

public class FilterActivity extends BaseActivity {

    private HashMap<String, Object> filterMap;

    private RadioGroup radioGroupSell, radioGroupArea1, radioGroupArea2, radioGroupDate1,
            radioGroupDate2, radioGroupAges1, radioGroupAges2;
    private RadioButton radioBtnSell, radioBtnAreaAll, radioBtnDateAll, radioBtnAgesAll;
    private TextView tvSelectType, tvSelectArea, tvSelectDate, tvSelectAges;

    private String sellType = "A";
    private int acreage = 0;
    private int enterAt = 0;
    private int liveNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterMap = new HashMap<>();

        radioGroupSell = findViewById(R.id.filter_radio_group_sell);
        radioGroupArea1 = findViewById(R.id.fliter_radio_group_area_1);
        radioGroupArea2 = findViewById(R.id.fliter_radio_group_area_2);
        radioGroupDate1 = findViewById(R.id.filter_radio_group_date_1);
        radioGroupDate2 = findViewById(R.id.filter_radio_group_date_2);
        radioGroupAges1 = findViewById(R.id.fliter_radio_group_ages_1);
        radioGroupAges2 = findViewById(R.id.fliter_radio_group_ages_2);
        radioBtnSell = findViewById(R.id.filter_radio_sell);
        radioBtnAreaAll = findViewById(R.id.filter_radio_area_all);
        radioBtnDateAll = findViewById(R.id.filter_radio_date_all);
        radioBtnAgesAll = findViewById(R.id.filter_radio_age_all);
        tvSelectType = findViewById(R.id.filter_tv_select_type);
        tvSelectArea = findViewById(R.id.filter_tv_select_area);
        tvSelectDate = findViewById(R.id.filter_tv_select_date);
        tvSelectAges = findViewById(R.id.filter_tv_select_ages);

        radioGroupSell.setOnCheckedChangeListener(sellListener);
        radioGroupArea1.setOnCheckedChangeListener(areaListener1);
        radioGroupArea2.setOnCheckedChangeListener(areaListener2);
        radioGroupDate1.setOnCheckedChangeListener(dateListener1);
        radioGroupDate2.setOnCheckedChangeListener(dateListener2);
        radioGroupAges1.setOnCheckedChangeListener(agesListener1);
        radioGroupAges2.setOnCheckedChangeListener(agesListener2);

        resetButton();

    }

    private final RadioGroup.OnCheckedChangeListener sellListener = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.filter_radio_sell:
                    sellType = "A";
                    tvSelectType.setText("매매");
                    break;
                case R.id.filter_radio_month:
                    sellType = "B";
                    tvSelectType.setText("전월세");
                    break;
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener areaListener1 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroupArea2.setOnCheckedChangeListener(null);
            radioGroupArea2.clearCheck();
            radioGroupArea2.setOnCheckedChangeListener(areaListener2);

            switch (checkedId) {
                case R.id.filter_radio_area_all:
                    tvSelectArea.setText("전체");
                    acreage = 0;
                    break;
                case R.id.filter_radio_area_low_10:
                    tvSelectArea.setText("10평 이하");
                    acreage = 1;
                    break;
                case R.id.filter_radio_area_10:
                    tvSelectArea.setText("10평대");
                    acreage = 2;
                    break;
                case R.id.filter_radio_area_20:
                    tvSelectArea.setText("20평대");
                    acreage = 3;
                    break;
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener areaListener2 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroupArea1.setOnCheckedChangeListener(null);
            radioGroupArea1.clearCheck();
            radioGroupArea1.setOnCheckedChangeListener(areaListener1);

            switch (checkedId) {
                case R.id.filter_radio_area_30:
                    tvSelectArea.setText("30평대");
                    acreage = 4;
                    break;
                case R.id.filter_radio_area_40:
                    tvSelectArea.setText("40평대");
                    acreage = 5;
                    break;
                case R.id.filter_radio_area_50:
                    tvSelectArea.setText("50평대");
                    acreage = 6;
                    break;
                case R.id.filter_radio_area_60:
                    tvSelectArea.setText("60평 이상");
                    acreage = 7;
                    break;
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener dateListener1 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroupDate2.setOnCheckedChangeListener(null);
            radioGroupDate2.clearCheck();
            radioGroupDate2.setOnCheckedChangeListener(dateListener2);

            switch (checkedId) {
                case R.id.filter_radio_date_all:
                    tvSelectDate.setText("전체");
                    enterAt = 0;
                    break;
                case R.id.filter_date_5_years:
                    tvSelectDate.setText("5년 이내");
                    enterAt = 5;
                    break;
                case R.id.filter_date_10_years:
                    tvSelectDate.setText("10년 이내");
                    enterAt = 10;
                    break;
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener dateListener2 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroupDate1.setOnCheckedChangeListener(null);
            radioGroupDate1.clearCheck();
            radioGroupDate1.setOnCheckedChangeListener(dateListener1);

            switch (checkedId) {
                case R.id.filter_date_15_years:
                    tvSelectDate.setText("15년 이내");
                    enterAt = 15;
                    break;
                case R.id.filter_date_20_years:
                    tvSelectDate.setText("20년 이내");
                    enterAt = 20;
                    break;
                case R.id.filter_date_25_years:
                    tvSelectDate.setText("25년 이내");
                    enterAt = 25;
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener agesListener1 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroupAges2.setOnCheckedChangeListener(null);
            radioGroupAges2.clearCheck();
            radioGroupAges2.setOnCheckedChangeListener(agesListener2);

            switch (checkedId) {
                case R.id.filter_radio_age_all:
                    tvSelectAges.setText("전체");
                    liveNum = 0;
                    break;
                case R.id.filter_radio_age_200:
                    tvSelectAges.setText("200세대 이상");
                    liveNum = 200;
                    break;
                case R.id.filter_radio_age_500:
                    tvSelectAges.setText("500세대 이상");
                    liveNum = 500;
                    break;
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener agesListener2 = new RadioGroup.OnCheckedChangeListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            radioGroupAges1.setOnCheckedChangeListener(null);
            radioGroupAges1.clearCheck();
            radioGroupAges1.setOnCheckedChangeListener(agesListener1);

            switch (checkedId) {
                case R.id.filter_radio_age_1000:
                    tvSelectAges.setText("1000세대 이상");
                    liveNum = 1000;
                    break;
                case R.id.filter_radio_age_2000:
                    tvSelectAges.setText("2000세대 이상");
                    liveNum = 2000;
                    break;
            }
        }
    };

    @SuppressLint("NonConstantResourceId")
    public void filterOnClick(View view) {
        switch (view.getId()) {
            case R.id.filter_btn_ok:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                filterMap.put("sellType", sellType);
                filterMap.put("acreage", acreage);
                filterMap.put("enterAt", enterAt);
                filterMap.put("liveNum", liveNum);

                bundle.putSerializable("filterMap", filterMap);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.filter_tv_reset:
                resetButton();
                break;
            case R.id.filter_iv_close:
                finish();
                break;
        }
    }

    private void resetButton() {
        radioGroupSell.clearCheck();
        radioGroupArea1.clearCheck();
        radioGroupArea2.clearCheck();
        radioGroupDate1.clearCheck();
        radioGroupDate2.clearCheck();
        radioGroupAges1.clearCheck();
        radioGroupAges2.clearCheck();

        radioBtnSell.setChecked(true);
        radioBtnAreaAll.setChecked(true);
        radioBtnDateAll.setChecked(true);
        radioBtnAgesAll.setChecked(true);
    }
}