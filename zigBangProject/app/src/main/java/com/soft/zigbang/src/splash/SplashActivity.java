package com.soft.zigbang.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.login.LoginActivity;

import static com.soft.zigbang.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(sSharedPreferences != null) {
            String token = sSharedPreferences.getString("X-ACCESS-TOKEN", "no");
            token.toString();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
