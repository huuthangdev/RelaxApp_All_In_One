package com.example.doantotnghiep.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Activity.MusicVideo.Activity_Play_Nhac;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.R;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    private ArrayList<Baihat> mangBaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangBaihat) {
        this.context = context;
        this.mangBaihat = mangBaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Baihat baihat = mangBaihat.get(i);
        holder.txtCasi.setText(baihat.getCasi());
        holder.txtBaihat.setText(baihat.getTenbaihat());
        holder.txtIndex.setText(i + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangBaihat.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtCasi, txtBaihat;
        ViewHolder(View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtDanhSachIndex);
            txtCasi = itemView.findViewById(R.id.txtTenCaSi);
            txtBaihat = itemView.findViewById(R.id.txtTenBaihat);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_Play_Nhac.class);
                intent.putExtra("cakhuc", mangBaihat.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
