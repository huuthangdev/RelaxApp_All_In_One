package com.example.doantotnghiep.Activity.ConSoBiAn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.R;


public class SecretNum_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button start, end;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consobian_screen);
        start = findViewById(R.id.buttonstart);
        end = findViewById(R.id.buttonend);
        start.setOnClickListener(view -> {
            Intent intent = new Intent(SecretNum_Screen.this, SecretNumActivity.class);
            startActivity(intent);
            finish();
        });

        end.setOnClickListener(view -> finish());
    }
}