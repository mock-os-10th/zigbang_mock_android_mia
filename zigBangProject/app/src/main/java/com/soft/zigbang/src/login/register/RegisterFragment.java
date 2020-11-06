package com.soft.zigbang.src.login.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.soft.zigbang.src.login.register.interfaces.RegisterFragmentView;
import com.soft.zigbang.src.login.register.interfaces.RegisterRetrofitInterface;
import com.soft.zigbang.src.login.register.models.SignUpBody;
import com.soft.zigbang.src.login.register.models.SignUpResponse;
import com.soft.zigbang.src.main.MainActivity;
import com.soft.zigbang.src.main.models.DefaultResponse;

import retrofit2.Call;


public class RegisterFragment extends Fragment implements View.OnClickListener, RegisterFragmentView {

    private final String TAG = "RegisterFragment";
    private RegisterService mRegisterService;
    private LoginActivity mParentActivity;
    EditText mEditEmail;
    EditText mEditPassword;
    EditText mEditPasswordCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        settingView(view);

        mRegisterService = new RegisterService(this);
        mParentActivity = (LoginActivity) getActivity();

        mEditPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int action, KeyEvent event) {
                if (action == EditorInfo.IME_ACTION_DONE || action == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    signUp();
                    return true;
                }
                return false;
            }
        });

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                mParentActivity.backFragment(1);
                break;
            case R.id.tv_register_next:
                signUp();
                break;
        }
    }

    private void signUp() {
        String email = mEditEmail.getText().toString();
        String pw = mEditPassword.getText().toString();
        String pwCheck = mEditPasswordCheck.getText().toString();
        if (!checkValidation(email, pw, pwCheck)) return;
        mParentActivity.showProgressDialog();
        mRegisterService.postRegister(email, pw);
    }

    @Override
    public void signUpSuccess(String text, int code) {
        mParentActivity.hideProgressDialog();
        mParentActivity.showCustomToast(text);

        if (code == 101) {
            mParentActivity.backFragment(1);
        } else if(code == 201) {
            mParentActivity.showCustomToast(text);
        }
    }

    @Override
    public void signUpFailure(String message) {
        mParentActivity.hideProgressDialog();
        mParentActivity.showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    private boolean checkValidation(String email, String pw, String pwCheck) {
        if(email.length() == 0) {
            mParentActivity.showCustomToast(getString(R.string.email_input));
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mParentActivity.showCustomToast(getString(R.string.email_check));
            return false;
        }

        if (pw.length() == 0) {
            mParentActivity.showCustomToast(getString(R.string.password_input));
            return false;
        }

        if(pw.length() < 8) {
            mParentActivity.showCustomToast(getString(R.string.password_cnt));
            return false;
        }

        if (!pw.equals(pwCheck)) {
            mParentActivity.showCustomToast(getString(R.string.password_check));
            return false;
        }

        boolean numeric = false;
        boolean alpha = false;
        for(int i = 0; i < pw.length(); i++) {
            int index = pw.charAt(i);

            if(index >= 48 && index <= 57) {
                numeric = true;
            } else if(index >= 65 && index <= 122) {
                alpha = true;
            }
        }

        if(!(numeric && alpha)) {
            mParentActivity.showCustomToast(getString(R.string.password_format));
            return false;
        }

        return true;
    }

    private void settingView(View view) {
        ImageView ivBack = view.findViewById(R.id.iv_back);
        TextView tvNext = view.findViewById(R.id.tv_register_next);

        ivBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        mEditEmail = view.findViewById(R.id.edit_email);
        mEditPassword = view.findViewById(R.id.edit_password);
        mEditPasswordCheck = view.findViewById(R.id.edit_password_check);
    }
}