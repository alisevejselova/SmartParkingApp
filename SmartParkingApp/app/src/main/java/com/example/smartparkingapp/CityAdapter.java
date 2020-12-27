package com.example.smartparkingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{
    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;

    private final List<String> myList;
    private final int rowLayout;
    private final Context mContext;
    String username;



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityName;
        public Button rezerviraj;

        public ViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityname);
            rezerviraj = itemView.findViewById(R.id.rezerviraj);

        }
    }

    // конструктор
    public CityAdapter(List<String> myList, int rowLayout,String username, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.username=username;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
         String entry = myList.get(i);
        viewHolder.cityName.setText(entry);
        viewHolder.rezerviraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReservationActivity.class);
                TextView text = viewHolder.cityName;
                String message = text.getText().toString();
                intent.putExtra("City", message);
                intent.putExtra("Username",username);
                mContext.startActivity(intent);

            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

}
