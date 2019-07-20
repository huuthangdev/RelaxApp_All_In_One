package com.example.doantotnghiep.Service;

import com.example.doantotnghiep.Model.MusicVideo.Album;
import com.example.doantotnghiep.Model.MusicVideo.Baihat;
import com.example.doantotnghiep.Model.MusicVideo.Phim;
import com.example.doantotnghiep.Model.MusicVideo.Quangcao;
import com.example.doantotnghiep.Model.MusicVideo.Sach;
import com.example.doantotnghiep.Model.MusicVideo.Truyen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("album.php")
    Call<List<Album>> GetAlbumHot();

    @GET("truyen.php")
    Call<List<Truyen>> GetTruyenHot();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @GET("listphim.php")
    Call<List<Phim>> GetPhimHot();

    @GET("listsach.php")
    Call<List<Sach>> GetSach();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat2.php")
    Call<List<Baihat>> GetBaiHatTheoTruyen(@Field("idtruyen") String idtruyen);

    @FormUrlEncoded
    @POST("danhsachbaihat3.php")
    Call<List<Baihat>> GetBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAlbum();

    @GET("tatcatruyen.php")
    Call<List<Truyen>> GetTruyen();

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> SearchBaiHat(@Field("tukhoa") String tukhoa);
}
