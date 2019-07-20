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

import com.example.doantotnghiep.Adapter.BaihatHotAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Baihathot extends Fragment {
    private RecyclerView recyclerViewBaihat;
    private BaihatHotAdapter baihatHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baihat_top_like, container, false);
        recyclerViewBaihat = view.findViewById(R.id.recyclerBaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baihatHotAdapter = new BaihatHotAdapter(getActivity(), baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewBaihat.setLayoutManager(linearLayoutManager);
                recyclerViewBaihat.setHasFixedSize(true);
                recyclerViewBaihat.setAdapter(baihatHotAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });

    }
}
