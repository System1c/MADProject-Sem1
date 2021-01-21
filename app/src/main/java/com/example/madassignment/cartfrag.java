package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class cartfrag extends AppCompatActivity {

    SpaceNavigationView NavigationView;
    private RecyclerView recview;
    private FirebaseFirestore fstore;
    private FirestoreRecyclerAdapter adapter;
    private List<String> itm = new ArrayList<>();
    private int srch, i, t;
    private TextView tot;
    private String tt;
    private  long m;
    private Button clr, ckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cartfrag);
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
                Intent intent = new Intent(cartfrag.this,search.class);
                startActivity(intent);;
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch(itemName) {
                    case "Home":
                        Intent intent = new Intent(cartfrag.this,homefrag.class);
                        startActivity(intent);
                        break;
                    case "Account":
                        Intent intent2 = new Intent(cartfrag.this,act_accmgmtfrag.class);
                        startActivity(intent2);
                        break;
                    case "Cart":
                        Intent intent3 = new Intent(cartfrag.this,cartfrag.class);
                        startActivity(intent3);
                        break;
                    case "Contact":
                        Intent intent4 = new Intent(cartfrag.this,confrag.class);
                        startActivity(intent4);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });


        itm = search.cartstore;
        srch = search.view;
        fstore = FirebaseFirestore.getInstance();
        recview = findViewById(R.id.cart_list);




        if(srch==1) {

                Query query = fstore.collection("Products");
                FirestoreRecyclerOptions<cartq> options = new FirestoreRecyclerOptions.Builder<cartq>().setQuery(query, cartq.class).build();

                adapter = new FirestoreRecyclerAdapter<cartq, cartfrag.ProductsViewHolder>(options) {
                    @NonNull
                    @Override
                    public cartfrag.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
                        return new cartfrag.ProductsViewHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull cartq model) {

                        for(int i2=0; i2<itm.size(); i2++) {
                            String kek = itm.get(i2);
                            if (kek == model.getId()) {
                                holder.l2name.setText(model.getName());
                                holder.l2price.setText(model.getPrice() + " ");
                                Picasso.get().load(model.getImage()).into(holder.l2image);
                                m = model.getPrice();
                                t = (int)m;
                                i = t + i;
                            }
                            tt = String.valueOf(i);
                            tot = findViewById(R.id.total);
                            tot.setText(tt);
                        }
                    }
                };



                recview.setHasFixedSize(true);
                recview.setLayoutManager(new LinearLayoutManager(this));
                recview.setAdapter(adapter);

        }
        else{
            Query query = fstore.collection("Products");
            FirestoreRecyclerOptions<cartq> options = new FirestoreRecyclerOptions.Builder<cartq>().setQuery(query, cartq.class).build();

            adapter = new FirestoreRecyclerAdapter<cartq, cartfrag.ProductsViewHolder>(options) {
                @NonNull
                @Override
                public cartfrag.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
                    return new cartfrag.ProductsViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull cartq model) {

                    for(int i2=0; i2<itm.size(); i2++) {
                        String kek = itm.get(i2);
                        if (kek == model.getId()) {
                            holder.l2name.setText(model.getName());
                            holder.l2price.setText(model.getPrice() + " ");
                            Picasso.get().load(model.getImage()).into(holder.l2image);
                            m = model.getPrice();
                            t = (int)m;
                            i = t + i;
                        }
                        tt = String.valueOf(i);
                        tot = findViewById(R.id.total);
                        tot.setText(tt);
                    }
                }
            };
            recview.setHasFixedSize(true);
            recview.setLayoutManager(new LinearLayoutManager(this));
            recview.setAdapter(adapter);
        }

        ckout = findViewById(R.id.chko);
        ckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itm.clear();
                Intent intent = new Intent(cartfrag.this, checkout_act.class);
                startActivity(intent);
            }
        });

        clr = findViewById(R.id.clrc);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itm.clear();
                finish();
                startActivity(getIntent());
            }
        });


    }
    private class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView l2name;
        private TextView l2price;
        private ImageView l2image;



        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            l2name = itemView.findViewById(R.id.productNameTextView);
            l2price = itemView.findViewById(R.id.productTotalPriceTextView);
            l2image = itemView.findViewById(R.id.productImageView);


        }
    }

        @Override
        protected void onStart() {
            super.onStart();
            adapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            adapter.stopListening();
        }

}


