package com.example.mk.otogalerim;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.otogalerim.Adapters.SliderAdapter;
import com.example.mk.otogalerim.Models.FavoriIslem;
import com.example.mk.otogalerim.Models.FavoriKontrol;
import com.example.mk.otogalerim.Models.IlanDetayPojo;
import com.example.mk.otogalerim.Models.SliderPojo;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import java.util.List;


import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetay extends AppCompatActivity {

    private TextView ilandetayBaslık, ilandetayFiyat, ilandetayMarka, ilandetayMotorTipi, ilandetayModel, ilandetaySeri, ilandetayYil, ilandetayIlanTipi, ilandetayKimden, ilandetayMotorHacmi, ilandetaySurat, ilandetayYakitTipi, ilandetayOrtalamaYakit, ilandetayDepoHacmi, ilandetayKm;
    private Button ilanMesajGonder, ilandetayFavoriyeAl;
    private ViewPager ilandetaySlider;


    String ilanId;
    List<SliderPojo> list;
    SliderAdapter sliderAdapter;
    CircleIndicator circleIndicator; /* Resim üzerinde nokta göstterimi için */
    SharedPreferences sharedPreferences;
    String uye_id, otherId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);

        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getString("ilanid");

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);


        tanimla();
        getIlanDetay();
        getResim();
        getText();
        action();

    }

    public void tanimla() {

        circleIndicator = (CircleIndicator) findViewById(R.id.sliderCircle);
        ilandetayMotorTipi = (TextView) findViewById(R.id.ilandetayMotorTipi);
        ilandetayBaslık = (TextView) findViewById(R.id.ilandetayBaslık);
        ilandetayFiyat = (TextView) findViewById(R.id.ilandetayFiyat);
        ilandetayMarka = (TextView) findViewById(R.id.ilandetayMarka);
        ilandetayModel = (TextView) findViewById(R.id.ilandetayModel);
        ilandetaySeri = (TextView) findViewById(R.id.ilandetaySeri);
        ilandetayYil = (TextView) findViewById(R.id.ilandetayYil);
        ilandetayIlanTipi = (TextView) findViewById(R.id.ilandetayIlanTipi);
        ilandetayKimden = (TextView) findViewById(R.id.ilandetayKimden);
        ilandetayMotorHacmi = (TextView) findViewById(R.id.ilandetayMotorHacmi);
        ilandetaySurat = (TextView) findViewById(R.id.ilandetaySurat);
        ilandetayYakitTipi = (TextView) findViewById(R.id.ilandetayYakitTipi);
        ilandetayOrtalamaYakit = (TextView) findViewById(R.id.ilandetayOrtalamaYakit);
        ilandetayDepoHacmi = (TextView) findViewById(R.id.ilandetayDepoHacmi);
        ilandetayKm = (TextView) findViewById(R.id.ilandetayKm);

        ilanMesajGonder = (Button) findViewById(R.id.ilanMesajGonder);


        ilandetayFavoriyeAl = (Button) findViewById(R.id.ilandetayFavoriyeAl);

        ilandetaySlider = (ViewPager) findViewById(R.id.ilandetaySlider);

    }

    public void getIlanDetay(){
    Call<IlanDetayPojo> request=ManagerAll.getInstance().ilanDetay(ilanId);
    request.enqueue(new Callback<IlanDetayPojo>() {
        @Override
        public void onResponse(Call<IlanDetayPojo> call, Response<IlanDetayPojo> response) {
            ilandetayMotorTipi.setText(response.body().getMotortipi());
            ilandetayBaslık.setText(response.body().getBaslik());
            ilandetayFiyat.setText(response.body().getUcret());
            ilandetayMarka.setText(response.body().getMarka());
            ilandetayModel.setText(response.body().getModel());
            ilandetaySeri.setText(response.body().getSeri());
            ilandetayYil.setText(response.body().getYil());
            ilandetayIlanTipi.setText(response.body().getIlantipi());
            ilandetayKimden.setText(response.body().getKimden());
            ilandetayMotorHacmi.setText(response.body().getMotorhacmi());
            ilandetaySurat.setText(response.body().getSurat());
            ilandetayYakitTipi.setText(response.body().getYakittipi());
            ilandetayOrtalamaYakit.setText(response.body().getOrtalamayakit());
            ilandetayDepoHacmi.setText(response.body().getDepohacmi());
            ilandetayKm.setText(response.body().getKm());
            //otherId = response.body().getUyeid();
        }

        @Override
        public void onFailure(Call<IlanDetayPojo> call, Throwable t) {

        }
    });

    }

   public void getResim() {
        Call<List<SliderPojo>> request = ManagerAll.getInstance().ilanResimleri(ilanId);
        request.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {
                list = response.body();
                sliderAdapter = new SliderAdapter(list, getApplicationContext());
                ilandetaySlider.setAdapter(sliderAdapter); /* listview gibi*/

                circleIndicator.setViewPager(ilandetaySlider);
                circleIndicator.bringToFront(); /* noktayı one cekmek*/

            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {

            }
        });
    }

    public void getText(){
        Call<FavoriKontrol> request=ManagerAll.getInstance().getButtonText(uye_id,ilanId);
        request.enqueue(new Callback<FavoriKontrol>() {
            @Override
            public void onResponse(Call<FavoriKontrol> call, Response<FavoriKontrol> response) {
                if (response.body().isTf()){
                    ilandetayFavoriyeAl.setText(response.body().getText());
                }else{

                    ilandetayFavoriyeAl.setText(response.body().getText());

                }
            }

            @Override
            public void onFailure(Call<FavoriKontrol> call, Throwable t) {

            }
        });
    }

   public void action() {
        ilandetayFavoriyeAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<FavoriIslem> request = ManagerAll.getInstance().favoriIslem(uye_id, ilanId);
                request.enqueue(new Callback<FavoriIslem>() {
                    @Override
                    public void onResponse(Call<FavoriIslem> call, Response<FavoriIslem> response) {
                        if (response.body().isTf()) {
                            Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                            getText();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                            getText();
                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriIslem> call, Throwable t) {

                    }
                });
            }
        });
/*
        ilanMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(IlanDetay.this, ChatActivity.class);
                OtherId.setOtherId(otherId);
                startActivity(ıntent);
            }
        });
    }*/
}
}