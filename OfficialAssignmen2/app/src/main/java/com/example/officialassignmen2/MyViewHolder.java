package com.example.officialassignmen2;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView gift_imageView, flower_imageView;
    TextView gift_textView, flower_textView, price_gift_textView, price_flower_textView;
    TextView fav_name, fav_price;
    ImageView image_fav;
    View v;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        gift_imageView = itemView.findViewById(R.id.image_gift_singe_view);
        gift_textView = itemView.findViewById(R.id.textView_gift_single_view);
        price_gift_textView = itemView.findViewById(R.id.price_gift_single_view);

        flower_imageView = itemView.findViewById(R.id.image_flower_single_view);
        flower_textView = itemView.findViewById(R.id.textView_flower_single_view);
        price_flower_textView = itemView.findViewById(R.id.price_flower_single_view);

        fav_name = itemView.findViewById(R.id.textView_flower_fav);
        fav_price = itemView.findViewById(R.id.price_flower_fav);
        image_fav = itemView.findViewById(R.id.image_flower_fav);



        v = itemView;
    }
}
