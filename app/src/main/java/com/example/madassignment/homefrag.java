package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class homefrag extends AppCompatActivity {


    SpaceNavigationView NavigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_homefrag);
        getSupportActionBar().hide();


        NavigationView = (SpaceNavigationView) findViewById(R.id.space);
        NavigationView.initWithSaveInstanceState(savedInstanceState);
        NavigationView.addSpaceItem(new SpaceItem("Home", R.drawable.dashboard));
        NavigationView.addSpaceItem(new SpaceItem("Account", R.drawable.account));
        NavigationView.addSpaceItem(new SpaceItem("Cart", R.drawable.cart));
        NavigationView.addSpaceItem(new SpaceItem("Contact", R.drawable.contact));
        NavigationView.showIconOnly();

        NavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent intent = new Intent(homefrag.this,search.class);
                startActivity(intent);;
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch(itemName) {
                    case "Home":
                        Intent intent = new Intent(homefrag.this,homefrag.class);
                        startActivity(intent);
                        break;
                    case "Account":
                        Intent intent2 = new Intent(homefrag.this,act_accmgmtfrag.class);
                        startActivity(intent2);
                        break;
                    case "Cart":
                        Intent intent3 = new Intent(homefrag.this,cartfrag.class);
                        startActivity(intent3);
                        break;
                    case "Contact":
                        Intent intent4 = new Intent(homefrag.this,confrag.class);
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