package com.example.mk.otogalerim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mk.otogalerim.Adapters.IlanlarimAdapter;
import com.example.mk.otogalerim.Models.IlanlarimPojo;
import com.example.mk.otogalerim.Models.IlanlarimSilPojo;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ilanlarim extends AppCompatActivity {

    ListView listView;
    IlanlarimAdapter ılanlarimAdapter;
    List<IlanlarimPojo> ılanlarimPojos;
    SharedPreferences sharedPreferences;
    String uye_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        listView = findViewById(R.id.ilanlarimListView);

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);

        ilanlarimiGoruntule();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Log.i("test",ılanlarimPojos.get(position).getBaslik());
                Toast.makeText(Ilanlarim.this, ""+ ılanlarimPojos.get(position).getIlanid().toString(), Toast.LENGTH_SHORT).show();
            ilanlarımAlertDialog(Ilanlarim.this, ılanlarimPojos.get(position).getIlanid());
        }
        });

    }

    public void ilanlarımAlertDialog(Activity activity, final String ilan_id) {
        LayoutInflater ınflater = activity.getLayoutInflater();
        View view = ınflater.inflate(R.layout.alertlayout, null);


        Button button = (Button) view.findViewById(R.id.buton);
        Button buttonCık = (Button) view.findViewById(R.id.buton2);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        buttonCık.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(ilan_id);
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void ilanlarimiGoruntule() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlarım");
        progressDialog.setMessage("İlanlarınız Yükleniyor , Lütfen Beleyin ... ");
        progressDialog.setCancelable(false);// iptal edilebilirliği kapatıyor , sebebi bir dialogu arka planda işlemler bitene kadar göstermek istiyoruz .
        progressDialog.show();

        ılanlarimPojos = new ArrayList<>();
        Call<List<IlanlarimPojo>> request = ManagerAll.getInstance().ilanlarim(uye_id);
        request.enqueue(new Callback<List<IlanlarimPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojo>> call, Response<List<IlanlarimPojo>> response) {

                if (response.isSuccessful()) {

                    ılanlarimPojos = response.body();
                    if (response.body().get(0).isTf()) {

                        ılanlarimAdapter = new IlanlarimAdapter(ılanlarimPojos, getApplicationContext(), Ilanlarim.this);
                        listView.setAdapter(ılanlarimAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + " tane ilanınız bulunmaktadır.", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();

                    } else {
                        Toast.makeText(getApplicationContext(), " Ilanınız bulunmamaktadır...", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                        Intent ıntent = new Intent(Ilanlarim.this, MainActivity.class);
                        startActivity(ıntent);
                        overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {

            }
        });
    }



    public void sil(String ilanId) {
        Call<IlanlarimSilPojo> request = ManagerAll.getInstance().ilanlarimSil(ilanId);
        request.enqueue(new Callback<IlanlarimSilPojo>() {
            @Override
            public void onResponse(Call<IlanlarimSilPojo> call, Response<IlanlarimSilPojo> response) {
                if (response.body().isTf()) {
                    ilanlarimiGoruntule();
                }
            }

            @Override
            public void onFailure(Call<IlanlarimSilPojo> call, Throwable t) {

            }
        });
    }

}
