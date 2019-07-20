package com.example.doantotnghiep.Controller_AiLaTrieuPhu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.R;

public class BatDauGame extends AppCompatActivity {
    MediaPlayer mp;
    ImageView logo;
    Switch voice;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_start_ailatrieuphu);
        voice = findViewById(R.id.openvoice);
        mp = MediaPlayer.create(this, R.raw.main_monitor);
        RotateNow();
        sharedPreferences = getSharedPreferences("sound", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = sharedPreferences.edit();

        voice.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                edit.putString("sound", "ok");
                mp.start();
                edit.apply();
            } else {
                edit.putString("sound", "no");
                mp.stop();
                edit.commit();
            }
        });
        if (sharedPreferences.getString("sound", "").equals("ok")) {
            mp.start();
            voice.setChecked(true);
        } else voice.setChecked(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mp.isPlaying()) {
            mp.stop();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
    }

    public void newGame(View view) {
        mp.stop();
        startActivity(new Intent(BatDauGame.this, GameScreenActivity.class));
    }

    public void RotateNow() {
        logo = findViewById(R.id.milyoner_logo);
        RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(15000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        logo.startAnimation(rotate);
    }


    public void quit(View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
