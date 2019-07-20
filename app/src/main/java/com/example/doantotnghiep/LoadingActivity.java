package com.example.doantotnghiep;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.doantotnghiep.Activity.MusicVideo.SearchActivity;
import com.example.doantotnghiep.Adapter.MainViewPaperAdapter;
import com.example.doantotnghiep.Fragment.Fragment_Call;
import com.example.doantotnghiep.Fragment.Fragment_Game;
import com.example.doantotnghiep.Fragment.Fragment_Tool;
import com.example.doantotnghiep.Fragment.MusicVideo.FragmentTopPhim;
import com.example.doantotnghiep.Fragment.MusicVideo.Fragment_Sach;
import com.example.doantotnghiep.Fragment.MusicVideo.Fragment_Trang_Chu;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class LoadingActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setId();
        init();
    }

    private void init() {
        MainViewPaperAdapter mainViewPaperAdapter = new MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPaperAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chủ");
        mainViewPaperAdapter.addFragment(new FragmentTopPhim(), "Video");
        mainViewPaperAdapter.addFragment(new Fragment_Sach(), "Sách");
        mainViewPaperAdapter.addFragment(new Fragment_Game(), "Game");
        mainViewPaperAdapter.addFragment(new Fragment_Tool(), "Công cụ");
        mainViewPaperAdapter.addFragment(new Fragment_Call(), "Thông tin");
        viewPager.setAdapter(mainViewPaperAdapter);
        viewPager.setOffscreenPageLimit(6);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.icontrangchu);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.icon_video);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_book);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.icon_game);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(R.drawable.icon_tool);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(R.drawable.iconcall);
        toolbar.setLogo(R.drawable.iconapp);
    }

    private void setId() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPaper);
        toolbar = findViewById(R.id.toolbarloading);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent i = new Intent(LoadingActivity.this, SearchActivity.class);
            startActivity(i);
            return true;
        }
        return false;
    }
}