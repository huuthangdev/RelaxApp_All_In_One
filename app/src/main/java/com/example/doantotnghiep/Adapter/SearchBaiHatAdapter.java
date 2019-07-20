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

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    Context context;
    private ArrayList<Baihat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dong_search_baihat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangbaihat.get(i);
        viewHolder.txtTenbaihat.setText(baihat.getTenbaihat());
        viewHolder.txtCasi.setText(baihat.getCasi());
        Picasso.get().load(baihat.getHinhbaihat()).into(viewHolder.imgBaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenbaihat, txtCasi;
        ImageView imgBaihat;

        ViewHolder(View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.txt_search_tenbaihat);
            txtCasi = itemView.findViewById(R.id.txt_search_tencasi);
            imgBaihat = itemView.findViewById(R.id.img_search);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_Play_Nhac.class);
                intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}