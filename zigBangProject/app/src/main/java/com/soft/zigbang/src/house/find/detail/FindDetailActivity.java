package com.soft.zigbang.src.house.find.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.models.FindResponse;

public class FindDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);

        FindResponse.Result apart = (FindResponse.Result) getIntent().getExtras().getSerializable("apart");
        apart.toString();
    }
}