package com.example.doantotnghiep.Model.MusicVideo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phim implements Parcelable {
    @SerializedName("IdPhim")
    @Expose
    private String idPhim;
    @SerializedName("TenPhim")
    @Expose
    private String tenPhim;
    @SerializedName("HinhPhim")
    @Expose
    private String hinhPhim;
    @SerializedName("LinkPhim")
    @Expose
    private String linkPhim;
    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    public String getTenPhim() {
        return tenPhim;
    }

    public String getHinhPhim() {
        return hinhPhim;
    }

    public String getLinkPhim() {
        return linkPhim;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idPhim);
        parcel.writeString(tenPhim);
        parcel.writeString(hinhPhim);
        parcel.writeString(linkPhim);
        parcel.writeString(luotthich);
    }

    private Phim(Parcel in) {
        idPhim = in.readString();
        tenPhim = in.readString();
        hinhPhim = in.readString();
        linkPhim = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Phim> CREATOR = new Creator<Phim>() {
        @Override
        public Phim createFromParcel(Parcel in) {
            return new Phim(in);
        }

        @Override
        public Phim[] newArray(int size) {
            return new Phim[size];
        }
    };

}

