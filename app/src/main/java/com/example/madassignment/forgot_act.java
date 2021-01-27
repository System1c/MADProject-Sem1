package com.example.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class forgot_act extends AppCompatActivity  {

    EditText email;
    int err;
    ImageButton chdet;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_act);
        getSupportActionBar().hide();

        email = findViewById(R.id.fogemail);
        chdet = findViewById(R.id.fogchadet);


        mAuth = FirebaseAuth.getInstance();

        chdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.sendPasswordResetEmail(String.valueOf(email))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgot_act.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(forgot_act.this, login_act.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(forgot_act.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }


    /*private void fieldver() {

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verifyfields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private void verifyfields() {
        String em = email.getText().toString().trim();

        if (!validEmail(em)) {
            email.setError("Please Enter A Valid E-mail");
            email.requestFocus();
            err=1;
            return;
        }
        else{
            err=0;
        }
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }*/


}