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



public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<LoginPojo> login(String ad, String sifre) {
        Call<LoginPojo> x = getRestApi().control(ad, sifre);
        return x;
    }

    public Call<RegisterPojo> register(String ad, String sifre) {
        Call<RegisterPojo> x = getRestApi().kayitol(ad, sifre);
        return x;
    }

    public Call<DogrulamaPojo> dogrula(String ad, String kod) {
        Call<DogrulamaPojo> x = getRestApi().dogrulama(ad, kod);
        return x;
    }

    public Call<IlanSonucPojo> ilanVer( String uye_id, String sehir, String ilce, String mahalle, String marka, String seri, String model, String yil, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km) {
        Call<IlanSonucPojo> x = getRestApi().ilanVer( uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km);
        return x;
    }

    public Call<ResimEklePojo> resimEkle(String uye_id, String ilan_id, String image) {
        Call<ResimEklePojo> x = getRestApi().resimYukle(uye_id, ilan_id, image);
        return x;
    }


    public Call<List<IlanlarimPojo>> ilanlarim(String uyeid) {
        Call<List<IlanlarimPojo>> x = getRestApi().ilanlarim(uyeid);
        return x;
    }

    public Call<IlanlarimSilPojo> ilanlarimSil(String ilanid) {
        Call<IlanlarimSilPojo> x = getRestApi().ilanlarimSil(ilanid);
        return x;
    }

    public Call<List<IlanlarPojo>> ilanlar() {
        Call<List<IlanlarPojo>> x = getRestApi().ilanlar();
        return x;
    }

    public Call<IlanDetayPojo> ilanDetay(String ilanid) {
        Call<IlanDetayPojo> x = getRestApi().ilanDetay(ilanid);
        return x;
    }


    public Call<List<SliderPojo>> ilanResimleri(String ilanid) {
        Call<List<SliderPojo>> x = getRestApi().ilanResimleri(ilanid);
        return x;
    }


    public Call<FavoriKontrol> getButtonText(String uyeid, String ilanid) {
        Call<FavoriKontrol> x = getRestApi().getButonText(uyeid, ilanid);
        return x;
    }


    public Call<FavoriIslem> favoriIslem(String uyeid, String ilanid) {
        Call<FavoriIslem> x = getRestApi().favoriIslem(uyeid, ilanid);
        return x;
    }


    public Call<List<FavoriSliderPojo>> setSlider(String uyeid) {
        Call<List<FavoriSliderPojo>> x = getRestApi().setSlider(uyeid);
        return x;
    }


    public Call<User> getInformation(String uyeid) {
        Call<User> x = getRestApi().getInformation(uyeid);
        return x;
    }

    public Call<Update> changeInformation(String uyeid, String user, String pass) {
        Call<Update> x = getRestApi().chandeInformation(uyeid,user,pass);
        return x;
    }

}
