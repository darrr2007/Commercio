package com.dharshni.jcomponent;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.dharshni.jcomponent.R;

public class MainActivity extends AppCompatActivity {

    CardView buyer,seller;
    ImageButton ibuyer,iseller;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buyer=findViewById(R.id.ButtonBuyer);
        seller=findViewById(R.id.ButtonSeller);
        ibuyer=findViewById(R.id.buyer);
        iseller=findViewById(R.id.seller);



        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SellerActivityDashboard.class));

            }
        });

        iseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SellerActivityDashboard.class));
            }
        });

        ibuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BuyerActivity.class));
            }
        });

        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BuyerActivity.class));
            }
        });
    }
}