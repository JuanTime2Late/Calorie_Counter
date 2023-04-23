package com.example.final_project_attempt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Holder extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper"; //Tag for easy spotting on terminal

    private static final String food_table = "food_table"; //Food_table is the name for the list
    private static final String COL0 = "FOOD"; //Food to hold the name for the list column no workng
    private static final String COL1 = "CALORIES"; //Name of the column for calories in the table


    public Holder(Context context){
        super(context, food_table, null, 1);
    } //constructor

    @Override
    public void onCreate(SQLiteDatabase db) { //Create a table with the name food_table only input is calories
        String createTable = "CREATE TABLE " + food_table + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  COL1 + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + food_table); //If the table exists drop and make new.
        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase(); //identifier for database
        ContentValues contentValues = new ContentValues(); //value holder
        contentValues.put(COL1, item); //Place the new calorie in this variable
        Log.d(TAG, "addData: Adding " + item + " to " + food_table); //Tag to show that it is working in terminal
        long result = db.insert(food_table, null, contentValues); //insert the calorie amount to the list

        if(result == -1) { //will return false if data enters incorrectly
            return false;
        }
        else {//will return true if data enters correctly
            return true;
        }
    }

    //returns data from database
    public Cursor getData(){ //gets data from table/list food_table
        SQLiteDatabase db = this.getWritableDatabase(); //Database identifier
        String query = "SELECT * FROM " + food_table; //Used for iterating in main
        Cursor data = db.rawQuery(query, null); //basically points to a variable in the list
        return data; //returns the variable being pointed at.
    }
}
