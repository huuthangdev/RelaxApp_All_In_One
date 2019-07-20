package com.example.doantotnghiep.Model.MusicVideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Truyen implements Serializable {
    @SerializedName("IdTruyen")
    @Expose
    private String idTruyen;
    @SerializedName("TenTruyen")
    @Expose
    private String tenTruyen;
    @SerializedName("TenTacGia")
    @Expose
    private String tenTacGia;
    @SerializedName("HinhTruyen")
    @Expose
    private String hinhTruyen;

    public Truyen() {
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public String getHinhTruyen() {
        return hinhTruyen;
    }

}
