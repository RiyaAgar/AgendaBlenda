package com.example.agendablenda;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.stream.Collectors;

public class Calculate extends AppCompatActivity {
    private FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Intent i = getIntent();
        db = FirebaseFirestore.getInstance();
        String emailemail = i.getStringExtra("email");

        Spinner UserinfoSpinner = (Spinner) findViewById(R.id.UserinfoSpinner);
        Task<QuerySnapshot> future = db.collection("users").get();

        List<FirebaseUser> users = future
                .getResult()
                .getDocuments()
                .stream()
                .map(docs -> docs.toObject(FirebaseUser.class))
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, users);


    }

}
