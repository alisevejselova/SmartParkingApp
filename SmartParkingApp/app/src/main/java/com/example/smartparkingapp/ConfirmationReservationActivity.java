package com.example.smartparkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationReservationActivity extends AppCompatActivity {
    String grad,parking,korisnik,datum,cas;
    float lat,lng;
    TextView confirmationINFO;
    Button navigate,moirezervacii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_reservation);
        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);

        Intent intent = getIntent();
        grad = intent.getStringExtra("Grad");
        parking = intent.getStringExtra("Parking");
        korisnik = intent.getStringExtra("Korisnik");
        datum = intent.getStringExtra("Datum");
        cas = intent.getStringExtra("Vreme");
        lat = intent.getFloatExtra("Lat",0);
        lng = intent.getFloatExtra("Long",0);

        confirmationINFO = findViewById(R.id.confirmationinfo);
        confirmationINFO.setText("Uspesno rezerviravte parking mesto vo "+parking+"na"+datum+"vo periodot"+cas);

        navigate = findViewById(R.id.navigate);

        String location = "google.navigation:q="+lat+","+lng;

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(location));

                intent.setPackage("com.google.android.apps.maps");

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }

            }
        });

        moirezervacii = findViewById(R.id.moirezervacii);
        moirezervacii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moirezervaci=new Intent(v.getContext(),MyReservationsActivity.class);
                moirezervaci.putExtra("Korisnik",korisnik);
                moirezervaci.putExtra("Parking",parking);
                moirezervaci.putExtra("Grad",grad);
                moirezervaci.putExtra("Datum",datum);
                moirezervaci.putExtra("Vreme",cas);
                startActivity(moirezervaci);
            }
        });

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
                Toast.makeText(ConfirmationReservationActivity.this,"Moi rezervacii cliked",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}