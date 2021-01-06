package com.example.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;



public class login_act extends AppCompatActivity {

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        getSupportActionBar().hide();

        login= (Button)findViewById(R.id.proceed);

        login.setOnClickListener(v -> {
            Intent intent = new Intent(login_act.this, mainact.class);
            startActivity(intent);
        });
    }
}