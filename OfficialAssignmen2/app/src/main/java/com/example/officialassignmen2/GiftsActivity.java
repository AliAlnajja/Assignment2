package com.example.officialassignmen2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class GiftsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Gifts> options;
    FirebaseRecyclerAdapter<Gifts, MyViewHolder> adapter;
    DatabaseReference databaseReference;

    Button gifts_flower, gifts_gift, gifts_external, gifts_favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Gifts");
        recyclerView = findViewById(R.id.gift_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        gifts_flower = findViewById(R.id.gifts_flower_recycle);
        gifts_gift = findViewById(R.id.gifts_gift_recycle);
        gifts_external = findViewById(R.id.gifts_external_recycle);
        gifts_favorites = findViewById(R.id.gifts_favorite_recycle);

        LoadData();

        gifts_external.setOnClickListener(view -> {
            goToUrl("https://www.chapters.indigo.ca/en-ca/gifts/top-gifts/");
        });

        gifts_flower.setOnClickListener(view -> {
            Intent intent = new Intent(this, FlowersActivity.class);
            startActivity(intent);
        });

        gifts_gift.setOnClickListener(view -> {
            Intent intent = new Intent(this, GiftsActivity.class);
            startActivity(intent);
        });

        gifts_favorites.setOnClickListener(view -> {
            Intent intent = new Intent(this, Favorite.class);
            startActivity(intent);
        });

    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<Gifts>().setQuery(databaseReference, Gifts.class).build();
        adapter = new FirebaseRecyclerAdapter<Gifts, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Gifts model) {
                holder.gift_textView.setText(model.getGiftsName());
                holder.price_gift_textView.setText(model.getPrice());
                Picasso.get().load(model.getImageUrl()).into(holder.gift_imageView);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GiftsActivity.this, Gift_Notes.class);
                        intent.putExtra("GiftKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_single_view, parent, false);
                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void goToUrl (String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}