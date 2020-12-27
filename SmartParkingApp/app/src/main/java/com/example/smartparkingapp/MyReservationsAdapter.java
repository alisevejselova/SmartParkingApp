package com.example.smartparkingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.viewHolder> {

     List<MyReservationsModel> arrayList;
     Context context;
     DatabaseHelper database;
     Activity activity;


    public MyReservationsAdapter(Context context, Activity activity, List<MyReservationsModel> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;

    }
    @Override
    public MyReservationsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myreservations_list, parent, false);
        return new MyReservationsAdapter.viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView parking;
        public TextView grad;
        public Button button;
        public TextView datum;
        public TextView cas;

        public viewHolder(View itemView) {
            super(itemView);
            grad =itemView.findViewById(R.id.cityname);
            parking = itemView.findViewById(R.id.parkingname);
            button = itemView.findViewById(R.id.navigacija);
            datum = itemView.findViewById(R.id.datum);
            cas = itemView.findViewById(R.id.cas);
        }
    }

    @Override
    public void onBindViewHolder(MyReservationsAdapter.viewHolder holder, int i) {
        MyReservationsModel entry=arrayList.get(i);
        holder.datum.setText(entry.getDate());
        holder.cas.setText(entry.getTime());
        holder.parking.setText(entry.getParkingName());


//        viewHolder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(v.getContext(), ConfirmationReservationActivity.class);
//                intent.putExtra("Parking", entry);
//                intent.putExtra("Korisnik", korisnik);
//                intent.putExtra("Datum", datum);
//                intent.putExtra("Vreme", cas);
//                intent.putExtra("Lat", lat);
//                intent.putExtra("Long", lng);
//                v.getContext().startActivity(intent);
//            }
//        });
}

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
