package com.example.mk.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mk.otogalerim.Models.IlanVerPojo;

public class AracBilgileri extends AppCompatActivity {

    Button aracBilgisiButon, aracBilgisiButonGeri;
    EditText markaBilgiEditText, seriBilgiEditText, modelBilgiEditText, yilBilgiEditText, kmBilgiEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);
        tanimla();
    }

    public void tanimla() {

        markaBilgiEditText = (EditText) findViewById(R.id.markaBilgiEditText);
        seriBilgiEditText = (EditText) findViewById(R.id.seriBilgiEditText);
        modelBilgiEditText = (EditText) findViewById(R.id.modelBilgiEditText);
        yilBilgiEditText = (EditText) findViewById(R.id.yilBilgiEditText);
        kmBilgiEditText=(EditText) findViewById(R.id.kmBilgiEditText);

        markaBilgiEditText.setText(IlanVerPojo.getMarka());
        seriBilgiEditText.setText(IlanVerPojo.getSeri());
        modelBilgiEditText.setText(IlanVerPojo.getModel());
        yilBilgiEditText.setText(IlanVerPojo.getYil());
        kmBilgiEditText.setText(IlanVerPojo.getKm());


        aracBilgisiButon = (Button) findViewById(R.id.aracBilgisiButon);
        aracBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!markaBilgiEditText.getText().toString().equals("") && !seriBilgiEditText.getText().toString().equals("") && !modelBilgiEditText.getText().toString().equals("") && !yilBilgiEditText.getText().toString().equals("") && !kmBilgiEditText.getText().toString().equals("")) {

                    IlanVerPojo.setMarka(markaBilgiEditText.getText().toString());
                    IlanVerPojo.setSeri(seriBilgiEditText.getText().toString());
                    IlanVerPojo.setModel(modelBilgiEditText.getText().toString());
                    IlanVerPojo.setYil(yilBilgiEditText.getText().toString());
                    IlanVerPojo.setKm(kmBilgiEditText.getText().toString());

                    Intent ıntent = new Intent(AracBilgileri.this, MotorPerformans.class);
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Tüm Bilgileri Giriniz", Toast.LENGTH_LONG).show();
                }

            }
        });


        aracBilgisiButonGeri = (Button) findViewById(R.id.aracBilgisiButonGeri);
        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(AracBilgileri.this, AdresBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();

            }
        });
    }
}
//ilanbilgileri / adres bilgileri / araçbilgileri / moto / yakit