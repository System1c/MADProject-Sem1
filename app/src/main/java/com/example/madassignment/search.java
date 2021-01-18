package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class search extends AppCompatActivity {
    private EditText sfield;
    private ImageButton imgbtn;
    private RecyclerView reslist;
    SpaceNavigationView NavigationView;
    private FirebaseFirestore fstore;
    private FirestoreRecyclerAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);
        getSupportActionBar().hide();

        sfield = findViewById(R.id.search_field);
        imgbtn = findViewById(R.id.search_btn);

        reslist = findViewById(R.id.result_list);

        fstore = FirebaseFirestore.getInstance();
        reslist = findViewById(R.id.result_list);

        Query query = fstore.collection("Products");
        FirestoreRecyclerOptions<prodmod> options = new FirestoreRecyclerOptions.Builder<prodmod>().setQuery(query, prodmod.class).build();

        adapter = new FirestoreRecyclerAdapter<prodmod, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull prodmod model) {
                holder.lname.setText(model.getName());
                holder.lprice.setText(model.getPrice() +" ");
                holder.ldesc.setText(model.getDescription());
            }
        };
        reslist.setHasFixedSize(true);
        reslist.setLayoutManager(new LinearLayoutManager(this));
        reslist.setAdapter(adapter);





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
                Intent intent = new Intent(search.this,search.class);
                startActivity(intent);;
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch(itemName) {
                    case "Home":
                        Intent intent = new Intent(search.this,first_act.class);
                        startActivity(intent);
                        break;
                    case "Account":
                        Intent intent2 = new Intent(search.this,act_accmgmtfrag.class);
                        startActivity(intent2);
                        break;
                    case "Cart":
                        Intent intent3 = new Intent(search.this,cartfrag.class);
                        startActivity(intent3);
                        break;
                    case "Contact":
                        Intent intent4 = new Intent(search.this,confrag.class);
                        startActivity(intent4);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });






    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView lname;
        private TextView lprice;
        private TextView ldesc;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            lname = itemView.findViewById(R.id.lname);
            lprice = itemView.findViewById(R.id.lprice);
            ldesc = itemView.findViewById(R.id.ldesc);
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
