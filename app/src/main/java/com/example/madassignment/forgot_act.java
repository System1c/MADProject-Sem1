package com.example.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class forgot_act extends AppCompatActivity {

    EditText email, fname, fp, sp;
    int err;
    ImageButton chdet;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_act);
        getSupportActionBar().hide();
        email = findViewById(R.id.editemail);
        fname = findViewById(R.id.fname);
        fp = findViewById(R.id.pw1);
        sp = findViewById(R.id.pw2);

        mAuth = FirebaseAuth.getInstance();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("lookgood--mad").child(uid);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {


            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("fname").getValue(String.class);
                    fname.setText(name);
                    String email1 = dataSnapshot.child("email").getValue(String.class);
                    email.setText(email1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            uidRef.addListenerForSingleValueEvent(eventListener);

        }
        fieldver();
        chdet = findViewById(R.id.chadet);
        if(err==0) {

            chdet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyfields();
                    String sem = email.getText().toString();
                    String sfn = fname.getText().toString();
                    String ssp = sp.getText().toString();
                    if(sp == null || fp == null) {
                        verifyfields();
                    }
                    else {
                        rootRef.child("lookgood--mad").child(uid).child("fname").setValue(sfn);
                        rootRef.child("lookgood--mad").child(uid).child("email").setValue(sem);
                        rootRef.child("lookgood--mad").child(uid).child("password").setValue(ssp);

                    }
                }
            });
        }
        else if(err==1){
            chdet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyfields();
                    Toast.makeText(act_accmgmtfrag.this, "Please Verify Info",Toast.LENGTH_LONG).show();
                    err=0;
                }
            });
        }



    }

    private void fieldver() {
        sp.addTextChangedListener(new TextWatcher() {
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

        fp.addTextChangedListener(new TextWatcher() {
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
        String pw = fp.getText().toString().trim();
        String pw2 = sp.getText().toString().trim();

        if (pw.isEmpty()) {
            fp.setError("Password cannot be empty");
            err=1;
            return;
        }
        if (pw2.isEmpty()) {
            sp.setError("Password cannot be empty");
            err=1;
            return;
        }
        if (!validEmail(em)) {
            email.setError("Please Enter A Valid E-mail");
            email.requestFocus();
            err=1;
            return;
        }
        if (pw.length() < 6) {
            fp.setError("Password is too short");
            err=1;
            return;
        }
        if (pw2.length() < 6) {
            sp.setError("Password is too short");
            err=1;
            return;
        }
        if (!pw2.equals(pw)){
            sp.setError("Passwords do not match");
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
    }
}