package com.example.mk.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mk.otogalerim.Models.IlanVerPojo;

import java.util.ArrayList;
import java.util.List;

public class IlanTuru extends AppCompatActivity {

    Spinner ilanturuSpinner ,kimdenSpinner;
    Button ilanTuruButton,ilanTuruButtonGeri;
    List<String> turList;
    List<String> sahipList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);
        listeDoldur();
        tanimla();
    }

    public void tanimla()
    {
        ilanturuSpinner = (Spinner) findViewById(R.id.ilanturuSpinner);
        kimdenSpinner = (Spinner)findViewById(R.id.kimdenSpinner);

        ArrayAdapter<String> turListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanturuSpinner.setAdapter(turListAdapter);


        ArrayAdapter<String> sahipListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(sahipListAdapter);

        ilanTuruButton = (Button) findViewById(R.id.ilanTuruButton);
        ilanTuruButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IlanVerPojo.setKimden(kimdenSpinner.getSelectedItem().toString());
                IlanVerPojo.setIlantipi(ilanturuSpinner.getSelectedItem().toString());
                Intent ıntent = new Intent(IlanTuru.this,AdresBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });


        ilanTuruButtonGeri = (Button) findViewById(R.id.ilanTuruButtonGeri);
        ilanTuruButtonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(IlanTuru.this,IlanBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
    public void listeDoldur()
    {
     turList = new ArrayList<>();
     sahipList = new ArrayList<>();
     turList.add("Satılık");
     turList.add("Kiralık");
     sahipList.add("Sahibinden");
     sahipList.add("Galariden");
    }
}
//ilanturuSpinner