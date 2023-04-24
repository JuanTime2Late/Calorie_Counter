package com.example.final_project_attempt1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class Goal extends AppCompatActivity {
    EditText edt1;
    Button btn1;
    TextView tv1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        /////////////Date Config\\\\\\\\\\\\\\\\
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        //Date TextView
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        /////////New goal Submission\\\\\\\\\
        edt1 = (EditText) findViewById(R.id.editTextNumber); //textbox to enter new calorie goal
        btn1 = (Button) findViewById(R.id.Submit_goal); //Button to submit goal
        tv1 = (TextView) findViewById(R.id.CurrGoal); //Show the current goal received from main.
        //Goal Display
        tv1.setText(getIntent().getStringExtra("message")); //receive from main new goal.



        btn1.setOnClickListener(new View.OnClickListener() { //Submit button pressed.
            @Override
            public void onClick(View view) { //When button is pressed
                String input = edt1.getText().toString(); //take input from new goal
                if(!TextUtils.isEmpty(input)) { //if input is not empty
                    tv1.setText(input); //set current goal to the new number
                    Intent intent = new Intent(Goal.this, MainActivity.class);
                    intent.putExtra("message", input); //send to the goal new number
                    startActivity(intent);
                }
                else{ //If it is empty and the button is pressed ask the user for an input again.
                        Toast.makeText(Goal.this, "Please enter an amount.",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}