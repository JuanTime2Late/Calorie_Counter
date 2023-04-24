package com.example.final_project_attempt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3; //Declaring buttons
    EditText food, cals; //Declaring input boxes
    Holder mDatabaseHelper; //Declaring Database class object

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
        tv3 = findViewById(R.id.total); //TextView variable for Total calories.
        tv2 = findViewById(R.id.textView7); //Number of Eaten calories.
        tv1 = findViewById(R.id.textView6); //Number of Calories for goal.
        if(getIntent().getStringExtra("message") != ""){ //This receives the goal amount from Goal.java
            tv1.setText(getIntent().getStringExtra("message"));
        }
        /////////////Goal Button\\\\\\\\\\\\\\\\
        Button btn1 = findViewById(R.id.GoalButton); //Btn1 is for Change Goal
        btn1.setOnClickListener(v -> openGoal()); //On click it will go to openGoal(see below)

        /////////////Enter Food and Calories\\\\\\\\\\\\\\
        cals = findViewById(R.id.Calories); //Variable cals finds input from Enter Calories box
        food = findViewById(R.id.Food); //Variable food find input from Enter food box.
        Button btn2 = findViewById(R.id.submit1); //Button for submitting food and calories
        Button btn3 = findViewById(R.id.button2); //Button for Today's Calories
        mDatabaseHelper = new Holder(this); // object initialized with new Database class obj

         btn2.setOnClickListener(view -> { //On click it will do the next
            String calories = cals.getText().toString(); // Declaring variable to hold calorie input
            String food1 = food.getText().toString(); //Declaring variable to hold food input.
            if(cals.length() != 0 && food.length() != 0 && tv1.length() != 0) { //
                if(!tv1.getText().toString().isEmpty() && !(tv2.getText().toString().isEmpty())) {
                    int eat = Integer.parseInt(tv2.getText().toString());
                    int cal = Integer.parseInt(cals.getText().toString());
                    int var1 = eat + cal; //adds calories to existing Eaten calories
                    tv2.setText(Integer.toString(var1));//sets Eaten to the total
                    int num = Integer.parseInt(tv1.getText().toString());
                    int var = num - var1; //Subtracts the Eaten total from the goal(can go negative)
                    tv3.setText(Integer.toString(var)); //sets Total subtraction to the remaining.
                }
                int totals = 35, i;
                int total1 = (food.getText().length() + cals.getText().length());
                int uses = totals - total1;
                StringBuilder spaces = new StringBuilder("");
                for(i = 0; i <= uses; ++i){
                       spaces.append(".");
                }
                String together = food1.toUpperCase() + spaces + calories; //Concatenate Both
                AddData(together); //adds to the list the concatenated food and calorie amount
                cals.setText(""); //After submission edittext is emptied again.
                food.setText(""); //After submission edittext is emptied again.
            } else if (tv1.length() == 0) { //Asks the user for the goal to be set first.
                toastMessage("Please enter a goal first.");
            } else { toastMessage("Either Food or Calories is empty.");} //else tell the user one
                                                                        //of the fields is empty.
        });

        btn3.setOnClickListener(view -> { //Opens today's Calories activity (today.java and xml)
            Intent intent = new Intent(MainActivity.this, today.class);
                startActivity(intent);
        });
    }

    public void AddData(String entry) { //Adds the calories to the list
        boolean insertData = mDatabaseHelper.addData(entry); //This will insert data to list
        //and make sure it is done correctly through boolean
        if(insertData) { //If the data is entered correctly
            toastMessage("Data Successfully Inserted!"); //Display to the user success.
        }
        else {
            toastMessage("smn wrong"); //Display to the user that data wasn't entered correctly.
        }
    }

    private void toastMessage(String s) { //Method so I dont have to retype it all the time.
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void openGoal(){ //Function to open Goal activity
        String input = tv1.getText().toString(); //Input takes the string from goal calories
        //Intent intent = new Intent(this, Goal.class); //receives from goal current goal
        //startActivity(intent);
        Intent intent2 =new Intent(MainActivity.this, Goal.class); //sends current goal
        intent2.putExtra("message", input);                               //to goal activity
        startActivity(intent2);
    }
}

///////////////////////////////NOTES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
/* Back button in action bar is not to be used. A lot of the functions to work on it are deprecated
   DO NOT ADD a back button to the UI the user should already have some type of back button.
   Use emulator Pixel 6 Pro API 30 for best results.

   Known errors:
   * Calories don't show result when goal is not set.

   Fixed:
   * Default goal not set. User has to input a goal before subtraction and result are made.
   FIXED = goal
   * Food input and list output not implemented FIX Concatenated when inputs were put in. Fix can
   be found in lines 63 and 64.
   * Database for calories
   * today's calories does not crash anymore (found that integers were being output and it
   expects a string.)
   * Change goal keeps the current goal and can go back without it changing.
   * Calorie and food entries are cleared after submission. (EditTextHere.setText("");)
   * Added messages to methods to easier identify what method causes the crashing (keep an eye on
   getData)

 */
