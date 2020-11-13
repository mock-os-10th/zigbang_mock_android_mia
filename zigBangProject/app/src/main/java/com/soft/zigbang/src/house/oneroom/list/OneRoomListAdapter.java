package com.soft.zigbang.src.house.oneroom.list;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.soft.zigbang.R;
import com.soft.zigbang.src.house.apart.ApartMapActivity;
import com.soft.zigbang.src.house.oneroom.models.OneRoomResponse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OneRoomListAdapter extends RecyclerView.Adapter<OneRoomListAdapter.OneRoomHolder> {

    ArrayList<OneRoomResponse.Result> oneRoomList;

    @NonNull
    @Override
    public OneRoomListAdapter.OneRoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_room, null);
        OneRoomHolder holder = new OneRoomHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OneRoomListAdapter.OneRoomHolder holder, int position) {
        OneRoomResponse.Result oneRoom = oneRoomList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(oneRoom.getImage()))
                .into(holder.oneRoomIvImage);
        holder.oneRoomTvPrice.setText("월세 " + oneRoom.getCharter());
        holder.oneRoomTvInfo.setText(oneRoom.getAcreage() + "평·" + oneRoom.getFloor() + "층");
        holder.oneRoomAddress.setText(oneRoom.getGu() + " " + oneRoom.getDong());
        holder.oneRoomContent.setText(oneRoom.getComment());
    }

    @Override
    public int getItemCount() {
        return oneRoomList.size();
    }

    public class OneRoomHolder extends RecyclerView.ViewHolder {
        ImageView oneRoomIvImage;
        TextView oneRoomTvPrice;
        TextView oneRoomTvInfo;
        TextView oneRoomAddress;
        TextView oneRoomContent;
        public OneRoomHolder(@NonNull View itemView) {
            super(itemView);

            oneRoomIvImage = itemView.findViewById(R.id.one_room_item_image);
            oneRoomTvPrice = itemView.findViewById(R.id.one_room_item_price);
            oneRoomTvInfo = itemView.findViewById(R.id.one_room_item_info);
            oneRoomAddress = itemView.findViewById(R.id.one_room_item_address);
            oneRoomContent = itemView.findViewById(R.id.one_room_item_content);
        }
    }

    public void setOneRoomList(ArrayList<OneRoomResponse.Result> oneRoomList) {

        this.oneRoomList = oneRoomList;
    }
}
