package com.example.officialassignmen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Favorite extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<FavoriteFlower> options;
    FirebaseRecyclerAdapter<FavoriteFlower, MyViewHolder> adapter;
    DatabaseReference databaseReference;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        btn = findViewById(R.id.checkout);
        databaseReference = FirebaseDatabase.getInstance().getReference("Favorite");
        recyclerView = findViewById(R.id.favorite_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData();
        
            btn.setOnClickListener(view -> {
                Intent intent = new Intent(this, Checkout.class);
                startActivity(intent);
            });
        }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<FavoriteFlower>().setQuery(databaseReference, FavoriteFlower.class).build();
        adapter = new FirebaseRecyclerAdapter<FavoriteFlower, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull FavoriteFlower model) {
                holder.fav_name.setText(model.getFlowersName());
                holder.fav_price.setText(model.getPrice());
                Picasso.get().load(model.getImageUrl()).into(holder.image_fav);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_favorite_view, parent, false);
                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}