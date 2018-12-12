package com.example.mk.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mk.otogalerim.Models.IlanVerPojo;

public class IlanBilgileri extends AppCompatActivity {

    Button ilanBilgisiButon, ilanBilgisiButonGeri;
    EditText ilanAciklamaEditText, ilanBaslikEditText,ilanFiyatEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);
        tanimla();
    }

    public void tanimla() {
        ilanAciklamaEditText = (EditText) findViewById(R.id.ilanAciklamaEditText);
        ilanBaslikEditText = (EditText) findViewById(R.id.ilanBaslikEditText);
        ilanBilgisiButon = (Button) findViewById(R.id.ilanBilgisiButon);

        ilanFiyatEditText = (EditText) findViewById(R.id.ilanFiyatEditText);

        ilanAciklamaEditText.setText(IlanVerPojo.getAciklama());
        ilanBaslikEditText.setText(IlanVerPojo.getBaslik());
        ilanFiyatEditText.setText(IlanVerPojo.getUcret());


        ilanBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ilanAciklamaEditText.getText().toString().equals("") && !ilanBaslikEditText.getText().toString().equals("") && !ilanFiyatEditText.getText().toString().equals("")) {
                    IlanVerPojo.setAciklama(ilanAciklamaEditText.getText().toString());
                    IlanVerPojo.setBaslik(ilanBaslikEditText.getText().toString());
                    IlanVerPojo.setUcret(ilanFiyatEditText.getText().toString());


                    Intent ıntent = new Intent(IlanBilgileri.this, IlanTuru.class);
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Tüm Bilgileri Giriniz", Toast.LENGTH_LONG).show();
                }
            }
        });


        ilanBilgisiButonGeri = (Button) findViewById(R.id.ilanBilgisiButonGeri);
        ilanBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(IlanBilgileri.this, MainActivity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();
            }
        });
    }
}

