package com.soft.zigbang.src.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soft.zigbang.R;


public class RegisterFragment extends Fragment implements View.OnClickListener {

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

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                ((LoginActivity)getActivity()).backFragment(1);
                break;
            case R.id.tv_register_next:
                // 입력 정보 넘겨준다
                break;
        }
    }
}