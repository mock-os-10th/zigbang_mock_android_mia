package com.soft.zigbang.src.house.find.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.models.FindResponse;

import java.util.ArrayList;

public class FindDetailActivity extends AppCompatActivity {

    private FindResponse.Result mApart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);

        Bundle args = getIntent().getExtras();
        if(args != null) {
            ArrayList<FindResponse.Result> list = (ArrayList<FindResponse.Result>) args.getSerializable("apartList");
            mApart = list.get(0);
        }


    }
}