package com.example.doantotnghiep.Activity.MusicVideo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.Model.MusicVideo.Sach;
import com.example.doantotnghiep.R;

import java.util.ArrayList;

public class SachActivity extends AppCompatActivity {
    public static ArrayList<Sach> mangSach = new ArrayList<>();
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
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
                if (mangSach.size() > 0) {
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
            mangSach.clear();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangSach.clear();
        if (intent != null) {
            if (intent.hasExtra("Sach")) {
                Sach sach = intent.getParcelableExtra("Sach");
                mangSach.add(sach);
            }
        }
    }

    private void setID() {
        webview = findViewById(R.id.webview);
        new playebook().execute(mangSach.get(0).getLinkSach());
    }

    @SuppressLint("StaticFieldLeak")
    class playebook extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onPostExecute(String sach) {
            super.onPostExecute(sach);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://docs.google.com/gview?embedded=true&url=" + sach);
        }
    }
}
