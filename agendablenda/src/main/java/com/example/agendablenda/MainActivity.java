package com.example.agendablenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView thedate;
    private Button btngocalendar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngocalendar = (Button) findViewById(R.id.btngocalendar);

        btngocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });

        Button cancelButton = (Button) findViewById(R.id.doneButton);
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                goToNextActivityBlank(user);
            }
        });

        Button Addbutton = (Button) findViewById(R.id.Addbutton);
        Addbutton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> days = getDays(); // Gets days from the array
                TextView InstructionTextView = (TextView) findViewById(R.id.InstructiontextView); // init all items
                final EditText FirstCourseEditText = (EditText) findViewById(R.id.FirstCourseEditText);
                EditText TimeEditText = (EditText) findViewById(R.id.TimeEditText);
                EditText TimeEndEditText = (EditText) findViewById(R.id.TimeEndEditText);

                String start_time = TimeEditText.getText().toString();  // turns edit string -> string
                String end_time = TimeEndEditText.getText().toString();

                DateFormat sdf = new SimpleDateFormat("hh:mm");

                try {
                    Date stime = sdf.parse(start_time); //turns string -> number -> time
                    Date eTime = sdf.parse(end_time);

                    Schedule personOneCourses = new Schedule(FirstCourseEditText.getText().toString(), // one course, schedule = multiple course
                                                                days,
                                                                new Time(stime.getTime()), //turns number -> time
                                                                new Time(eTime.getTime())

                            );

                    // Call firebase and store the variables once you have access to the user's object
                } catch (ParseException e) {//tests for validity
//                    e.printStackTrace();
                    String errormsg = "You done goofed up, write the write number boi";
                    System.out.println(errormsg);
                }
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                repeatActivity(user);

            }
        }

        );
//

        Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new Button.OnClickListener() {
             public void onClick(View v) {
                 ArrayList<String> days = getDays(); // Gets days from the array
                 TextView InstructionTextView = (TextView) findViewById(R.id.InstructiontextView); // init all items
                 final EditText FirstCourseEditText = (EditText) findViewById(R.id.FirstCourseEditText);
                 EditText TimeEditText = (EditText) findViewById(R.id.TimeEditText);
                 EditText TimeEndEditText = (EditText) findViewById(R.id.TimeEndEditText);

                 String start_time = TimeEditText.getText().toString();  // turns edit string -> string
                 String end_time = TimeEndEditText.getText().toString();

                 DateFormat sdf = new SimpleDateFormat("hh:mm");

                 try {
                     Date stime = sdf.parse(start_time); //turns string -> number -> time
                     Date eTime = sdf.parse(end_time);

                     Schedule personOneCourses = new Schedule(FirstCourseEditText.getText().toString(), // one course, schedule = multiple course
                             days,
                             new Time(stime.getTime()), //turns number -> time
                             new Time(eTime.getTime())

                     );

                     // Call firebase and store the variables once you have access to the user's object
                 } catch (ParseException e) {//tests for validity
//                    e.printStackTrace();
                     String errormsg = "You done goofed up, write the write number boi";
                     System.out.println(errormsg);
                 }
                 mAuth = FirebaseAuth.getInstance();
                 FirebaseUser user = mAuth.getCurrentUser();
                 goToNextActivity(user);

             }
         }

        );

    }



    private void goToNextActivityBlank(FirebaseUser user) {
        Intent intent = new Intent(MainActivity.this, Basic.class);
        startActivity(intent);
    }

    private void goToNextActivity(FirebaseUser user) {
        Intent intent = new Intent(this, Basic.class);
        startActivity(intent);

    }

    private void repeatActivity(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




    private ArrayList<String> getDays() {  //array for all days
        ArrayList<String> days = new ArrayList<>();

        CheckBox monday = (CheckBox) findViewById(R.id.checkBox2);   //if checkbox monday is pressed, add monday to array
        if (monday.isChecked()) {
            days.add("Monday");
        }

        CheckBox tuesday = (CheckBox) findViewById(R.id.checkBox3);
        if (tuesday.isChecked()) {
            days.add("Tuesday");
        }

        CheckBox wednesday = (CheckBox) findViewById(R.id.checkBox4);
        if (wednesday.isChecked()) {
            days.add("Wednesday");
        }

        CheckBox thursday = (CheckBox) findViewById(R.id.checkBox5);
        if (thursday.isChecked()) {
            days.add("Thursday");
        }

        CheckBox friday = (CheckBox) findViewById(R.id.checkBox6);
        if (friday.isChecked()) {
            days.add("Friday");
        }

        return days;
    }
}

