package com.example.agendablenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseUser;


public class Basic extends AppCompatActivity{
    private static final String TAG = "Basic";

//    private TextView welcomeText;
//    private Button addClass;
//    private Button editClass;
//    private Button compareSchedule;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        final Button addClass = (Button) findViewById(R.id.addClass);
        final Button editClass = (Button) findViewById(R.id.editClass);
        final Button compareSchedule = (Button) findViewById(R.id.compareSchedule);

        //setContentView(R.layout.RelativeLayout); -------------------------------------------------IDK WHAT THIS IS
        //TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, user!");
        addClass.setText("Add a class");
        editClass.setText("Edit a class");
        compareSchedule.setText("Compare Classes");

        addClass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Basic.this, MainActivity.class));
            }
        });

        editClass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(Basic.this, MainActivity.class));
            }
        });

        compareSchedule.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(Basic.this, MainActivity.class));
            }
        });
    }

    private void goToNextActivity(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
