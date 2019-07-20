package com.example.doantotnghiep.Activity.MusicVideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doantotnghiep.Fragment.MusicVideo.Fragment_Timkiem;
import com.example.doantotnghiep.LoadingActivity;
import com.example.doantotnghiep.R;

import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    Fragment fragment = new Fragment_Timkiem();
                    loadFragment(fragment);
                });
            }
        }, 5);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(SearchActivity.this, LoadingActivity.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

