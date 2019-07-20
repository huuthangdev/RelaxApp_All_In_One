package com.example.doantotnghiep.Model.MusicVideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quangcao implements Serializable {

    @SerializedName("IdQuangCao")
    @Expose
    private String idQuangCao;
    @SerializedName("Hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("Noidung")
    @Expose
    private String noidung;
    @SerializedName("IdBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;

    public String getIdQuangCao() {
        return idQuangCao;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

}