package com.example.doantotnghiep.Fragment.CoTuong;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

import com.example.doantotnghiep.R;

@SuppressWarnings("deprecation")
public class CoTuongSettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.cotuong_settings);
    }
}
