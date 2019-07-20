package com.example.doantotnghiep.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doantotnghiep.Activity.TienIch.MapsActivity;
import com.example.doantotnghiep.R;


public class Fragment_Call extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        LinearLayout call = view.findViewById(R.id.layoutcall);
        call.setOnClickListener(view1 -> {
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:0334572911"));
            startActivity(i);
        });

        LinearLayout map = view.findViewById(R.id.layoutmap);
        map.setOnClickListener(view12 -> {
            Intent i = new Intent(getActivity(), MapsActivity.class);
            startActivity(i);
        });

        return view;
    }
}
