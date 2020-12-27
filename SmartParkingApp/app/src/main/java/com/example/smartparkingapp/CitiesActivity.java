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
import java.util.Arrays;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CityAdapter mAdapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);

        List<String> cities = Arrays.asList("Valandovo", "Gevgelija", "Dojran",
                "Strumica", "Skopje", "Veles", "Bitola", "Prilep",
                "Struga", "Ohrid");

        Intent intent = getIntent();
        username = intent.getStringExtra("Username");

        TextView userinfo = findViewById(R.id.username);
        userinfo.setText(username);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CityAdapter(cities, R.layout.city_list,username, this);
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
                moirezervaci.putExtra("Korisnik",username);
               startActivity(moirezervaci);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
