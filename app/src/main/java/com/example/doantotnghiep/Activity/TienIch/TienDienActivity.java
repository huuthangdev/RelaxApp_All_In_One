package com.example.doantotnghiep.Activity.TienIch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doantotnghiep.R;

import java.text.DecimalFormat;

public class TienDienActivity extends AppCompatActivity implements View.OnClickListener {
    Button cal, clear;
    EditText nhaptiendien;
    TextView gia1, gia2, gia3, gia4, gia5, gia6, tiendien, thuegtgt, tongtien;
    int hs1 = 50 * 1678, hs2 = 50 * 1734, hs3 = 100 * 2014, hs4 = 100 * 2536, hs5 = 100 * 2834;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_dien);
        setId();
    }

    private void setId() {
        nhaptiendien = findViewById(R.id.nhaptiendien);
        gia1 = findViewById(R.id.gia1);
        gia2 = findViewById(R.id.gia2);
        gia3 = findViewById(R.id.gia3);
        gia4 = findViewById(R.id.gia4);
        gia5 = findViewById(R.id.gia5);
        gia6 = findViewById(R.id.gia6);
        cal = findViewById(R.id.buttonrun);
        clear = findViewById(R.id.buttonclear);
        cal.setOnClickListener(this);
        clear.setOnClickListener(this);
        tiendien = findViewById(R.id.tiendien);
        thuegtgt = findViewById(R.id.thuegtgt);
        tongtien = findViewById(R.id.tongtien);
        nhaptiendien.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                TinhTien();
                return true;
            }
            return false;
        });
    }


    @SuppressLint("SetTextI18n")
    private void TinhTien() {
        int a = Integer.parseInt(nhaptiendien.getText().toString());
        DecimalFormat hienthi = new DecimalFormat("0,000");
        if (a <= 50) {
            int b = a * 1678;
            gia1.setText("50 kWh * 1.678 VNĐ = " + hienthi.format(b) + " VNĐ");
            tiendien.setText(hienthi.format(b) + " VNĐ");
            thuegtgt.setText(hienthi.format(b * 0.1) + " VNĐ");
            tongtien.setText(hienthi.format(b * 1.1) + " VNĐ");
        } else if (a <= 100) {
            gia1.setText("50 kWh * 1.678 VNĐ = " + hienthi.format(hs1) + " VNĐ");
            int b = (a - 50) * 1734;
            gia2.setText("50 kWh * 1.734 VNĐ = " + hienthi.format(b) + " VNĐ");
            tiendien.setText(hienthi.format(hs1 + b) + " VNĐ");
            thuegtgt.setText(hienthi.format((hs1 + b) * 0.1) + " VNĐ");
            tongtien.setText(hienthi.format((hs1 + b) * 1.1) + " VNĐ");

        } else if (a <= 200) {
            gia1.setText("50 kWh * 1.678 VNĐ = " + hienthi.format(hs1) + " VNĐ");
            gia2.setText("50 kWh * 1.734 VNĐ = " + hienthi.format(hs2) + " VNĐ");
            int b = (a - 100) * 2014;
            gia3.setText("100 kWh * 2.014 VNĐ = " + hienthi.format(b) + " VNĐ");
            tiendien.setText(hienthi.format(hs1 + hs2 + b) + " VNĐ");
            thuegtgt.setText(hienthi.format((hs1 + hs2 + b) * 0.1) + " VNĐ");
            tongtien.setText(hienthi.format((hs1 + hs2 + b) * 1.1) + " VNĐ");

        } else if (a <= 300) {
            gia1.setText("50 kWh * 1.678 VNĐ = " + hienthi.format(hs1) + " VNĐ");
            gia2.setText("50 kWh * 1.734 VNĐ = " + hienthi.format(hs2) + " VNĐ");
            gia3.setText("100 kWh * 2.014 VNĐ = " + hienthi.format(hs3) + " VNĐ");
            int b = (a - 200) * 2536;
            gia4.setText("100 kWh * 2.536 VNĐ = " + hienthi.format(b) + " VNĐ");
            tiendien.setText(hienthi.format(hs1 + hs2 + hs3 + b) + " VNĐ");
            thuegtgt.setText(hienthi.format((hs1 + hs2 + hs3 + b) * 0.1) + " VNĐ");
            tongtien.setText(hienthi.format((hs1 + hs2 + hs3 + b) * 1.1) + " VNĐ");

        } else if (a <= 400) {
            gia1.setText("50 kWh * 1.678 VNĐ = " + hienthi.format(hs1) + " VNĐ");
            gia2.setText("50 kWh * 1.734 VNĐ = " + hienthi.format(hs2) + " VNĐ");
            gia3.setText("100 kWh * 2.014 VNĐ = " + hienthi.format(hs3) + " VNĐ");
            gia4.setText("100 kWh * 2.536 VNĐ = " + hienthi.format(hs4) + " VNĐ");
            int b = (a - 300) * 2834;
            gia5.setText("100 kWh * 2.834 VNĐ = " + hienthi.format(b) + " VNĐ");
            tiendien.setText(hienthi.format(hs1 + hs2 + hs3 + hs4 + b) + " VNĐ");
            thuegtgt.setText(hienthi.format((hs1 + hs2 + hs3 + hs4 + b) * 0.1) + " VNĐ");
            tongtien.setText(hienthi.format((hs1 + hs2 + hs3 + hs4 + b) * 1.1) + " VNĐ");

        } else {
            gia1.setText("50 kWh * 1.678 VNĐ = " + hienthi.format(hs1) + " VNĐ");
            gia2.setText("50 kWh * 1.734 VNĐ = " + hienthi.format(hs2) + " VNĐ");
            gia3.setText("100 kWh * 2.014 VNĐ = " + hienthi.format(hs3) + " VNĐ");
            gia4.setText("100 kWh * 2.536 VNĐ = " + hienthi.format(hs4) + " VNĐ");
            gia5.setText("100 kWh * 2.834 VNĐ = " + hienthi.format(hs5) + " VNĐ");
            int b = (a - 400) * 2927;
            gia6.setText((a - 400) + " kWh * 2.927 VNĐ = " + hienthi.format(b) + " VNĐ");
            tiendien.setText(hienthi.format(hs1 + hs2 + hs3 + hs4 + hs5 + b) + " VNĐ");
            thuegtgt.setText(hienthi.format((hs1 + hs2 + hs3 + hs4 + hs5 + b) * 0.1) + " VNĐ");
            tongtien.setText(hienthi.format((hs1 + hs2 + hs3 + hs4 + hs5 + b) * 1.1) + " VNĐ");

        }
    }

    private void Refresh() {
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonrun:
                TinhTien();
                break;
            case R.id.buttonclear:
                Refresh();
                break;
            default:
                break;
        }
    }
}

