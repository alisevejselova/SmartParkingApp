package com.example.smartparkingapp;

public class MyReservationsModel {
    private String username;
  // private String city_name;
    private String parking_name;
    private String date;
    private String time;

    public MyReservationsModel( String username, String parking_name, String date, String time) {
        this.username=username;
        this.parking_name = parking_name;
      //  this.city_name = city_name;
        this.date = date;
        this.time = time;
    }


    public String getParkingName () {
        return parking_name;
    }
    public void setParkingName (String parking_name){
        this.parking_name = parking_name;
    }
//    public String getCityName () {
//        return city_name;
//    }
//    public void setCityName (String city_name){
//        this.city_name = city_name;
//    }
    public String getDate () {
        return date;
    }
    public void setDate (String date){
        this.date = date;
    }
    public String getTime () {
        return time;
    }
    public void setTime (String time){
        this.time = time;
    }
}
