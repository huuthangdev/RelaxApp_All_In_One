package com.example.doantotnghiep.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.doantotnghiep.Activity.MusicVideo.Activity_List_BaiHat;
import com.example.doantotnghiep.Model.MusicVideo.Quangcao;
import com.example.doantotnghiep.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    Context context;
    private ArrayList<Quangcao> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imgbackground = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsong = view.findViewById(R.id.imageviewbanner);
        TextView txttitle = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtnoidung = view.findViewById(R.id.textviewnoidung);

        Picasso
                .get()
                .load(arrayListbanner.get(position).getHinhanh())
                .into(imgbackground);
        Picasso
                .get()
                .load(arrayListbanner.get(position).getHinhBaiHat())
                .into(imgsong);
        txttitle.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListbanner.get(position).getNoidung());

        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, Activity_List_BaiHat.class);
            intent.putExtra("banner", arrayListbanner.get(position));
            context.startActivity(intent);
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
