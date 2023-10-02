package com.dharshni.jcomponent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dharshni.jcomponent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {

    private RecyclerView feedbackRecyclerView;
    private ArrayList<AdData> productList; // Updated variable name to productList
    private FeedbackAdapter feedbackAdapter;
    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackRecyclerView = findViewById(R.id.feedbackRecyclerView);
        feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>(); // Initialize the productList ArrayList

        feedbackRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Retrieve the products data from Firebase
        feedbackRef.child("dharshni").child("product")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                            AdData adData = productSnapshot.getValue(AdData.class);
                            if (adData != null) {
                                productList.add(adData);
                            } else {
                                Log.d("Firebase", "adData is null for productSnapshot: " + productSnapshot.getKey());
                            }
                        }
                        setupFeedbackAdapter();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }

    private void setupFeedbackAdapter() {
        if (!productList.isEmpty()) {
            feedbackAdapter = new FeedbackAdapter(productList, this);
            feedbackRecyclerView.setAdapter(feedbackAdapter);

            feedbackAdapter.setOnItemClickListener(new FeedbackAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    showFeedbackDialog(position);
                }
            });
        } else {
            Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show();
        }
    }

    // Example of how to show the FeedbackDialog with position value set
    private void showFeedbackDialog(int position) {
        FeedbackDialog dialog = FeedbackDialog.newInstance(position);
        dialog.show(getSupportFragmentManager(), "feedback_dialog");
    }

    // Example method to save feedback to Firebase
    public void saveFeedbackToFirebase(int position, String feedback) {
        // Assuming you have a Firebase Realtime Database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Save the feedback using the position as a child node
        databaseReference.child("feedback").child(String.valueOf(position)).setValue(feedback);

        Toast.makeText(this, "Feedback saved successfully!", Toast.LENGTH_SHORT).show();
    }
}
