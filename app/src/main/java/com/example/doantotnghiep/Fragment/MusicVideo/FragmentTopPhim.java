package com.example.doantotnghiep.Fragment.MusicVideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.TopPhimAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Phim;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTopPhim extends Fragment {
    private RecyclerView recyclerView;
    private TopPhimAdapter phimHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_phim, container, false);
        recyclerView = view.findViewById(R.id.recyclerPhimHot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Phim>> callback = dataservice.GetPhimHot();
        callback.enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(@NonNull Call<List<Phim>> call, @NonNull Response<List<Phim>> response) {
                ArrayList<Phim> phimArrayList = (ArrayList<Phim>) response.body();
                phimHotAdapter = new TopPhimAdapter(getActivity(), phimArrayList);
                RecyclerView.LayoutManager LayoutManager = new GridLayoutManager(getActivity(), 2);
                recyclerView.setLayoutManager(LayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(phimHotAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Phim>> call, @NonNull Throwable t) {

            }
        });

    }
}

