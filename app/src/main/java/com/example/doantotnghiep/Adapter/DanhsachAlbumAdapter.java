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
import com.example.doantotnghiep.Model.MusicVideo.Album;import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachAlbumAdapter extends RecyclerView.Adapter<DanhsachAlbumAdapter.ViewHolder>{

    Context context;
    private ArrayList<Album> albumArrayList;

    public DanhsachAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        Picasso.get().load(album.getHinhanhAlbum()).into(viewHolder.imgAlbum);
        viewHolder.txtAlbum.setText(album.getTenAlbum());
        viewHolder.txtCasi.setText(album.getTencasiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlbum;
        TextView txtAlbum, txtCasi;
        ViewHolder(View itemView) {
            super(itemView);

            imgAlbum = itemView.findViewById(R.id.imgDanhsachAlbum);
            txtAlbum = itemView.findViewById(R.id.txtDanhSachAlbum);
            txtCasi  = itemView.findViewById(R.id.txtCasiAlbum);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_List_BaiHat.class);
                intent.putExtra("album", albumArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
