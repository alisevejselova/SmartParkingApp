package com.example.smartparkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {
    String grad,parking,korisnik,datum,cas;
    float lat,lng;
    DatabaseHelper database;
    RecyclerView mRecyclerView;
    MyReservationsAdapter mAdapter;
    ArrayList<MyReservationsModel> arrayList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        intent = getIntent();
//        grad = intent.getStringExtra("Grad");
//        parking = intent.getStringExtra("Parking");
      korisnik = intent.getStringExtra("Korisnik");
//        datum = intent.getStringExtra("Datum");
//        cas = intent.getStringExtra("Vreme");


       TextView korisnikime = findViewById(R.id.username);
        korisnikime.setText(korisnik);

        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);

        database = new DatabaseHelper(this);
        //List<String> valuesNames = database.getUserReservations(korisnik);
        arrayList = new ArrayList<>(database.getUserReservations(korisnik));
        mRecyclerView = findViewById(R.id.recycler_view3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyReservationsAdapter( getApplicationContext(),this,arrayList);
        mRecyclerView.setAdapter(mAdapter);

    }

}