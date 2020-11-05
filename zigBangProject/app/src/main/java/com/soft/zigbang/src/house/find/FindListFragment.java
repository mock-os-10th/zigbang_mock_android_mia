package com.soft.zigbang.src.house.find;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.models.FindResponse;

import java.util.List;


public class FindListFragment extends Fragment {


    public static FindListFragment newInstance(List<FindResponse.Result> findList) {
        FindListFragment fragment = new FindListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_list, container, false);
        TextView findListTvCancel = view.findViewById(R.id.find_list_tv_cancel);
        findListTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FindMapActivity)getActivity()).removeFragment();
            }
        });
        return view;
    }

}