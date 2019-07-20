package com.example.doantotnghiep.Fragment.MusicVideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantotnghiep.Activity.MusicVideo.Activity_List_Truyen;
import com.example.doantotnghiep.Adapter.TruyenAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Truyen;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Truyen extends Fragment {
    private RecyclerView recyclerView;
    private TruyenAdapter truyenAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_truyen, container, false);
        recyclerView = view.findViewById(R.id.recyclerTruyen);
        TextView txtXemthem = view.findViewById(R.id.txtXemthemTruyen);
        txtXemthem.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), Activity_List_Truyen.class);
            startActivity(intent);
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Truyen>> callback = dataservice.GetTruyenHot();
        callback.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                ArrayList<Truyen> TruyenArrayList = (ArrayList<Truyen>) response.body();

                truyenAdapter = new TruyenAdapter(getActivity(), TruyenArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(truyenAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {

            }
        });
    }
}
