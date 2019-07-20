package com.example.doantotnghiep.Activity.TienIch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.doantotnghiep.R;

public class FlashActivity extends AppCompatActivity {
    private ImageButton imgBtnSwitch;
    private static final int camera_request = 50;
    private boolean hasACameraFlash;
    private boolean flashlightStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        hasACameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        imgBtnSwitch = findViewById(R.id.imgBtnSwitch);
        imgBtnSwitch.setOnClickListener(view -> imgBtnSwitchOnClicked());
    }

    public void imgBtnSwitchOnClicked() {
        if (ContextCompat.checkSelfPermission(FlashActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, camera_request);
            }
            return;
        }

        imgBtnSwitch.setOnClickListener(v -> {
            if (hasACameraFlash) {
                if (flashlightStatus) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setFlashLightOff();
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setFlashLightOn();
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setFlashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String camID = null;
            if (cameraManager != null) {
                camID = cameraManager.getCameraIdList()[0];
            }
            if (cameraManager != null) {
                cameraManager.setTorchMode(camID, true);
            }
            flashlightStatus = true;
            imgBtnSwitch.setImageResource(R.drawable.icon_off);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setFlashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String camID = null;
            if (cameraManager != null) {
                camID = cameraManager.getCameraIdList()[0];
            }
            if (cameraManager != null) {
                cameraManager.setTorchMode(camID, false);
            }
            flashlightStatus = false;
            imgBtnSwitch.setImageResource(R.drawable.icon_on);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == camera_request && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            imgBtnSwitchOnClicked();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setFlashLightOff();
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
