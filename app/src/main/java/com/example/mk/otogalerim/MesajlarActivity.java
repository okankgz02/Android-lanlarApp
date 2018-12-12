package com.example.mk.otogalerim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MesajlarActivity extends AppCompatActivity  {

    List<String> otherIdList;
    String userId;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    MesajlarAdapter mesajlarAdapter;
    ListView mesajlarlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlar);
        otherIdList = new ArrayList<>();

        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        userId = sharedPreferences.getString("uye_id", null);
        reference = FirebaseDatabase.getInstance().getReference();
        mesajlarAdapter = new MesajlarAdapter(otherIdList, userId, getApplicationContext(),MesajlarActivity.this);
        mesajlarlistview = (ListView) findViewById(R.id.mesajlarlistview);
        mesajlarlistview.setAdapter(mesajlarAdapter);
        listele();
    }

    public void listele() {
        reference.child("messages").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("mesajlar", dataSnapshot.getKey());
                otherIdList.add(dataSnapshot.getKey());
                mesajlarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
