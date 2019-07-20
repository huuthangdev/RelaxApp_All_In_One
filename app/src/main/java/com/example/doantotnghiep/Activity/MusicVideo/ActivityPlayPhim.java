package com.example.doantotnghiep.Activity.MusicVideo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.Model.MusicVideo.Phim;
import com.example.doantotnghiep.R;

import java.util.ArrayList;

public class ActivityPlayPhim extends AppCompatActivity {
    public VideoView vidView;
    public ProgressBar progressBar;
    int position = 0;
    public static ArrayList<Phim> mangPhim = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_phim);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        setID();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mangPhim.size() > 0) {
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            vidView.stopPlayback();
            mangPhim.clear();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangPhim.clear();
        if (intent != null) {
            if (intent.hasExtra("phimhot")) {
                Phim phim = intent.getParcelableExtra("phimhot");
                mangPhim.add(phim);
            }
        }
    }


    private void setID() {
        vidView = findViewById(R.id.myVideo);
        progressBar = findViewById(R.id.progressbar);
        new playVideo().execute(mangPhim.get(0).getLinkPhim());
    }

    @SuppressLint("StaticFieldLeak")
    class playVideo extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String phim) {
            super.onPostExecute(phim);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            MediaController mediaController = new MediaController(ActivityPlayPhim.this);
            mediaController.setAnchorView(vidView);
            vidView.setMediaController(mediaController);
            Uri vidurl = Uri.parse(phim);
            vidView.setVideoURI(vidurl);
            progressBar.setVisibility(View.VISIBLE);
            vidView.start();
            vidView.setOnPreparedListener(mediaPlayer -> {
                vidView.seekTo(position);
                if (position == 0) {
                    vidView.start();
                    mediaPlayer.setOnVideoSizeChangedListener((mediaPlayer1, i, i1) -> {
                        progressBar.setVisibility(View.GONE);
                        mediaPlayer1.start();
                    });
                }
            });
        }
    }
}