package com.example.officialassignmen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Gift_Notes extends AppCompatActivity {

    private ImageView imageView;
    TextView textView, price;
    Button button;
    DatabaseReference reference, refFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_notes);

        imageView = findViewById(R.id.image_gift_note);
        textView = findViewById(R.id.textView_gift_note);
        price = findViewById(R.id.price_gift_note);
        button = findViewById(R.id.button_fav_gift);
        reference = FirebaseDatabase.getInstance().getReference().child("Gifts");
        refFav = FirebaseDatabase.getInstance().getReference().child("Favorite");

        String NoteKey = getIntent().getStringExtra("GiftKey");

        reference.child(NoteKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String giftName = snapshot.child("giftsName").getValue().toString();
                    String prices = snapshot.child("price").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();

                    Picasso.get().load(imageUrl).into(imageView);
                    textView.setText(giftName);
                    price.setText(prices);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String prices = price.getText().toString();
                            String image = imageUrl;
                            String giftName = textView.getText().toString();
                            FavoriteFlower favoriteFlower = new FavoriteFlower(image, prices, giftName);
                            refFav.push().setValue(favoriteFlower);

                            Toast.makeText(Gift_Notes.this, "Added To Favorite", Toast.LENGTH_SHORT).show();
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