package com.example.doantotnghiep.Fragment.MusicVideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Adapter.SachAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Sach;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Fragment_Sach extends Fragment {
    private RecyclerView recyclerView;
    private SachAdapter sachAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        recyclerView = view.findViewById(R.id.recyclerSach);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Sach>> callback = dataservice.GetSach();
        callback.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sach>> call, @NonNull Response<List<Sach>> response) {
                ArrayList<Sach> SachArrayList = (ArrayList<Sach>) response.body();
                sachAdapter = new SachAdapter(getActivity(), SachArrayList);
                RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(LayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(sachAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Sach>> call, @NonNull Throwable t) {

            }
        });

    }
}

