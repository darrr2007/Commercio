package com.dharshni.jcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dharshni.jcomponent.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private ArrayList<AdData> cartProducts;

    Button checkout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the cartProducts list from the intent
        cartProducts = getIntent().getParcelableArrayListExtra("cartProducts");

        if (cartProducts != null && cartProducts.size() > 0) {
            // Pass the context of the activity to the CartAdapter constructor

            CartAdapter cartAdapter = new CartAdapter(cartProducts);
            cartRecyclerView.setAdapter(cartAdapter);
        } else {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
        }

        checkout = findViewById(R.id.checkoutButton);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the cartProducts list to CheckoutActivity
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putParcelableArrayListExtra("cartProducts", cartProducts);
                startActivity(intent);
            }
        });
    }

}
