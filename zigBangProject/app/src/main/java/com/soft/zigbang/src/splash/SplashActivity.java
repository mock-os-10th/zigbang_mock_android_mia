package com.soft.zigbang.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.gun0912.tedpermission.TedPermission;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.login.LoginActivity;
import com.soft.zigbang.src.main.MainActivity;

import static com.soft.zigbang.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.soft.zigbang.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String jwt = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
                Intent intent;
                if(jwt == null) {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
