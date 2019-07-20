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

import com.example.doantotnghiep.Activity.MusicVideo.SachActivity;
import com.example.doantotnghiep.Model.MusicVideo.Sach;
import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {

    Context context;
    private ArrayList<Sach> SachArrayList;

    public SachAdapter(Context context, ArrayList<Sach> SachArrayList) {
        this.context = context;
        this.SachArrayList = SachArrayList;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_sach, viewGroup, false);
        return new SachAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder viewHolder, int i) {
        Sach Sach = SachArrayList.get(i);
        viewHolder.txtTen.setText(Sach.getTenSach());
        Picasso.get().load(Sach.getHinhSach()).into(viewHolder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return SachArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen;
        ImageView imgHinh;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenSach);
            imgHinh = itemView.findViewById(R.id.imgSach);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, SachActivity.class);
                intent.putExtra("Sach", SachArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}


