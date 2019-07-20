package com.example.doantotnghiep.Activity.CoTuong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.R;


public class CoTuong_Screen extends AppCompatActivity {
    Button start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_tuong_screen);
        start = findViewById(R.id.buttonstart);
        end = findViewById(R.id.buttonend);
        start.setOnClickListener(view -> {
            Intent intent = new Intent(CoTuong_Screen.this, CoTuongActivity.class);
            startActivity(intent);
            finish();
        });

        end.setOnClickListener(view -> finish());
    }
}

