package com.soft.zigbang.src.house.find;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;

public class FilterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }


    public void filterOnClick(View view) {
        switch (view.getId()) {
            case R.id.filter_tv_age_all:
                showCustomToast("전체");
                break;
            case R.id.filter_btn_ok:
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}