package com.soft.zigbang.src.login.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    EditText editEmail;
    EditText editPassword;
    EditText editPasswordCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ImageView ivBack = view.findViewById(R.id.iv_back);
        TextView tvNext = view.findViewById(R.id.tv_register_next);

        ivBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        editEmail = view.findViewById(R.id.edit_email);
        editPassword = view.findViewById(R.id.edit_password);
        editPasswordCheck = view.findViewById(R.id.edit_password_check);

        mRegisterService = new RegisterService(this);
        mParentActivity = (LoginActivity) getActivity();

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                mParentActivity.backFragment(1);
                break;
            case R.id.tv_register_next:
                String email = editEmail.getText().toString();
                String pw = editPassword.getText().toString();
                String pwCheck = editPasswordCheck.getText().toString();
                if (!checkPassword(pw, pwCheck)) return;
                mRegisterService.postRegister(email, pw);
                break;
        }
    }

    @Override
    public void signUpSuccess(String text, int code) {
        mParentActivity.hideProgressDialog();
        mParentActivity.showCustomToast(text);

        if (code == 101) {
            mParentActivity.backFragment(1);
        }
    }

    @Override
    public void signUpFailure(String message) {
        mParentActivity.hideProgressDialog();
        mParentActivity.showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    private boolean checkPassword(String pw, String pwCheck) {
        if (!pw.equals(pwCheck)) {
            mParentActivity.showCustomToast(getString(R.string.password_check));
            return false;
        }

        if(pw.length() < 8) {
            mParentActivity.showCustomToast(getString(R.string.password_cnt));
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
}