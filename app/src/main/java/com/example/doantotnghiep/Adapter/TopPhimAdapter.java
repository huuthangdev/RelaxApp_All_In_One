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

import com.example.doantotnghiep.Activity.MusicVideo.ActivityPlayPhim;
import com.example.doantotnghiep.Model.MusicVideo.Phim;
import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopPhimAdapter extends RecyclerView.Adapter<TopPhimAdapter.ViewHolder> {

    Context context;
    private ArrayList<Phim> phimArrayList;

    public TopPhimAdapter(Context context, ArrayList<Phim> phimArrayList) {
        this.context = context;
        this.phimArrayList = phimArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_phimhot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Phim phim = phimArrayList.get(i);
        viewHolder.txtTen.setText(phim.getTenPhim());
        Picasso.get().load(phim.getHinhPhim()).into(viewHolder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return phimArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen;
        ImageView imgHinh;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.TenPhimHot);
            imgHinh = itemView.findViewById(R.id.imgPhimHot);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ActivityPlayPhim.class);
                intent.putExtra("phimhot", phimArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}

