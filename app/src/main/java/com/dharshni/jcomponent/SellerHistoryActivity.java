package com.dharshni.jcomponent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dharshni.jcomponent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SellerHistoryActivity extends AppCompatActivity {

    private DatabaseReference adsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_history);

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adsRef = database.getReference().child("users").child("dharshni").child("product");

        // Get references to the TextViews in your XML layout
        TextView brandTextView = findViewById(R.id.brandTextView);
        TextView categoryTextView = findViewById(R.id.categoryTextView);
        TextView conditionTextView = findViewById(R.id.conditionTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);
        TextView titleTextView = findViewById(R.id.titleTextView);

        // Retrieve the ad data from Firebase
        adsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the ad data as a DataSnapshot
//                    DataSnapshot adSnapshot = dataSnapshot.getChildren().iterator().next();

                    // Retrieve the individual values
                    String brand = dataSnapshot.child("brand").getValue(String.class);
                    String category = dataSnapshot.child("category").getValue(String.class);
                    String condition = dataSnapshot.child("condition").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    String location = dataSnapshot.child("location").getValue(String.class);
                    Double priceDouble = dataSnapshot.child("price").getValue(Double.class);
                    String title = dataSnapshot.child("title").getValue(String.class);

// Check if priceDouble is null
                    double price = 0.0; // Default value if priceDouble is null
                    if (priceDouble != null) {
                        price = priceDouble;
                    }

// Populate the TextViews with the retrieved values
                    brandTextView.setText(brand);
                    categoryTextView.setText(category);
                    conditionTextView.setText(condition);
                    descriptionTextView.setText(description);
                    locationTextView.setText(location);
                    priceTextView.setText(String.valueOf(price));
                    titleTextView.setText(title);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error case
            }
        });
    }
}
