package com.example.mk.otogalerim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mk.otogalerim.Adapters.IlanlarAdapter;
import com.example.mk.otogalerim.Models.IlanlarPojo;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ilanlar extends AppCompatActivity {
    ListView listView;
    List<IlanlarPojo> ılanlarPojoList;
    IlanlarAdapter ılanlarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);
        listView = findViewById(R.id.ilanlarListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ıntent = new Intent(Ilanlar.this,IlanDetay.class);
                ıntent.putExtra("ilanid",ılanlarPojoList.get(position).getIlanid());

                startActivity(ıntent);
            }
        });
        ilanlarimiGoruntule();
    }

    public void ilanlarimiGoruntule() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ilanlar");
        progressDialog.setMessage("İlanlar Listeleniyor , Lütfen Beleyin ... ");
        progressDialog.setCancelable(false);// iptal edilebilirliği kapatıyor , sebebi bir dialogu arka planda işlemler bitene kadar göstermek istiyoruz .
        progressDialog.show();
        final Call<List<IlanlarPojo>> request = ManagerAll.getInstance().ilanlar();
        request.enqueue(new Callback<List<IlanlarPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
                if (response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        ılanlarPojoList = response.body();
                        ılanlarAdapter = new IlanlarAdapter(ılanlarPojoList, getApplicationContext());
                        listView.setAdapter(ılanlarAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + " tane ilan listelenmiştir.", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarPojo>> call, Throwable t) {

            }
        });


    }
}
