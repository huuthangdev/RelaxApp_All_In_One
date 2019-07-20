package com.example.doantotnghiep.Activity.MusicVideo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.DanhsachTruyenAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Truyen;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_Truyen extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    DanhsachTruyenAdapter danhsachTruyenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_truyen);
        setID();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Truyen>> callback = dataservice.GetTruyen();
        callback.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                ArrayList<Truyen> mangtruyen = (ArrayList<Truyen>) response.body();
                danhsachTruyenAdapter = new DanhsachTruyenAdapter(Activity_List_Truyen.this, mangtruyen);
                recyclerView.setLayoutManager(new GridLayoutManager(Activity_List_Truyen.this, 2));
                recyclerView.setAdapter(danhsachTruyenAdapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {
            }
        });
    }

    private void setID() {
        recyclerView = findViewById(R.id.recycler_list_Truyen);
        toolbar = findViewById(R.id.toolbar_list_truyen);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các truyện");
        toolbar.setNavigationOnClickListener(view -> finish());
    }
}
