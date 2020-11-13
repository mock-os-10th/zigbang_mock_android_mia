package com.soft.zigbang.src.house.find.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.models.FindResponse;

import java.util.ArrayList;

public class FindDetailSchoolAdapter extends RecyclerView.Adapter<FindDetailSchoolAdapter.SchoolViewHolder> {

    ArrayList<FindResponse.School> schools;

    public FindDetailSchoolAdapter(ArrayList<FindResponse.School> schools) {
        this.schools = schools;
    }

    @NonNull
    @Override
    public FindDetailSchoolAdapter.SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school, null);
        SchoolViewHolder holder = new SchoolViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindDetailSchoolAdapter.SchoolViewHolder holder, int position) {
        FindResponse.School school = schools.get(position);
        holder.itemSchoolName.setText(school.getName());
        holder.itemSchoolType.setText(getSchoolType(school.getIsCode(), school.getIsPublic()));
        holder.itemSchoolDistance.setText(school.getDistance() + "m");
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class SchoolViewHolder extends RecyclerView.ViewHolder {
        TextView itemSchoolName;
        TextView itemSchoolType;
        TextView itemSchoolDistance;

        public SchoolViewHolder(@NonNull View itemView) {
            super(itemView);

            itemSchoolName = itemView.findViewById(R.id.item_school_tv_name);
            itemSchoolType = itemView.findViewById(R.id.item_school_tv_type);
            itemSchoolDistance = itemView.findViewById(R.id.item_school_tv_distance);
        }
    }

    private String getSchoolType(String coedType, String publicType) {
        String type = "";
        switch (coedType) {
            case "Y":
                type += "공학";
                break;
            case "F":
                type += "남중";
                break;
            case "M":
                type += "여중";
                break;
        }
        type += "/";
        switch (publicType) {
            case "Y":
                type += "공립";
                break;
            case "N":
                type += "사립";
                break;
        }

        return type;
    }
}
