package com.soft.zigbang.src.house.find;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.zigbang.R;
import com.soft.zigbang.src.house.find.interfaces.FindOnItemClickListener;
import com.soft.zigbang.src.house.find.models.FindResponse;

import java.util.ArrayList;

public class FindListAdapter extends RecyclerView.Adapter<FindListAdapter.FindHolder> implements Filterable {

    ArrayList<FindResponse.Result> findList;
    ArrayList<FindResponse.Result> filteredList;
    FindOnItemClickListener listener;

    public FindListAdapter(ArrayList<FindResponse.Result> findList) {
        this.findList = findList;
        this.filteredList = findList;
    }

    public void setFindOnItemClickListener(FindOnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FindListAdapter.FindHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, null);
        FindHolder holder = new FindHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindListAdapter.FindHolder holder, int position) {
        FindResponse.Result result = filteredList.get(position);
        holder.itemFindTvName.setText(result.getName());
        holder.itemFindTvAddress.setText(result.getAddress());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class FindHolder extends RecyclerView.ViewHolder {

        TextView itemFindTvName;
        TextView itemFindTvAddress;

        public FindHolder(@NonNull View itemView) {
            super(itemView);

            itemFindTvName = itemView.findViewById(R.id.item_find_tv_name);
            itemFindTvAddress = itemView.findViewById(R.id.item_find_tv_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filteredList = findList;
                } else {
                    ArrayList<FindResponse.Result> filteringList = new ArrayList<>();
                    for (FindResponse.Result find : findList) {
                        if (find.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(find);
                        }
                    }
                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<FindResponse.Result>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
