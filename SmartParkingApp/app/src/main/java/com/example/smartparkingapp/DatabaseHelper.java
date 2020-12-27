package com.example.smartparkingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 ="license_plate";



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 5);
    }

    //List <Cities> citiesList = new ArrayList<>();
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser " + "(ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, license_plate TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE Reservations(username VARCHAR, parkingname VARCHAR, date VARCHAR, timeslot VARCHAR);");
        sqLiteDatabase.execSQL("CREATE TABLE ParkingPlaces(name VARCHAR, city VARCHAR, totalSpaces INTEGER, lat FLOAT, long FLOAT);");

        sqLiteDatabase.execSQL("INSERT INTO ParkingPlaces VALUES ('Gama Oil Valandovo', 'Valandovo', 20, 41.3171604, 22.5877705);");
        sqLiteDatabase.execSQL("INSERT INTO ParkingPlaces VALUES ('Centar Valandovo', 'Valandovo', 50, 41.31744, 22.56002);");
        sqLiteDatabase.execSQL("INSERT INTO ParkingPlaces VALUES ('Sportska Sala Valandovo', 'Valandovo', 60, 41.3162943733, 22.5605741415);");
        sqLiteDatabase.execSQL("INSERT INTO ParkingPlaces VALUES ('Ramada Plaza Princess Hotel', 'Gevgelija', 80,41.186443, 22.501638);");
        sqLiteDatabase.execSQL("INSERT INTO ParkingPlaces VALUES ('Appolonia', 'Gevgelija', 70,41.1409442, 22.5039965);");
        sqLiteDatabase.execSQL("INSERT INTO ParkingPlaces VALUES ('Parking Stopanska', 'Gevgelija', 20,41.1403092437, 22.501545223);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ParkingPlaces");
        onCreate(sqLiteDatabase);
    }

    public void insertIntoReservations(String username, String parkingname, String date, String timeslot) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("parkingname", parkingname);
        contentValues.put("date", date);
        contentValues.put("timeslot", timeslot);
        database.insert("Reservations", null, contentValues);
    }
public List<String> getParkings (String city) {
    List<String> parkingNames = new ArrayList();
    SQLiteDatabase database = this.getReadableDatabase();
    Cursor c1 = database.rawQuery("SELECT * FROM ParkingPlaces WHERE city LIKE '" + city + "'", null);
    while(c1.moveToNext()) {
        parkingNames.add(c1.getString(0));
    }
    c1.close();
    return parkingNames;
}
    public int getTotalSpaces(String name) {
        int total = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM ParkingPlaces WHERE name LIKE '" + name + "'", null);
        if(c1.moveToFirst()) {
            total = c1.getInt(2);
            c1.close();
            return total;
        } else {
            return 0;
        }
    }
    public int getNumberOfReservations(String date, String time, String name) {
        int count = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM Reservations WHERE date = '" + date + "' AND timeslot = '" + time + "' AND parkingname = '" + name + "'" , null);
        if(c1.moveToFirst()) {
            count = c1.getCount();
            c1.close();
            return count;
        } else {
            return 0;
        }
    }
//    public boolean isEmpty(String TableName){
//
//        SQLiteDatabase database = this.getReadableDatabase();
//        long NoOfRows = DatabaseUtils.queryNumEntries(database,TableName);
//
//        if (NoOfRows == 0){
//            return true;
//        } else {
//            return false;
//        }
//    }
    public boolean existingReservation (String username, String date, String timeslot) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM Reservations WHERE date = '" + date + "' AND timeslot = '" + timeslot + "' AND username = '" + username + "'" , null);
        return c1.moveToFirst();
    }
    public float getLat(String parkingname) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM ParkingPlaces WHERE name LIKE '" + parkingname + "'", null);
        if(c1.moveToFirst()) {
            return c1.getFloat(3);
        } else {
            return 0;
        }
    }
    public float getLong(String parkingname) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM ParkingPlaces WHERE name LIKE '" + parkingname + "'", null);
        if(c1.moveToFirst()) {
            return c1.getFloat(4);
        } else {
            return 0;
        }
    }

//dodadi korisnik vo baza
    public long addUser(String user, String password,String license_plate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("license_plate",license_plate);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }
// proverka pri login , dali korisnikot se naoga vo baza
    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?" ;
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }


}
