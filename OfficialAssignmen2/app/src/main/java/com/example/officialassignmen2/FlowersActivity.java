package com.example.officialassignmen2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FlowersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Flowers> options;
    FirebaseRecyclerAdapter<Flowers, MyViewHolder> adapter;
    DatabaseReference databaseReference;
    Button flowers_button, flowers_gift, external_link, flowers_favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Flowers");
        recyclerView = findViewById(R.id.favorite_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        flowers_button = findViewById(R.id.flowers_flower_recycler);
        flowers_gift = findViewById(R.id.flowers_gifts_recycler);
        flowers_favorites = findViewById(R.id.favorite_recycler);
        external_link = findViewById(R.id.gifts_external_recycler);

        external_link.setOnClickListener(view -> {
            goToUrl("https://themillionroses.us/?gclid=Cj0KCQjwmPSSBhCNARIsAH3cYgaJ_aBBfpSglVOBlbMlbXOT8mto1Tw-2U5nw02YWp6Jb1eZz_Y9CioaAoFjEALw_wcB");
        });

        flowers_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, FlowersActivity.class);
            startActivity(intent);
        });

        flowers_gift.setOnClickListener(view -> {
            Intent intent = new Intent(this, GiftsActivity.class);
            startActivity(intent);
        });

        flowers_favorites.setOnClickListener(view -> {
            Intent intent = new Intent(this, Favorite.class);
            startActivity(intent);
        });



        LoadData();
    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<Flowers>().setQuery(databaseReference, Flowers.class).build();
        adapter = new FirebaseRecyclerAdapter<Flowers, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Flowers model) {
                holder.flower_textView.setText(model.getFlowersName());
                holder.price_flower_textView.setText(model.getPrice());
                Picasso.get().load(model.getImageUrl()).into(holder.flower_imageView);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FlowersActivity.this, FlowerNotes.class);
                        intent.putExtra("FlowerKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_single_view, parent, false);
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
