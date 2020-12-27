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

import java.util.List;

public class ParkingActivity extends AppCompatActivity {
    DatabaseHelper database;
    RecyclerView mRecyclerView;
    ParkingAdapter mAdapter;
    String korisnik,grad,datum,cas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);

        Intent intent = getIntent();
         korisnik = intent.getStringExtra("Korisnik");
         grad = intent.getStringExtra("Grad");
         datum = intent.getStringExtra("Datum");
         cas = intent.getStringExtra("Cas");

        TextView info = findViewById(R.id.info);
        info.setText(korisnik+" "+grad+" "+ datum +" "+cas);

        database = new DatabaseHelper(this);
        List<String> valuesNames = database.getParkings(grad);

        mRecyclerView = findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ParkingAdapter(valuesNames, R.layout.parking_list, grad,datum, cas, korisnik, database,this);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId())
        {
            case R.id.button:
                Intent moirezervaci=new Intent(this,MyReservationsActivity.class);
                moirezervaci.putExtra("Korisnik",korisnik);
                startActivity(moirezervaci);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}