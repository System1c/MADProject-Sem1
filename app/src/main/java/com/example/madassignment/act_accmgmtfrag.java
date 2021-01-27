package com.example.madassignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.regex.Pattern;

public class act_accmgmtfrag extends AppCompatActivity {


    SpaceNavigationView navigationView;
    private FirebaseAuth mAuth;
    EditText email, fname, fp, sp;
    FirebaseFirestore fstore;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Button chdet, lgo;
    int err=0;
    ProgressDialog nDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_act_accmgmt);
        getSupportActionBar().hide();

        nDialog = new ProgressDialog(act_accmgmtfrag.this);
        nDialog.setMessage("Connecting to Firebase");
        nDialog.setTitle("Getting Account Info");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();


        navigationView = (SpaceNavigationView) findViewById(R.id.space);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("Home", R.drawable.dashboard));
        navigationView.addSpaceItem(new SpaceItem("Account", R.drawable.account));
        navigationView.addSpaceItem(new SpaceItem("Cart", R.drawable.cart));
        navigationView.addSpaceItem(new SpaceItem("Contact", R.drawable.contact));
        navigationView.showIconOnly();

        mAuth = FirebaseAuth.getInstance();

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent intent = new Intent(act_accmgmtfrag.this,search.class);
                startActivity(intent);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch(itemName) {
                    case "Home":
                        Intent intent = new Intent(act_accmgmtfrag.this,homefrag.class);
                        startActivity(intent);
                        break;
                    case "Account":
                        Intent intent2 = new Intent(act_accmgmtfrag.this,act_accmgmtfrag.class);
                        startActivity(intent2);
                        break;
                    case "Cart":
                        Intent intent3 = new Intent(act_accmgmtfrag.this,cartfrag.class);
                        startActivity(intent3);
                        break;
                    case "Contact":
                        Intent intent4 = new Intent(act_accmgmtfrag.this,confrag.class);
                        startActivity(intent4);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });

        email = findViewById(R.id.editemail);
        fname = findViewById(R.id.fname);
        fp = findViewById(R.id.pw1);
        sp = findViewById(R.id.pw2);




        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("lookgood--mad").child(uid);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {



            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("fname").getValue(String.class);
                    fname.setText(name);
                    String email1 = dataSnapshot.child("email").getValue(String.class);
                    email.setText(email1);
                    nDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            uidRef.addListenerForSingleValueEvent(eventListener);

        }
        else if(user == null){

            Toast.makeText(act_accmgmtfrag.this, "Please Login",Toast.LENGTH_LONG).show();
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

                    nDialog = new ProgressDialog(act_accmgmtfrag.this);
                    if(sp == null || fp == null) {
                        verifyfields();
                    }
                    else {



                        rootRef.child("lookgood--mad").child(uid).child("fname").setValue(sfn);
                        rootRef.child("lookgood--mad").child(uid).child("email").setValue(sem);
                            rootRef.child("lookgood--mad").child(uid).child("password").setValue(ssp);

                        user.updateEmail(sem);
                        user.updatePassword(ssp);
                        Toast.makeText(act_accmgmtfrag.this, "Successfully Changed Account Information",Toast.LENGTH_LONG).show();
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


        lgo = findViewById(R.id.logout);
        lgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(act_accmgmtfrag.this, first_act.class);
                startActivity(intent);
                finish();
            }
        });



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