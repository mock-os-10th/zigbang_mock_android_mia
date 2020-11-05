package com.soft.zigbang.src.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.HouseActivity;


public class MainFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RelativeLayout apartLayout = view.findViewById(R.id.apart_layout);
        RelativeLayout oneRoomLayout = view.findViewById(R.id.one_room_layout);
        RelativeLayout twoRoomLayout = view.findViewById(R.id.two_room_layout);
        RelativeLayout officeTelLayout = view.findViewById(R.id.officetel_layout);
        RelativeLayout storeLayout = view.findViewById(R.id.store_layout);
        RelativeLayout officeLayout = view.findViewById(R.id.office_layout);

        apartLayout.setOnClickListener(this);
        oneRoomLayout.setOnClickListener(this);
        twoRoomLayout.setOnClickListener(this);
        officeTelLayout.setOnClickListener(this);
        storeLayout.setOnClickListener(this);
        officeLayout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), HouseActivity.class);
        switch (v.getId()) {
            case R.id.apart_layout:
                intent.putExtra("type", "APART");
                getActivity().startActivity(intent);
                break;
            case R.id.one_room_layout:
                intent.putExtra("type", "ONE");
                getActivity().startActivity(intent);
                break;
            case R.id.two_room_layout:
            case R.id.officetel_layout:
            case R.id.store_layout:
            case R.id.office_layout:
                Toast.makeText(getContext(), "미구현 상태입니다", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}