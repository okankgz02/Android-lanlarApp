package com.example.mk.otogalerim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mk.otogalerim.Models.Update;
import com.example.mk.otogalerim.Models.User;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BilgiActvity extends AppCompatActivity {

    EditText userbilgi_username, userbilgi_pass;
    Button userbilgi_buton;
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi_actvity);
        tanimla();
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uye_id = sharedPreferences.getString("uye_id", null);

        istekAt(uye_id);


    }

    public void tanimla() {
        userbilgi_username = (EditText) findViewById(R.id.userbilgi_username);
        userbilgi_pass = (EditText) findViewById(R.id.userbilgi_pass);
        userbilgi_buton = (Button) findViewById(R.id.userbilgi_buton);
        userbilgi_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change(uye_id,userbilgi_username.getText().toString(),userbilgi_pass.getText().toString());
            }
        });
    }

    public void istekAt(String uye_id) {


        Call<User> request = ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userbilgi_username.setText(response.body().getKadi());
                    userbilgi_pass.setText(response.body().getSifre());
                    Log.i("username",response.body().getKadi());
                    Toast.makeText(getApplicationContext(), "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {


            }
        });
    }

    public void change(String userid,String user,String pass)
    {
        Call<Update> request  = ManagerAll.getInstance().changeInformation(userid,user,pass);
        request.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                if(response.body().isTf())
                {
                    Intent ıntent = new Intent(BilgiActvity.this,BilgiActvity.class);
                    startActivity(ıntent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {

            }
        });
    }


}
