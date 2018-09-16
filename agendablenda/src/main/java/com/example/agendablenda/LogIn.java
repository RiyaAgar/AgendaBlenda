package com.example.agendablenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);

        final TextView messageLog = (TextView) findViewById(R.id.messageLog);

        Button signIn = (Button) findViewById(R.id.signInButton);
        Button signUp = (Button) findViewById(R.id.signUpButton);

        signIn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (email.getText() == null || password.getText() == null) {
                    return;
                }

                String user_email = email.getText().toString();
                String user_password = password.getText().toString();

                login(user_email, user_password, messageLog);

                System.out.println("Sign in the user");
            }
        });

        signUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (email.getText() == null || password.getText() == null) {
                    return;
                }

                String user_email = email.getText().toString();
                String user_password = password.getText().toString();

                createUser(user_email, user_password, messageLog);

                System.out.println("Sign up the user");
            }
        });
    }

    private void goToNextActivity(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void login(String email, String password, final TextView messageLog) {
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user.getUid());
                    messageLog.setText("Login was successful");

                    goToNextActivity(user);
                } else {
                    System.out.println("login failed");
                    System.err.println(task.getException().getMessage());
                    String message = "Login failed: " + task.getException().getMessage();
                    messageLog.setText(message);
                }
            }
        });
    }

    private void createUser(String email, String password, final TextView messageLog) {
        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user.getUid());
                    messageLog.setText("Login was successful");

                    goToNextActivity(user);
                } else {
                    // If sign in fails, display a message to the user.
                    System.out.println("create user failed");
                    System.err.println(task.getException().getMessage());
                    String message = "Create user failed: " + task.getException().getMessage();
                    messageLog.setText(message);
                }
            }
        });
    }
}
