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

import com.example.doantotnghiep.Activity.TienIch.FlashActivity;
import com.example.doantotnghiep.Activity.TienIch.TienDienActivity;
import com.example.doantotnghiep.R;


public class Fragment_Tool extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tienich, container, false);
        Button layoutflash = view.findViewById(R.id.buttonflash);
        Button layouttiendien = view.findViewById(R.id.buttontiendien);
        layoutflash.setOnClickListener(this);
        layouttiendien.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonflash:
                Intent a = new Intent(getActivity(), FlashActivity.class);
                startActivity(a);
                break;

            case R.id.buttontiendien:
                Intent b = new Intent(getActivity(), TienDienActivity.class);
                startActivity(b);
                break;
            default:
                break;
        }
    }
}
