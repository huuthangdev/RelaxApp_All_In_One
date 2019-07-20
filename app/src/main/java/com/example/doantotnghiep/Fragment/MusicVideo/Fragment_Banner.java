package com.example.doantotnghiep.Fragment.MusicVideo;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.doantotnghiep.Adapter.BannerAdapter;
import com.example.doantotnghiep.Model.MusicVideo.Quangcao;
import com.example.doantotnghiep.R;
import com.example.doantotnghiep.Service.APIService;
import com.example.doantotnghiep.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {

    private View view;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;
    private Runnable runnable;
    private Handler handler;
    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viewpaper);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(@NonNull Call<List<Quangcao>> call, @NonNull Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(), banners);
                viewPager.setAdapter(bannerAdapter);
                viewPager.setOffscreenPageLimit(3);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = () -> {
                    currentItem = viewPager.getCurrentItem();
                    currentItem++;
                    if (currentItem >= Objects.requireNonNull(viewPager.getAdapter()).getCount()){
                        try {
                            currentItem = 0;
                        } catch (Exception ignored) {
                        }
                    }
                    viewPager.setCurrentItem(currentItem, true);
                    handler.postDelayed(runnable, 7000);
                };
                handler.postDelayed(runnable, 7000);
            }

            @Override
            public void onFailure(@NonNull Call<List<Quangcao>> call, @NonNull Throwable t) {

            }
        });
    }
}
