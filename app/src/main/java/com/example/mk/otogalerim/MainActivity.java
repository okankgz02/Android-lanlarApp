package com.example.mk.otogalerim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.mk.otogalerim.Adapters.FavoriSliderAdapter;
import com.example.mk.otogalerim.Models.FavoriSliderPojo;
import com.example.mk.otogalerim.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView navHeaderTextView;
    SharedPreferences.Editor editor;
    Button ilanVerButon, ilanlarimMenuButon, ılanlarButon, iletisimBilgileri, kisimesajlari;

    ViewPager mainActivitySliderFavori;
    CircleIndicator mainActivitysliderCircle;

    String uye_id;
    FavoriSliderAdapter favoriSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        navHeaderText = sharedPreferences.getString("uye_KullaniciAdi", null);
        uye_id = sharedPreferences.getString("uye_id", null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        navHeaderTextView = (TextView) headerView.findViewById(R.id.navHeaderText);
        navHeaderTextView.setText(navHeaderText);
        tanimla();
       getSlider();

    }

    public void tanimla() {


        mainActivitySliderFavori = (ViewPager) findViewById(R.id.mainActivitySliderFavori);
        mainActivitysliderCircle = (CircleIndicator) findViewById(R.id.mainActivitysliderCircle);

        ilanVerButon = (Button) findViewById(R.id.ilanVerButon);
        ilanVerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, IlanBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        ilanlarimMenuButon = (Button) findViewById(R.id.ilanlarimMenuButon);
        ilanlarimMenuButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, Ilanlarim.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });
        ılanlarButon = (Button) findViewById(R.id.ılanlarButon);

        ılanlarButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, Ilanlar.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });
        iletisimBilgileri = (Button) findViewById(R.id.iletisimBilgileri);
        iletisimBilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, BilgiActvity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        kisimesajlari = (Button) findViewById(R.id.kisimesajlari);
        kisimesajlari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, MesajlarActivity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.cikisYap) {

            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent ıntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(ıntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


        public void getSlider() {
        final Call<List<FavoriSliderPojo>> request = ManagerAll.getInstance().setSlider(uye_id);
        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {
                if (response.body().get(0).isTf()) {
                    if (response.body().size() > 0) {
                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                        mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                        mainActivitysliderCircle.setViewPager(mainActivitySliderFavori);
                        mainActivitysliderCircle.bringToFront();
                    }


                } else {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                    mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                    mainActivitysliderCircle.setViewPager(mainActivitySliderFavori);
                    mainActivitysliderCircle.bringToFront();
                }
            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       getSlider();
    }
}
