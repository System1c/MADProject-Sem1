package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity ;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class mainact extends AppCompatActivity {
    BottomNavigationView btmnvm;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    SpaceNavigationView NavigationView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

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
                Intent intent = new Intent(mainact.this,search.class);
                startActivity(intent);;
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch(itemName) {
                    case "Home":
                        Intent intent = new Intent(mainact.this,homefrag.class);
                        startActivity(intent);
                        break;
                    case "Account":
                        Intent intent2 = new Intent(mainact.this,act_accmgmtfrag.class);
                        startActivity(intent2);
                        break;
                    case "Cart":
                        Intent intent3 = new Intent(mainact.this,cartfrag.class);
                        startActivity(intent3);
                        break;
                    case "Contact":
                        Intent intent4 = new Intent(mainact.this,confrag.class);
                        startActivity(intent4);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });
        /*btmnvm = findViewById(R.id.menu);

        btmnvm.setSelectedItemId(R.id.homef);

        btmnvm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homef:
                        startActivity(new Intent(getApplicationContext(),homefrag.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.edprof:
                        startActivity(new Intent(getApplicationContext(),homefrag.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.viewcart:
                        startActivity(new Intent(getApplicationContext(),homefrag.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });*/
    }




}