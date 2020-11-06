package com.soft.zigbang.src.house.find;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.interfaces.FindOnItemClickListener;
import com.soft.zigbang.src.house.find.models.FindResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FindListFragment extends Fragment implements FindOnItemClickListener {

    private ArrayList<FindResponse.Result> mFindList;
    private FindMapActivity mParentActivity;

    public static FindListFragment newInstance(List<FindResponse.Result> findList) {
        FindListFragment fragment = new FindListFragment();
        Bundle args = new Bundle();
        args.putSerializable("findList", (Serializable) findList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFindList = (ArrayList<FindResponse.Result>) getArguments().getSerializable("findList");
            mFindList.toString();
        }

        mParentActivity = (FindMapActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_list, container, false);

        RecyclerView findListRv = view.findViewById(R.id.find_list_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        findListRv.setLayoutManager(manager);

        FindListAdapter adapter = new FindListAdapter(mFindList);
        // 클릭리스너
        adapter.setFindOnItemClickListener(this);
        findListRv.setAdapter(adapter);

        TextView findListTvCancel = view.findViewById(R.id.find_list_tv_cancel);
        EditText findListEditSearch = view.findViewById(R.id.find_list_edit_search);
        findListEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findListTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParentActivity.removeFragment();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(View v, int pos) {
        long index = mFindList.get(pos).getApartIndex();

    }
}