package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class first_act extends AppCompatActivity
{
         private Button login, register;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.act_init);
            getSupportActionBar().hide();

            register = (Button) findViewById(R.id.register);
            login= (Button) findViewById(R.id.login);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(first_act.this,reg_act.class);
                    startActivity(intent);
                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(first_act.this,login_act.class);
                    startActivity(intent);
                }
            });
        }
}
