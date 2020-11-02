package com.soft.zigbang.src.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soft.zigbang.R;

public class GeneralFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general, container, false);

        ImageView ivBack = view.findViewById(R.id.iv_back);
        EditText editId = view.findViewById(R.id.edit_id);
        EditText editPassword = view.findViewById(R.id.edit_password);
        TextView tvFindId = view.findViewById(R.id.tv_find_id);
        TextView tvFindPassword = view.findViewById(R.id.tv_find_password);
        TextView tvFindRegister = view.findViewById(R.id.tv_register);

        ivBack.setOnClickListener(this);
        editId.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        tvFindId.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);
        tvFindRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                ((LoginActivity)getActivity()).backFragment(0);
                break;
            case R.id.tv_register:
                ((LoginActivity)getActivity()).addFragment(1);
                break;
            case R.id.tv_start:
                break;
            case R.id.tv_find_id:
            case R.id.tv_find_password:
                ((LoginActivity)getActivity()).showCustomToast("미구현 상태입니다");
//                Toast.makeText(getContext(), "미구현 상태입니다", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}