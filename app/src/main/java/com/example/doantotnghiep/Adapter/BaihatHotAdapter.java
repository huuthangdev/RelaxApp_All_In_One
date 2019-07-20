package com.example.doantotnghiep.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Activity.MusicVideo.Activity_Play_Nhac;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaihatHotAdapter extends RecyclerView.Adapter<BaihatHotAdapter.ViewHolder> {

    Context context;
    private ArrayList<Baihat> baihatArrayList;

    public BaihatHotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = baihatArrayList.get(i);
        viewHolder.txtCasi.setText(baihat.getCasi());
        viewHolder.txtTen.setText(baihat.getTenbaihat());
        Picasso.get().load(baihat.getHinhbaihat()).into(viewHolder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtCasi;
        ImageView imgHinh;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenBaihatHot);
            txtCasi = itemView.findViewById(R.id.txtCasiBaihatHot);
            imgHinh = itemView.findViewById(R.id.imgBaihathot);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_Play_Nhac.class);
                intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
