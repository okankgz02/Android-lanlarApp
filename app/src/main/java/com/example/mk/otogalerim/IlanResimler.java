package com.example.mk.otogalerim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mk.otogalerim.Models.ResimEklePojo;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanResimler extends AppCompatActivity {

    Button resimSecButn, resimEkleButon, ilanYayınlaButon;
    ImageView secilenIlanResmiImageView;
    Bitmap bitmap;
    String uye_id, ilan_id, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        tanimla();
        Bundle bundle = getIntent().getExtras();

         uye_id = String.valueOf(bundle.getInt("uye_id"));
        ilan_id = String.valueOf(bundle.getInt("ilan_id"));
       


    }

    public void tanimla() {
        resimSecButn = (Button) findViewById(R.id.resimSecButn);
        resimEkleButon = (Button) findViewById(R.id.resimEkleButon);
        ilanYayınlaButon = (Button) findViewById(R.id.ilanYayınlaButon);
        secilenIlanResmiImageView = (ImageView) findViewById(R.id.secilenIlanResmiImageView);


        resimSecButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimGoster();

            }
        });


        resimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yukle();
            }
        });

    }

    public void resimGoster() { // galeriyi açar
        Intent ıntent = new Intent();
        ıntent.setType("image/*");
        ıntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(ıntent, 777);
    }

    public void yukle() {
        image = imageToString();
        Call<ResimEklePojo> request = ManagerAll.getInstance().resimEkle(uye_id,ilan_id,image);
        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                secilenIlanResmiImageView.setImageBitmap(bitmap);
                secilenIlanResmiImageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public String imageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imageToString;
    }

}
//