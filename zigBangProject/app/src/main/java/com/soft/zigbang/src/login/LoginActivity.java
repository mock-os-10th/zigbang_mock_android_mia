package com.soft.zigbang.src.login;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.login.general.GeneralFragment;
import com.soft.zigbang.src.login.register.RegisterFragment;

import static com.soft.zigbang.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity{

    private final FragmentManager fm = getSupportFragmentManager();
    private final Fragment generalFragment = new GeneralFragment();
    private final Fragment registerFragment = new RegisterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginOnClick(View v) {
        switch (v.getId()) {
            case R.id.rel_kakao_login:
            case R.id.iv_facebook:
            case R.id.iv_google:
                showCustomToast(getString(R.string.noImpl));
                break;
            case R.id.iv_general:
                fm.beginTransaction().add(R.id.login_container, generalFragment).commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void backFragment(int index) {
        switch (index) {
            case 0:
                fm.beginTransaction().remove(generalFragment).commit();
                break;
            case 1:
                fm.beginTransaction().remove(registerFragment).commit();
                break;
        }

    }

    public void addFragment(int index) {
        switch (index) {
            case 0:
                fm.beginTransaction().add(R.id.login_container, generalFragment).commit();
                break;
            case 1:
                fm.beginTransaction().add(R.id.login_container, registerFragment).commit();
                break;
        }
    }
}