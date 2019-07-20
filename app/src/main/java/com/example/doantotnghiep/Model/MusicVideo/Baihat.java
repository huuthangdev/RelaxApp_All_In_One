package com.example.doantotnghiep.Model.MusicVideo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat implements Parcelable {

    @SerializedName("Idbaihat")
    @Expose
    private String idbaihat;
    @SerializedName("Tenbaihat")
    @Expose
    private String tenbaihat;
    @SerializedName("Hinhbaihat")
    @Expose
    private String hinhbaihat;
    @SerializedName("Casi")
    @Expose
    private String casi;
    @SerializedName("Linkbaihat")
    @Expose
    private String linkbaihat;
    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    private Baihat(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

    public String getTenbaihat() {
        return tenbaihat;
    }

    public String getHinhbaihat() {
        return hinhbaihat;
    }

    public String getCasi() {
        return casi;
    }

    public String getLinkbaihat() {
        return linkbaihat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idbaihat);
        parcel.writeString(tenbaihat);
        parcel.writeString(hinhbaihat);
        parcel.writeString(casi);
        parcel.writeString(linkbaihat);
        parcel.writeString(luotthich);
    }
}