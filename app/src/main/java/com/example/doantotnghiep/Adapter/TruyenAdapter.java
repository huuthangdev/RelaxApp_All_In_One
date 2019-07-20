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

import com.example.doantotnghiep.Activity.MusicVideo.Activity_List_BaiHat;
import com.example.doantotnghiep.Model.MusicVideo.Truyen;
import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {

    Context context;
    private ArrayList<Truyen> mangTruyen;

    public TruyenAdapter(Context context, ArrayList<Truyen> mangTruyen) {
        this.context = context;
        this.mangTruyen = mangTruyen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_truyen, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Truyen truyen = mangTruyen.get(i);
        viewHolder.txtTenTacGia.setText(truyen.getTenTacGia());
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());
        Picasso.get().load(truyen.getHinhTruyen()).into(viewHolder.imgHinhTruyen);
    }

    @Override
    public int getItemCount() {
        return mangTruyen.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhTruyen;
        TextView txtTenTruyen, txtTenTacGia;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinhTruyen = itemView.findViewById(R.id.imgTruyen);
            txtTenTruyen = itemView.findViewById(R.id.txtTenTruyen);
            txtTenTacGia = itemView.findViewById(R.id.txtTenTacGia);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_List_BaiHat.class);
                intent.putExtra("truyen", mangTruyen.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}