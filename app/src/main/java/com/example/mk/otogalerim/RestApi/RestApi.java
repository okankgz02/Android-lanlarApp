package com.example.mk.otogalerim.RestApi;

import com.example.mk.otogalerim.Models.DogrulamaPojo;
import com.example.mk.otogalerim.Models.FavoriIslem;
import com.example.mk.otogalerim.Models.FavoriKontrol;
import com.example.mk.otogalerim.Models.FavoriSliderPojo;

import com.example.mk.otogalerim.Models.IlanDetayPojo;
import com.example.mk.otogalerim.Models.IlanSonucPojo;
import com.example.mk.otogalerim.Models.IlanlarPojo;
import com.example.mk.otogalerim.Models.IlanlarimPojo;
import com.example.mk.otogalerim.Models.IlanlarimSilPojo;
import com.example.mk.otogalerim.Models.LoginPojo;
import com.example.mk.otogalerim.Models.RegisterPojo;
import com.example.mk.otogalerim.Models.ResimEklePojo;
import com.example.mk.otogalerim.Models.SliderPojo;
import com.example.mk.otogalerim.Models.Update;
import com.example.mk.otogalerim.Models.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApi {
    @FormUrlEncoded
    @POST("/login.php")
    Call<LoginPojo> control(@Field("kad") String ad, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/register.php")
    Call<RegisterPojo> kayitol(@Field("kadi") String ad, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/dogrulama.php")
    Call<DogrulamaPojo> dogrulama(@Field("kadi") String ad, @Field("kod") String kod);


    @FormUrlEncoded
    @POST("/ilanver.php")
    Call<IlanSonucPojo> ilanVer(@Field("uye_id") String uye_id, @Field("sehir") String sehir, @Field("ilce") String ilce, @Field("mahalle") String mahalle, @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("yil") String yil, @Field("ilantipi") String ilantipi, @Field("kimden") String kimden, @Field("baslik") String baslik, @Field("aciklama") String aciklama, @Field("motortipi") String motortipi, @Field("motorhacmi") String motorhacmi, @Field("surat") String surat, @Field("yakittipi") String yakittipi, @Field("ortalamayakit") String ortalamayakit, @Field("depohacmi") String depohacmi, @Field("km") String km);


    @FormUrlEncoded
    @POST("/ilanresmiekle.php")
    Call<ResimEklePojo> resimYukle(@Field("uye_id") String uye_id, @Field("ilan_id") String ilan_id, @Field("resim") String base64StringResim);



    @GET("/ilanlarim.php")
    Call<List<IlanlarimPojo>> ilanlarim(@Query("uyeid") String uyeid);

    @GET("/ilanlarimdansil.php")
    Call<IlanlarimSilPojo> ilanlarimSil(@Query("ilan_id") String ilanid);

    @GET("/ilanlar.php")
    Call<List<IlanlarPojo>> ilanlar();

    @GET("/ilandetay.php")
    Call<IlanDetayPojo> ilanDetay(@Query("ilanid") String ilanid);

    @GET("/ilanresimleri.php")
    Call<List<SliderPojo>> ilanResimleri(@Query("ilanid") String ilanid);


    @GET("/favori.php")
    Call<FavoriKontrol> getButonText(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);



    @GET("/favoriislem.php")
    Call<FavoriIslem> favoriIslem(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);



    @GET("/favoriilanslider.php")
    Call<List<FavoriSliderPojo>> setSlider(@Query("uye_id") String uyeid);

    @GET("/bilgiler.php")
    Call<User> getInformation(@Query("uyeid") String uyeid);

    @GET("/guncelle.php")
    Call<Update> chandeInformation(@Query("uyeid") String uyeid, @Query("user") String user, @Query("pass") String pass);


}
