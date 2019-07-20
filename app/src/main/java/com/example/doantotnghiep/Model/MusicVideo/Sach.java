package com.example.doantotnghiep.Model.MusicVideo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sach implements Parcelable {
    @SerializedName("IdSach")
    @Expose
    private String idSach;
    @SerializedName("TenSach")
    @Expose
    private String tenSach;
    @SerializedName("HinhSach")
    @Expose
    private String hinhSach;
    @SerializedName("LinkSach")
    @Expose
    private String linkSach;
    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    public String getTenSach() {
        return tenSach;
    }

    public String getHinhSach() {
        return hinhSach;
    }

    public String getLinkSach() {
        return linkSach;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idSach);
        parcel.writeString(tenSach);
        parcel.writeString(hinhSach);
        parcel.writeString(linkSach);
        parcel.writeString(luotthich);
    }

    private Sach(Parcel in) {
        idSach = in.readString();
        tenSach = in.readString();
        hinhSach = in.readString();
        linkSach = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Sach> CREATOR = new Creator<Sach>() {
        @Override
        public Sach createFromParcel(Parcel in) {
            return new Sach(in);
        }

        @Override
        public Sach[] newArray(int size) {
            return new Sach[size];
        }
    };

}
