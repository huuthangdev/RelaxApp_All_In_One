package com.example.doantotnghiep.Activity.MusicVideo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.DanhsachbaihatAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Album;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.Model.MusicVideo.Quangcao;
import com.example.doantotnghiep.Model.MusicVideo.Truyen;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_List_BaiHat extends AppCompatActivity {
    Quangcao quangcao;
    Album album;
    Truyen truyen;
    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangBaihat;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        setId();

        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }

        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }

        if (truyen != null && !truyen.getTenTruyen().equals("")) {
            setValueInView(truyen.getTenTruyen(), truyen.getHinhTruyen());
            GetDataTruyen(truyen.getIdTruyen());
        }
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Collapsed);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Expanded);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinh).into(imgdanhsachcakhuc);
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                recyclerViewDanhSachBaiHat.setHasFixedSize(true);
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void GetDataTruyen(String idTruyen) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoTruyen(idTruyen);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void setId() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collasingToolbar);
        toolbar = findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerDanhSachBaihat);
        imgdanhsachcakhuc = findViewById(R.id.imgDanhsachBaihat);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
            if (intent.hasExtra("truyen")) {
                truyen = (Truyen) intent.getSerializableExtra("truyen");
            }
        }
    }
}
