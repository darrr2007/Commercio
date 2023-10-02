package com.dharshni.jcomponent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dharshni.jcomponent.R;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView checkoutRecyclerView;
    private ArrayList<AdData> checkoutProducts;
    private Button callButton, messageButton, emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        messageButton = findViewById(R.id.messageButton);
        emailButton = findViewById(R.id.emailButton);

        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the checkoutProducts list from the intent
        checkoutProducts = getIntent().getParcelableArrayListExtra("cartProducts");

        if (checkoutProducts != null && checkoutProducts.size() > 0) {
            CartAdapter checkoutAdapter = new CartAdapter(checkoutProducts);
            checkoutRecyclerView.setAdapter(checkoutAdapter);
        } else {
            Toast.makeText(this, "No items to checkout", Toast.LENGTH_SHORT).show();
        }

        callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void makePhoneCall() {
        if (checkoutProducts != null && !checkoutProducts.isEmpty()) {
            // Assuming that the phone number is stored in the first item of checkoutProducts
            String phoneNumber = String.valueOf(checkoutProducts.get(0).getPhone());

            // Check if the phone number is valid
            if (!phoneNumber.isEmpty()) {
                // Create an Intent to initiate the call
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Phone number not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No items to checkout", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMessage() {
        if (checkoutProducts != null && !checkoutProducts.isEmpty()) {
            // Assuming that the phone number is stored in the first item of checkoutProducts
            String phoneNumber = String.valueOf(checkoutProducts.get(0).getPhone());

            // Check if the phone number is valid
            if (!phoneNumber.isEmpty()) {
                // Create an Intent to send a message
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + phoneNumber));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Phone number not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No items to checkout", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        if (checkoutProducts != null && !checkoutProducts.isEmpty()) {
            // Assuming that the email is stored in the first item of checkoutProducts
            String email = checkoutProducts.get(0).getEmail();

            // Check if the email is valid
            if (!email.isEmpty()) {
                // Create an Intent to send an email
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Email not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No items to checkout", Toast.LENGTH_SHORT).show();
        }
    }
}
