package com.example.final_project_attempt1;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class today extends AppCompatActivity {
    private static final String TAG = "ListDataActivity"; //Terminal will show lines with
    //ListDataActivity for easy spotting.
    Holder mDatabaseHelper; //Database object from holder class
    private ListView mListView; //Listview variable

    @Override
    protected void onCreate(@Nullable Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.today);
        mListView = (ListView) findViewById(R.id.listFood); //listview variable
        mDatabaseHelper = new Holder(this); //Database class object

        populateListView(); //Calls method to fill listview with the database
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying database in the ListView."); //Shows in terminal
        // to let you know that it is working
        Cursor data = mDatabaseHelper.getData(); //cursor data to share from the database in Holder class.
        ArrayList<String> listData = new ArrayList<>(); //Array list to hold the calorie values.
        while(data.moveToNext()){
            listData.add(data.getString(1)); //this will iterate through the database and add
        }                                       //to listview until filled.
        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.textviewlist, listData);
        mListView.setAdapter(adapter); //these two lines help adapt the data from database to listview
    }

    private void toastMessage(String message){ //So I dont have to type it again and again.
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

//////////NOTES\\\\\\\\\\\\\
/*Might need to duplicate so that we can accomodate for food name inputs
* calories may or may not always take.*/