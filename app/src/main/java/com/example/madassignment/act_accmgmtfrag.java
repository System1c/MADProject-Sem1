package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class act_accmgmtfrag extends AppCompatActivity {


    SpaceNavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_act_accmgmt);
        getSupportActionBar().hide();

        navigationView = (SpaceNavigationView) findViewById(R.id.space);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("Home", R.drawable.dashboard));
        navigationView.addSpaceItem(new SpaceItem("Account", R.drawable.account));
        navigationView.addSpaceItem(new SpaceItem("Cart", R.drawable.cart));
        navigationView.addSpaceItem(new SpaceItem("Contact", R.drawable.contact));
        navigationView.showIconOnly();

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent intent = new Intent(act_accmgmtfrag.this,search.class);
                startActivity(intent);;
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



    }
}