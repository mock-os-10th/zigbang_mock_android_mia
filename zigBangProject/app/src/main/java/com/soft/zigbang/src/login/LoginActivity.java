package com.soft.zigbang.src.login;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.login.general.GeneralFragment;
import com.soft.zigbang.src.login.register.RegisterFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private final FragmentManager fm = getSupportFragmentManager();
    private final Fragment generalFragment = new GeneralFragment();
    private final Fragment registerFragment = new RegisterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnKaKao = findViewById(R.id.btn_login_kakao);
        ImageView ivFacebook = findViewById(R.id.iv_facebook);
        ImageView ivGoogle = findViewById(R.id.iv_google);
        ImageView ivGeneral = findViewById(R.id.iv_general);

        btnKaKao.setOnClickListener(this);
        ivFacebook.setOnClickListener(this);
        ivGoogle.setOnClickListener(this);
        ivGeneral.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_kakao:
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