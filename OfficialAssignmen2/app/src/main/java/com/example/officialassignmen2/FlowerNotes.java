package com.example.officialassignmen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FlowerNotes extends AppCompatActivity {

    private ImageView imageView;
    TextView textView, price;
    Button button;
    DatabaseReference reference, refFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_notes);

        imageView = findViewById(R.id.image_flower_note);
        textView = findViewById(R.id.textView_flower_note);
        price = findViewById(R.id.price_flower_note);
        button = findViewById(R.id.button_fav);
        reference = FirebaseDatabase.getInstance().getReference().child("Flowers");
        refFav = FirebaseDatabase.getInstance().getReference().child("Favorite");

        String NoteKey = getIntent().getStringExtra("FlowerKey");

        reference.child(NoteKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String flowersName = snapshot.child("flowersName").getValue().toString();
                    String prices = snapshot.child("price").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();

                    Picasso.get().load(imageUrl).into(imageView);
                    textView.setText(flowersName);
                    price.setText(prices);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String prices = price.getText().toString();
                            String image = imageUrl;
                            String flowersName = textView.getText().toString();
                            FavoriteFlower favoriteFlower = new FavoriteFlower(image ,prices, flowersName);
                            refFav.push().setValue(favoriteFlower);

                            Toast.makeText(FlowerNotes.this, "Added To Favorite", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}