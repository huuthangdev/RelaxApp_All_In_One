package com.example.doantotnghiep.Activity.TicTacToe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.R;


public class TicTac_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button start, start2, end;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_screen);
        start = findViewById(R.id.buttonstart);
        start2 = findViewById(R.id.buttonstart2);
        end = findViewById(R.id.buttonend);
        start.setOnClickListener(view -> {
            Intent intent = new Intent(TicTac_Screen.this, TicTacActivity.class);
            startActivity(intent);
            finish();
        });
        start2.setOnClickListener(view -> {
            Intent intent = new Intent(TicTac_Screen.this, TicTacPVP.class);
            startActivity(intent);
            finish();
        });
        end.setOnClickListener(view -> finish());
    }
}