package com.example.mk.otogalerim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mk.otogalerim.Models.IlanSonucPojo;
import com.example.mk.otogalerim.Models.IlanVerPojo;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Yakit extends AppCompatActivity {

    EditText yakitTipiBilgiEditText, ortalamaYakitEditText, depoHacmiSüratEditText;
    Button yakitTuketimBilgisiButon, yakitTuketimBilgisiButonGeri;
    SharedPreferences sharedPreferences;
    private View mProgressView;
    private View yakitFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);
        tanimla();
    }

    public void tanimla() {
        yakitFormView = findViewById(R.id.yakit_view);
        mProgressView = findViewById(R.id.login_progressYakit);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        IlanVerPojo.setUye_id(sharedPreferences.getString("uye_id", null));


        yakitTipiBilgiEditText = (EditText) findViewById(R.id.yakitTipiBilgiEditText);
        ortalamaYakitEditText = (EditText) findViewById(R.id.ortalamaYakitEditText);
        depoHacmiSüratEditText = (EditText) findViewById(R.id.depoHacmiSüratEditText);

        yakitTipiBilgiEditText.setText(IlanVerPojo.getYakittipi());
        ortalamaYakitEditText.setText(IlanVerPojo.getOrtalamayakit());
        depoHacmiSüratEditText.setText(IlanVerPojo.getDepohacmi());


        yakitTuketimBilgisiButon = (Button) findViewById(R.id.yakitTuketimBilgisiButon);
        yakitTuketimBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!yakitTipiBilgiEditText.getText().toString().equals("") && !ortalamaYakitEditText.getText().toString().equals("") && !depoHacmiSüratEditText.getText().toString().equals("")) {
                    showProgress(true);
                    IlanVerPojo.setYakittipi(yakitTipiBilgiEditText.getText().toString());
                    IlanVerPojo.setOrtalamayakit(ortalamaYakitEditText.getText().toString());
                    IlanVerPojo.setDepohacmi(depoHacmiSüratEditText.getText().toString());

                    ilaniYayinla( IlanVerPojo.getUye_id(), IlanVerPojo.getSehir(), IlanVerPojo.getIlce(), IlanVerPojo.getMahalle(), IlanVerPojo.getMarka(), IlanVerPojo.getSeri(), IlanVerPojo.getModel(), IlanVerPojo.getYil(), IlanVerPojo.getIlantipi(), IlanVerPojo.getKimden(), IlanVerPojo.getBaslik(), IlanVerPojo.getAciklama(), IlanVerPojo.getMotortipi(), IlanVerPojo.getMotorhacmi(), IlanVerPojo.getSurat(), IlanVerPojo.getYakittipi(), IlanVerPojo.getOrtalamayakit(), IlanVerPojo.getDepohacmi(), IlanVerPojo.getKm());


                } else {
                    Toast.makeText(getApplicationContext(), "Tüm Bilgileri Giriniz", Toast.LENGTH_LONG).show();
                }
            }
        });


        yakitTuketimBilgisiButonGeri = (Button) findViewById(R.id.yakitTuketimBilgisiButonGeri);
        yakitTuketimBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(Yakit.this, MotorPerformans.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();

            }
        });
    }

    public void ilaniYayinla(String uye_id, String sehir, String ilce, String mahalle, String marka, String seri, String model, String yil, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km) {
        final Call<IlanSonucPojo> request = ManagerAll.getInstance().ilanVer(uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km);
        request.enqueue(new Callback<IlanSonucPojo>() {
            @Override
            public void onResponse(Call<IlanSonucPojo> call, Response<IlanSonucPojo> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(), "Ilanınız Yayınlanmıştır.", Toast.LENGTH_LONG).show();
                    Intent ıntent = new Intent(Yakit.this, IlanResimler.class);
                    ıntent.putExtra("ilan_id", response.body().getIlanid());
                    ıntent.putExtra("uye_id", response.body().getUyeid());
                    Log.i("test 1 ", response.body().getIlanid()+"//"+response.body().getUyeid());
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                    showProgress(false);
                } else {
                    Toast.makeText(getApplicationContext(), "Ilanınız Yayınlanmamıştır.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<IlanSonucPojo> call, Throwable t) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            yakitFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
