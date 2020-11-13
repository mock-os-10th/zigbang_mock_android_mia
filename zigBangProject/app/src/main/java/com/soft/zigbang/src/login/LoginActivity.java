package com.soft.zigbang.src.login;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.login.general.GeneralFragment;
import com.soft.zigbang.src.login.kakao.interfaces.KaKaoActivityView;
import com.soft.zigbang.src.login.register.RegisterFragment;
import com.soft.zigbang.src.main.MainActivity;


public class LoginActivity extends BaseActivity implements KaKaoActivityView {

    private final FragmentManager fm = getSupportFragmentManager();
    private final Fragment generalFragment = new GeneralFragment();
    private final Fragment registerFragment = new RegisterFragment();

    private LoginButton mBtnKaKaoLogin;
    private LoginService mLoginService;
    private SessionCallback mSessionCallback;
    private Session mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSessionCallback = new SessionCallback();
        mLoginService = new LoginService(this);
        mBtnKaKaoLogin = findViewById(R.id.btn_kakao_login);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    public void LoginOnClick(View v) {
        switch (v.getId()) {
            case R.id.rel_kakao_login:
                mBtnKaKaoLogin.performClick();
                mSession = Session.getCurrentSession();
                mSession.addCallback(mSessionCallback);
                mSession.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
                break;
            case R.id.iv_general:
                fm.beginTransaction().add(R.id.login_container, generalFragment).commit();
                break;
            case R.id.iv_facebook:
            case R.id.iv_google:
                showCustomToast(getString(R.string.noImpl));
                break;
        }
    }

    @Override
    public void kakaoLoginSuccess(int userNo) {
        hideProgressDialog();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void kakaoLoginFailure(String message) {
        showCustomToast(message);
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

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();
                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        showCustomToast(getString(R.string.network_error));
                        finish();
                    } else {
                        showCustomToast(getString(R.string.login_again));
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    showCustomToast(getString(R.string.login_again));
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    showProgressDialog();
                    mLoginService.postKaKaoLogin(mSession.getTokenInfo().getAccessToken());
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {

        }
    }
}