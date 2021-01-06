package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class reg_act extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText regem, regpw, regpw2;
    Button reg, bk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        reg = findViewById(R.id.registerbut);
        reg.setOnClickListener(this);

        bk = findViewById(R.id.back);
        bk.setOnClickListener(this);

        regem = findViewById(R.id.regEmail);
        regpw = findViewById(R.id.regPass);
        regpw2 = findViewById(R.id.regPass2);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(reg_act.this, first_act.class);
                startActivity(intent);
                break;
            case R.id.registerbut:
                regUser();
                break;
        }
    }
    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void regUser() {
        String em = regem.getText().toString().trim();
        String pw = regpw.getText().toString().trim();
        String pw2 = regpw2.getText().toString().trim();

        if (em.isEmpty()) {
            regem.setError("E-mail cannot be empty");
            regem.requestFocus();
            return;
        }
        if (pw.isEmpty()) {
            regpw.setError("Password cannot be empty");
            regpw.requestFocus();
            return;
        }
        if (pw2.isEmpty()) {
            regpw2.setError("Password cannot be empty");
            regpw2.requestFocus();
            return;
        }
        if (!validEmail(em)) {
            regem.setError("Please Enter A Valid E-mail");
            regem.requestFocus();
            return;
        }
        if (pw.length() < 6) {
            regpw.setError("Password is too short");
            regpw.requestFocus();
            return;
        }
        if (pw2.length() < 6) {
            regpw2.setError("Password is too short");
            regpw2.requestFocus();
            return;
        }
        if (!pw2.equals(pw)){
            regpw2.setError("Passwords do not match");
            regpw2.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(em, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           userfb user = new userfb(em, pw);
                            FirebaseDatabase.getInstance().getReference("lookgood--mad").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(reg_act.this, "User Registered",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(reg_act.this,"Failed to Register",Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                        } else {
                            Toast.makeText(reg_act.this,"Failed to Register",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });



    }

}