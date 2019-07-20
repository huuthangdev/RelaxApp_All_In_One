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

public class DanhsachTruyenAdapter extends RecyclerView.Adapter<DanhsachTruyenAdapter.ViewHolder> {
    Context context;
    private ArrayList<Truyen> truyenArrayList;

    public DanhsachTruyenAdapter(Context context, ArrayList<Truyen> truyenArrayList) {
        this.context = context;
        this.truyenArrayList = truyenArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_truyen, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Truyen truyen = truyenArrayList.get(i);
        Picasso.get().load(truyen.getHinhTruyen()).into(viewHolder.imgTruyen);
        viewHolder.txtTruyen.setText(truyen.getTenTruyen());
        viewHolder.txtTacgia.setText(truyen.getTenTacGia());
    }

    @Override
    public int getItemCount() {
        return truyenArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTruyen;
        TextView txtTruyen, txtTacgia;

        ViewHolder(View itemView) {
            super(itemView);

            imgTruyen = itemView.findViewById(R.id.imgDanhsachTruyen);
            txtTruyen = itemView.findViewById(R.id.txtDanhSachTruyen);
            txtTacgia = itemView.findViewById(R.id.txtTacgia);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_List_BaiHat.class);
                intent.putExtra("truyen", truyenArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
