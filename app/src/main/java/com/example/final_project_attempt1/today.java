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
    private static final String TAG = "ListDataActivity";
    Holder mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.today);
        mListView = (ListView) findViewById(R.id.listFood);
        mDatabaseHelper = new Holder(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying database in the ListView.");
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

