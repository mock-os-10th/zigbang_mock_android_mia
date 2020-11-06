package com.soft.zigbang.src.house.apart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.house.find.FindMapActivity;

public class ApartActivity extends BaseActivity {

    private TextView mApartTvTitle, mApartTvDesc, mApartTvFirst, mApartTvSecond;
    private ImageView mApartIvFirst, mApartIvSecond;
    private LinearLayout mApartLinearBigData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apart);

        mApartTvFirst = findViewById(R.id.apart_tv_first);
        mApartTvSecond = findViewById(R.id.apart_tv_second);
        mApartIvFirst = findViewById(R.id.apart_iv_first);
        mApartIvSecond = findViewById(R.id.apart_iv_second);
        mApartLinearBigData = findViewById(R.id.apart_linear_big_data);
    }

    public void apartOnClick(View view) {
        switch (view.getId()) {
            case R.id.apart_rel_first:
                Intent intent = new Intent(this, FindMapActivity.class);
                startActivity(intent);
                break;
            case R.id.apart_rel_second:
                showCustomToast(getString(R.string.noImpl));
                break;
            case R.id.apart_iv_back:
                finish();
                break;
        }
    }
}