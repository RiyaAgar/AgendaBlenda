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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    public static final String USER_KEY = "user";
    public static final String PASS_KEY = "password";
    String user_email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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

                user_email = email.getText().toString();
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



    private void login(final String email, final String password, final TextView messageLog) {
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user.getUid());
                    messageLog.setText("Login was successful");

                    DocumentReference loginInfo = FirebaseFirestore.getInstance().document(email);


                    Map<String, Object> logins = new HashMap<String, Object>();
                    logins.put(USER_KEY, email);
                    logins.put(PASS_KEY, password);

                    bringOverLogin(loginInfo);
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

    private void bringOverLogin(DocumentReference loginInfo) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", user_email);
        startActivity(intent);
    }

    private void createUser(final String email, final String password, final TextView messageLog) {
        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user.getUid());
                    messageLog.setText("Login was successful");

                    DocumentReference loginInfo = FirebaseFirestore.getInstance().document(email);


                    Map<String, Object> logins = new HashMap<String, Object>();
                    logins.put (USER_KEY, email);
                    logins.put (PASS_KEY, password);

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
