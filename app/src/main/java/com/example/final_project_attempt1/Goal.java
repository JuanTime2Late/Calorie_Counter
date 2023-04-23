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

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

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
        edt1 = (EditText) findViewById(R.id.editTextNumber);
        btn1 = (Button) findViewById(R.id.Submit_goal);
        tv1 = (TextView) findViewById(R.id.CurrGoal);
        //Goal Display
        tv1.setText(getIntent().getStringExtra("message"));



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edt1.getText().toString();
                if(!TextUtils.isEmpty(input)) {
                    tv1.setText(input);
                    Intent intent = new Intent(Goal.this, MainActivity.class);
                    intent.putExtra("message", input);
                    startActivity(intent);
                }
                else{
                        Toast.makeText(Goal.this, "Please Enter an Amount.",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}