package com.example.doantotnghiep;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button start, end;
    ImageView diaxoay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.buttonstart);
        end = findViewById(R.id.buttonend);
        RotateNow();
        start.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
            startActivity(intent);
            finish();
        });

        end.setOnClickListener(view -> finish());
    }

    public void RotateNow() {
        diaxoay = findViewById(R.id.img_diaxoay);
        RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        diaxoay.startAnimation(rotate);
    }

}