package com.example.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class login_act extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button login;
    EditText lem, lpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        lem = findViewById(R.id.email);
        lpw = findViewById(R.id.password);


        login= (Button)findViewById(R.id.proceed);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        logUser();
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void logUser(){
        String pem = lem.getText().toString().trim();
        String ppw = lpw.getText().toString().trim();

        if (pem.isEmpty()) {
            lem.setError("E-mail cannot be empty");
            lem.requestFocus();
            return;
        }
        if (ppw.isEmpty()) {
            lpw.setError("Password cannot be empty");
            lpw.requestFocus();
            return;
        }

        if (!validEmail(pem)) {
            lem.setError("Please Enter A Valid E-mail");
            lem.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(pem, ppw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login_act.this, "User Logged In",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(login_act.this, mainact.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login_act.this, "Invalid Credentials",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
    }
}

