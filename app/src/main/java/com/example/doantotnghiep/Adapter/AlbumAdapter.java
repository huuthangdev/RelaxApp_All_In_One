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
import com.example.doantotnghiep.Model.MusicVideo.Album;
import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = mangAlbum.get(i);
        viewHolder.txtTenCasiAlbum.setText(album.getTencasiAlbum());
        viewHolder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.get().load(album.getHinhanhAlbum()).into(viewHolder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhAlbum;
        TextView txtTenAlbum, txtTenCasiAlbum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinhAlbum = itemView.findViewById(R.id.imgAlbum);
            txtTenAlbum = itemView.findViewById(R.id.txtTenAlbum);
            txtTenCasiAlbum = itemView.findViewById(R.id.txtTenCaSiAlbum);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Activity_List_BaiHat.class);
                intent.putExtra("album", mangAlbum.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
