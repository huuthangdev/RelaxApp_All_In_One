package com.example.doantotnghiep.Activity.MusicVideo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.DanhsachAlbumAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Album;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_Album extends AppCompatActivity {

    RecyclerView recyclerView;
    DanhsachAlbumAdapter danhsachAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        setID();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                danhsachAlbumAdapter = new DanhsachAlbumAdapter(Activity_List_Album.this, mangalbum);
                recyclerView.setLayoutManager(new GridLayoutManager(Activity_List_Album.this, 2));
                recyclerView.setAdapter(danhsachAlbumAdapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
            }
        });
    }

    private void setID() {
        recyclerView = findViewById(R.id.recycler_list_Album);
        Toolbar toolbar = findViewById(R.id.toolbar_list_album);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các Album");
        toolbar.setNavigationOnClickListener(view -> finish());
    }
}
