package com.example.doantotnghiep.Model.MusicVideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("TenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("TencasiAlbum")
    @Expose
    private String tencasiAlbum;
    @SerializedName("HinhanhAlbum")
    @Expose
    private String hinhanhAlbum;


    public String getIdAlbum() {
        return idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public String getTencasiAlbum() {
        return tencasiAlbum;
    }

    public String getHinhanhAlbum() {
        return hinhanhAlbum;
    }

}