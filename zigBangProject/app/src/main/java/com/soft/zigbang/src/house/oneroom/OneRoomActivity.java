package com.soft.zigbang.src.house.oneroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.oneroom.sell.SellHouseActivity;

public class OneRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_room);
    }

    public void oneRoomOnClick(View view) {
        switch (view.getId()) {
            case R.id.one_room_rel_sell:
                Intent intent = new Intent(this, SellHouseActivity.class);
                startActivity(intent);
                break;
            case R.id.one_room_iv_back:
                finish();
                break;
        }

    }
}