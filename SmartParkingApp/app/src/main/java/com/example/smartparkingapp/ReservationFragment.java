package com.example.smartparkingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ReservationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    public ReservationFragment() {
        // Required empty public constructor
    }
   String vreme,city,username;
    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent  = getActivity().getIntent();
        TextView text = getActivity().findViewById(R.id.cityinfo);
         city = intent.getStringExtra("City");
         username= intent.getStringExtra("Username");
        text.setText("Izbran grad :"+city);




        TextView displaydate = getActivity().findViewById(R.id.date);
        displaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDatePickerDialog();
            }
        });


        Spinner spinner = getActivity().findViewById(R.id.spinner_dialog);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 vreme= (String) spinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            // show in same activity
            ReservationFragment2 frag = (ReservationFragment2) getFragmentManager().findFragmentById(R.id.fragment2);

        } else {}


        Button rezerviraj = getActivity().findViewById(R.id.rezerviraj1);
        rezerviraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView displaydate = getActivity().findViewById(R.id.date);
                String datum = (String) displaydate.getText();
//                if(db.existingReservation(username, datum, vreme)) {
//                    Toast.makeText(getActivity(),"Postoi aktivna rezervacija vo izbranoto vreme !",Toast.LENGTH_SHORT).show();
//                } else {
//                if(db.numberReservations(username)) {
//                    Toast.makeText(getActivity(), "Vekje imate 3 aktivni rezervacii!", Toast.LENGTH_LONG).show();
//                }else {
                Intent intent = new Intent(v.getContext(), ParkingActivity.class);
                    intent.putExtra("Korisnik", username);
                    intent.putExtra("Grad", city);
                    intent.putExtra("Datum", datum);
                    intent.putExtra("Cas", vreme);
                    startActivity(intent);
           //    }
           }
        });

    }


    private  void showDatePickerDialog(){
        DatePickerDialog dialog = new DatePickerDialog( getActivity(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month+1;
        String date = dayOfMonth + "/" + month + "/" + year ;
        TextView displaydate = getActivity().findViewById(R.id.date);
        displaydate.setText(date);
    }

}