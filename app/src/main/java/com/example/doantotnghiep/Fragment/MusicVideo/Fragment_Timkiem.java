package com.example.doantotnghiep.Fragment.MusicVideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.SearchBaiHatAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Timkiem extends Fragment {
    private RecyclerView recyclerView;
    private TextView txt_khong_du_lieu;
    private SearchBaiHatAdapter searchBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        recyclerView = view.findViewById(R.id.recycler_search);
        txt_khong_du_lieu = view.findViewById(R.id.txt_khongcodulieu);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_timkiem_baihat, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Gõ tên bài hát...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
              SearchTuKhoaBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaBaiHat(String query) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.SearchBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                if (mangbaihat != null) {
                    if (mangbaihat.size() > 0) {
                        searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), mangbaihat);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(searchBaiHatAdapter);
                        recyclerView.setHasFixedSize(true);
                        txt_khong_du_lieu.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        txt_khong_du_lieu.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }
}
