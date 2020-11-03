package com.soft.zigbang.src.login.general;

import android.content.Intent;
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
import com.soft.zigbang.src.login.LoginActivity;
import com.soft.zigbang.src.login.general.interfaces.GeneralFragmentView;
import com.soft.zigbang.src.main.MainActivity;
import static com.soft.zigbang.src.ApplicationClass.userNo;

public class GeneralFragment extends Fragment implements View.OnClickListener, GeneralFragmentView {

    private GeneralService mGeneralService;
    private LoginActivity mParentActivity;

    EditText editId;
    EditText editPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general, container, false);

        ImageView ivBack = view.findViewById(R.id.iv_back);
        editId = view.findViewById(R.id.edit_id);
        editPassword = view.findViewById(R.id.edit_password);

        TextView tvStart = view.findViewById(R.id.tv_start);
        TextView tvFindId = view.findViewById(R.id.tv_find_id);
        TextView tvFindPassword = view.findViewById(R.id.tv_find_password);
        TextView tvFindRegister = view.findViewById(R.id.tv_register);

        ivBack.setOnClickListener(this);
        editId.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        tvStart.setOnClickListener(this);
        tvFindId.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);
        tvFindRegister.setOnClickListener(this);

        mGeneralService = new GeneralService(this);
        mParentActivity = (LoginActivity) getActivity();

        return view;
    }

    @Override
    public void generalLoginSuccess(String text, int pUserNo) {
        mParentActivity.hideProgressDialog();
        userNo = pUserNo;
        Intent intent = new Intent(getActivity(), MainActivity.class);
        mParentActivity.startActivity(intent);
        mParentActivity.finish();
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
                String email = editId.getText().toString();
                String pw = editPassword.getText().toString();
//                mGeneralService.postLogin(email, pw);
                mGeneralService.postLogin("suy0226@naver.com", "test1234");
                break;
            case R.id.tv_find_id:
            case R.id.tv_find_password:
                mParentActivity.showCustomToast(getString(R.string.noImpl));
                break;
        }


    }
}