package com.example.final_project_attempt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Holder extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String food_table = "food_table";
    private static final String COL0 = "FOOD";
    private static final String COL1 = "CALORIES";


    public Holder(Context context){
        super(context, food_table, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + food_table + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  COL1 + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + food_table);
        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        Log.d(TAG, "addData: Adding " + item + " to " + food_table);
        long result = db.insert(food_table, null, contentValues);

        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //returns data from database
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + food_table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
