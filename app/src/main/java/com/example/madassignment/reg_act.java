package com.example.madassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class reg_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        getSupportActionBar().hide();
    }
}
