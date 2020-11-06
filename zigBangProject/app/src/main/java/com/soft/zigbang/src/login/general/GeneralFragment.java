package com.soft.zigbang.src.login.general;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.login.LoginActivity;
import com.soft.zigbang.src.login.general.interfaces.GeneralFragmentView;
import com.soft.zigbang.src.main.MainActivity;

import static com.soft.zigbang.src.ApplicationClass.userNo;

public class GeneralFragment extends Fragment implements View.OnClickListener, GeneralFragmentView {

    private GeneralService mGeneralService;
    private LoginActivity mParentActivity;

    EditText mEditId;
    EditText mEditPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general, container, false);
        settingView(view);

        mGeneralService = new GeneralService(this);
        mParentActivity = (LoginActivity) getActivity();

        mEditPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int action, KeyEvent event) {
                if (action == EditorInfo.IME_ACTION_DONE || action == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    generalLogin();
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    private void generalLogin() {
        String email = mEditId.getText().toString();
        String pw = mEditPassword.getText().toString();

        if (!checkValidation(email, pw)) return;

        mParentActivity.showProgressDialog();
        mGeneralService.postLogin(email, pw);
    }

    @Override
    public void generalLoginSuccess(int pUserNo) {
        mParentActivity.hideProgressDialog();
        userNo = pUserNo; // 추후 sSharedPreferences 저장

        Intent intent = new Intent(getActivity(), MainActivity.class);
        mParentActivity.startActivity(intent);
        mParentActivity.finish();
    }

    @Override
    public void generalLoginFalse(String text) {
        mParentActivity.hideProgressDialog();
        mParentActivity.showCustomToast(text);
    }

    @Override
    public void generalLoginFailure(String message) {
        mParentActivity.hideProgressDialog();
        mParentActivity.showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                mParentActivity.backFragment(0);
                break;
            case R.id.tv_register:
                mParentActivity.addFragment(1);
                break;
            case R.id.tv_start:
                generalLogin();
                break;
            case R.id.tv_find_id:
            case R.id.tv_find_password:
                mParentActivity.showCustomToast(getString(R.string.noImpl));
                break;
        }
    }

    private boolean checkValidation(String email, String pw) {

        if (email.length() == 0) {
            mParentActivity.showCustomToast(getString(R.string.email_input));
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mParentActivity.showCustomToast(getString(R.string.email_check));
            return false;
        }

        if (pw.length() == 0) {
            mParentActivity.showCustomToast(getString(R.string.password_input));
            return false;
        }

        return true;
    }

    private void settingView(View view) {
        ImageView ivBack = view.findViewById(R.id.iv_back);
        mEditId = view.findViewById(R.id.edit_id);
        mEditPassword = view.findViewById(R.id.edit_password);

        TextView tvStart = view.findViewById(R.id.tv_start);
        TextView tvFindId = view.findViewById(R.id.tv_find_id);
        TextView tvFindPassword = view.findViewById(R.id.tv_find_password);
        TextView tvFindRegister = view.findViewById(R.id.tv_register);

        ivBack.setOnClickListener(this);
        mEditId.setOnClickListener(this);
        mEditPassword.setOnClickListener(this);
        tvStart.setOnClickListener(this);
        tvFindId.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);
        tvFindRegister.setOnClickListener(this);
    }
}