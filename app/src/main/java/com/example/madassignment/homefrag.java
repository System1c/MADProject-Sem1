package com.example.madassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import static javax.xml.xpath.XPathFactory.newInstance;

public class homefrag extends AppCompatActivity implements View.OnClickListener {
    public static String cat;
    private FirebaseFirestore fstore;
    private FirestoreRecyclerAdapter adapter;

    ImageButton male, female, shoe, pfume, watch, deal;


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
                Intent intent = new Intent(homefrag.this, search.class);
                startActivity(intent);
                ;
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch (itemName) {
                    case "Home":
                        Intent intent = new Intent(homefrag.this, homefrag.class);
                        startActivity(intent);
                        break;
                    case "Account":
                        Intent intent2 = new Intent(homefrag.this, act_accmgmtfrag.class);
                        startActivity(intent2);
                        break;
                    case "Cart":
                        Intent intent3 = new Intent(homefrag.this, cartfrag.class);
                        startActivity(intent3);
                        break;
                    case "Contact":
                        Intent intent4 = new Intent(homefrag.this, confrag.class);
                        startActivity(intent4);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        shoe = findViewById(R.id.shoe);
        pfume = findViewById(R.id.perfume);
        watch = findViewById(R.id.watch);
        deal = findViewById(R.id.deals);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        shoe.setOnClickListener(this);
        pfume.setOnClickListener(this);
        watch.setOnClickListener(this);
        deal.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.male:
                cat = "male";
                Intent intent = new Intent(homefrag.this,search.class);
                startActivity(intent);
                break;
            case R.id.female:
                cat = "female";
                Intent intent2 = new Intent(homefrag.this,search.class);
                startActivity(intent2);
                break;
            case R.id.shoe:
                cat = "shoe";
                Intent intent3 = new Intent(homefrag.this,search.class);
                startActivity(intent3);
                break;
            case R.id.perfume:
                cat = "access";
                Intent intent4 = new Intent(homefrag.this,search.class);
                startActivity(intent4);
                break;
            case R.id.watch:
                cat = "watch";
                Intent intent5 = new Intent(homefrag.this,search.class);
                startActivity(intent5);
                break;
            case R.id.deals:
                cat = "deals";
                Intent intent6 = new Intent(homefrag.this,search.class);
                startActivity(intent6);
                break;
        }
    }
}