package com.soft.zigbang.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.gun0912.tedpermission.TedPermission;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.login.LoginActivity;
import com.soft.zigbang.src.main.MainActivity;
import com.soft.zigbang.src.splash.interfaces.SplashActivityView;

import static com.soft.zigbang.src.ApplicationClass.SUCCESS_CODE;
import static com.soft.zigbang.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.soft.zigbang.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity implements SplashActivityView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashService splashService = new SplashService(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String jwt = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
                if(jwt == null) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
//                    showProgressDialog();
                    splashService.getJwt();
                }
            }
        }, 1000);
    }

    @Override
    public void getJwtSuccess(int code, String message) {
        hideProgressDialog();
        if(code == SUCCESS_CODE) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            showCustomToast("다시 로그인해주세요.");
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void getJwtFailure(String message) {
        showCustomToast(getString(R.string.network_error));
    }
}
