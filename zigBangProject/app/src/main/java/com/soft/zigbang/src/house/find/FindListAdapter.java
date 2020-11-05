package com.soft.zigbang.src.house.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.zigbang.R;

import java.util.ArrayList;

public class FindListAdapter extends RecyclerView.Adapter<FindListAdapter.FindHolder> implements Filterable {

    ArrayList<String> bikeList;
    ArrayList<String> filteredList;

//    public FindListAdapter(ArrayList<BikeInfo> bikeList) {
//        this.bikeList = bikeList;
//        this.filteredList = bikeList;
//    }

    @NonNull
    @Override
    public FindListAdapter.FindHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, null);
        FindHolder holder = new FindHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindListAdapter.FindHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class FindHolder extends RecyclerView.ViewHolder {

        public FindHolder(@NonNull View itemView) {
            super(itemView);


        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
//                    filteredList = bikeList;
                } else {
//                    ArrayList<BikeInfo> filteringList = new ArrayList<>();
//                    for(BikeInfo info : bikeList) {
//                        if(info.getStationName().toLowerCase().contains(charString.toLowerCase())) {
//                            filteringList.add(info);
//                        }
//                    }
//                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
//                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
//                filteredList = (ArrayList<BikeInfo>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
