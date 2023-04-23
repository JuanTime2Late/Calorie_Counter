package com.example.final_project_attempt1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3;
    EditText food, cals;
    Holder mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////Date Config\\\\\\\\\\\\\\\\
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        //Date TextView
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
        /////////////Goal Display\\\\\\\\\\\\\\\\
        tv3 = findViewById(R.id.total);
        tv2 = findViewById(R.id.textView7);
        tv1 = findViewById(R.id.textView6);
        if(getIntent().getStringExtra("message") != ""){
            tv1.setText(getIntent().getStringExtra("message"));
        }

         //receive from goal class
        /////////////Goal Button\\\\\\\\\\\\\\\\
        Button btn1 = findViewById(R.id.GoalButton);
        btn1.setOnClickListener(v -> openGoal());

        /////////////Enter Food and Calories\\\\\\\\\\\\\\
        cals = findViewById(R.id.Calories);
        food = findViewById(R.id.Food);
        Button btn2 = findViewById(R.id.submit1);
        Button btn3 = findViewById(R.id.button2);
        mDatabaseHelper = new Holder(this);

        btn2.setOnClickListener(view -> {
            String newEntry = cals.getText().toString();
            if(cals.length() != 0 && food.length() != 0) {
                if(!tv1.getText().toString().isEmpty() && !(tv2.getText().toString().isEmpty())) {
                    int eat = Integer.parseInt(tv2.getText().toString());
                    int cal = Integer.parseInt(cals.getText().toString());
                    int var1 = eat + cal;
                    tv2.setText(Integer.toString(var1));
                    int num = Integer.parseInt(tv1.getText().toString());
                    int var = num - var1;
                    tv3.setText(Integer.toString(var));
                }
                AddData(newEntry);
                cals.setText("");
            } else { toastMessage("Either Food or Calories is empty.");}


        });

        btn3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, today.class);
                startActivity(intent);
        });
    }

    public void AddData(String entry) {
        boolean insertData = mDatabaseHelper.addData(entry);

        if(insertData) {
            toastMessage("Data Successfully Inserted!");
        }
        else {
            toastMessage("smn wrong");
        }
    }

    private void toastMessage(String s) {
        Toast.makeText(this,s , Toast.LENGTH_SHORT).show();
    }

    public void openGoal(){
        String input = tv1.getText().toString();
        Intent intent = new Intent(this, Goal.class);
        startActivity(intent);
        Intent intent2 =new Intent(MainActivity.this, Goal.class);
        intent2.putExtra("message", input);
        startActivity(intent2);
    }
}