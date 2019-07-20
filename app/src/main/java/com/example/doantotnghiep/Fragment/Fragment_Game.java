package com.example.doantotnghiep.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doantotnghiep.Activity.CoTuong.CoTuong_Screen;
import com.example.doantotnghiep.Activity.ConSoBiAn.SecretNum_Screen;
import com.example.doantotnghiep.Activity.TicTacToe.TicTac_Screen;
import com.example.doantotnghiep.Controller_AiLaTrieuPhu.BatDauGame;
import com.example.doantotnghiep.R;


public class Fragment_Game extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trochoi, container, false);
        Button layoutcotuong = view.findViewById(R.id.buttoncotuong);
        Button layoutcaro = view.findViewById(R.id.buttoncaro);
        Button layoutconso = view.findViewById(R.id.buttonconsobian);
        Button layoutailatrieuphu = view.findViewById(R.id.buttonailatrieuphu);
        layoutcotuong.setOnClickListener(this);
        layoutcaro.setOnClickListener(this);
        layoutconso.setOnClickListener(this);
        layoutailatrieuphu.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttoncotuong:
                Intent a = new Intent(getActivity(), CoTuong_Screen.class);
                startActivity(a);
                break;
            case R.id.buttoncaro:
                Intent b = new Intent(getActivity(), TicTac_Screen.class);
                startActivity(b);
                break;
            case R.id.buttonconsobian:
                Intent c = new Intent(getActivity(), SecretNum_Screen.class);
                startActivity(c);
                break;
            case R.id.buttonailatrieuphu:
                Intent d = new Intent(getActivity(), BatDauGame.class);
                startActivity(d);
                break;
            default:
                break;
        }
    }
}
