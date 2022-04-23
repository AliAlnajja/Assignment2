package com.example.officialassignmen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Checkout extends AppCompatActivity {

    Button pickup, proceed, delivery;
    EditText text;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        pickup = findViewById(R.id.pickup_checkout);
        proceed = findViewById(R.id.proceed_checkout);
        delivery = findViewById(R.id.deliver_checkout);
        text = findViewById(R.id.editText_checkout);
        reference = FirebaseDatabase.getInstance().getReference("Favorite");

        delivery.setOnClickListener(view -> {
            Toast.makeText(this, "Order will be delivered", Toast.LENGTH_SHORT).show();
        });

        pickup.setOnClickListener(view -> {
            Toast.makeText(this, "Order will be picked up", Toast.LENGTH_SHORT).show();
        });

        proceed.setOnClickListener(view -> {
            Intent intent = new Intent(this, Timer.class);
            startActivity(intent);

            reference.removeValue();

        });

    }
}