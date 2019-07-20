package com.example.doantotnghiep.Activity.MusicVideo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doantotnghiep.Fragment.MusicVideo.Fragment_Dia_nhac;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;


public class Activity_Play_Nhac extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTime, txtTotalTime;
    SeekBar seekBar;
    ImageButton btnPlay;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean next = false;
    Fragment_Dia_nhac fragment_dia_nhac;
    public static ArrayList<Baihat> mangBaiHat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        setSpeedOptions();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        setID();
        eventClick();
        loadFragment(fragment_dia_nhac);
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mangBaiHat.size() > 0) {
                    fragment_dia_nhac.Playnhac(mangBaiHat.get(0).getHinhbaihat());
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);
        btnPlay.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlay.setImageResource(R.drawable.iconplay);
                fragment_dia_nhac.stopDisc();
            } else {
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.iconpause);
                fragment_dia_nhac.startDisc();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baihat);
            }
        }
    }

    private String[] getSpeedStrings() {
        return new String[]{"1.0", "0.25", "0.5", "0.75", "1.25", "1.5", "2.0"};
    }

    private void setSpeedOptions() {
        final Spinner speedOptions = findViewById(R.id.speedOptions);
        String[] speeds = getSpeedStrings();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, speeds);
        speedOptions.setAdapter(arrayAdapter);
        speedOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null) {
                    float selectedSpeed = Float.parseFloat(
                            speedOptions.getItemAtPosition(i).toString());

                    changeplayerSpeed(selectedSpeed);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void changeplayerSpeed(float speed) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
            } else {
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                mediaPlayer.pause();
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            mediaPlayer.stop();
            mangBaiHat.clear();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setID() {
        toolbar = findViewById(R.id.toolbar_play_nhac);
        seekBar = findViewById(R.id.seekbar_song);
        txtTime = findViewById(R.id.txt_time_song);
        txtTotalTime = findViewById(R.id.txt_total_time_song);
        btnPlay = findViewById(R.id.btn_play);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
            mediaPlayer.stop();
            mangBaiHat.clear();
        });
        toolbar.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_nhac();
        if (mangBaiHat.size() > 0) {
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenbaihat());
            new playMp3().execute(mangBaiHat.get(0).getLinkbaihat());
            btnPlay.setImageResource(R.drawable.iconpause);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class playMp3 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            updateTime();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @SuppressLint("SimpleDateFormat")
    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    @SuppressLint("SimpleDateFormat")
    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                        repeat = true;
                        next = true;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }, 300);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next) {
                    if (position < (mangBaiHat.size())) {
                        btnPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat) {
                            if (position == 0) {
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (position > (mangBaiHat.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        Objects.requireNonNull(getSupportActionBar()).setTitle(mangBaiHat.get(position).getTenbaihat());
                    }
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
