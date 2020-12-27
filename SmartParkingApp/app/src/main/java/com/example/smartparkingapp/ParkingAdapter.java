package com.example.smartparkingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {

    private final List<String> myList;
    private final int rowLayout;
    private final Context mContext;
    private final String cas;
    private final String datum;
    private final String korisnik;
    private final DatabaseHelper database;
    String grad;

    public class ParkingViewHolder extends RecyclerView.ViewHolder {
        public TextView parkingName;
        public Button button;
        public TextView slobodni;
        public TextView zafateni;

        public ParkingViewHolder(View itemView) {
            super(itemView);
            parkingName = itemView.findViewById(R.id.parkingname);
            button = itemView.findViewById(R.id.rezerviraj1);
            slobodni = itemView.findViewById(R.id.slobodni_mesta);
            zafateni = itemView.findViewById(R.id.zafateni_mesta);
        }
    }
    public ParkingAdapter(List<String> myList, int rowLayout, String datum, String cas, String korisnik,  DatabaseHelper database, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.datum = datum;
        this.cas = cas;
        this.korisnik = korisnik;
        this.database = database;
    }

    @Override
    public ParkingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ParkingViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ParkingViewHolder viewHolder, int i) {
        final String entry = myList.get(i);
        int totalSpaces = database.getTotalSpaces(entry);
        final int freeSpaces = totalSpaces - database.getNumberOfReservations(datum, cas, entry);
        int takenSpaces = totalSpaces - freeSpaces;
        final float lat = database.getLat(entry);
        final float lng = database.getLong(entry);

        String takenS = String.valueOf(takenSpaces);
        String freeS = String.valueOf(freeSpaces);

        viewHolder.parkingName.setText(entry);
        viewHolder.zafateni.setText(takenS);
        viewHolder.slobodni.setText(freeS);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(freeSpaces != 0) {
                    //                if(db.existingReservation(username, datum, vreme)) {
//                    Toast.makeText(getActivity(),"Postoi aktivna rezervacija vo izbranoto vreme !",Toast.LENGTH_SHORT).show();
//                } else {
                    database.insertIntoReservations(korisnik, entry, datum, cas);
                    Intent intent = new Intent(v.getContext(), ConfirmationReservationActivity.class);
                    intent.putExtra("Grad", grad);
                    intent.putExtra("Parking", entry);
                    intent.putExtra("Korisnik", korisnik);
                    intent.putExtra("Datum", datum);
                    intent.putExtra("Vreme", cas);
                    intent.putExtra("Lat", lat);
                    intent.putExtra("Long", lng);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }


}

