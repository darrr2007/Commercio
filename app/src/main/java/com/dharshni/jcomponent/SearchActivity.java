package com.dharshni.jcomponent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dharshni.jcomponent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView adsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private DatabaseReference adsRef;
    private DatabaseReference cartRef;

    Button cart;

    List<AdData> cartProducts = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cart = findViewById(R.id.cartButton);

        adsRecyclerView = findViewById(R.id.adsRv);
        adsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoriesRecyclerView = findViewById(R.id.categoriesRv);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adsRef = database.getReference().child("users").child("dharshni").child("product");
        cartRef = database.getReference().child("users").child("dharshni").child("cart");

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the cartProducts list to CartActivity
                Intent intent = new Intent(SearchActivity.this, CartActivity.class);
                intent.putParcelableArrayListExtra("cartProducts", new ArrayList<>(cartProducts));
                startActivity(intent);
            }
        });

        adsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<AdData> adsList = new ArrayList<>();
                for (DataSnapshot adSnapshot : snapshot.getChildren()) {
                    AdData adData = adSnapshot.getValue(AdData.class);
                    adsList.add(adData);
                }

                AdapterCategory adsAdapter = new AdapterCategory(adsList, new AdapterCategory.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdData adData) {
                        showProductDetails(adData);
                    }
                });
                adsRecyclerView.setAdapter(adsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(SearchActivity.this, "Failed to retrieve ads", Toast.LENGTH_SHORT).show();
            }
        });

        // Categories data
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Electronics");
        categoriesList.add("Mobiles");
        categoriesList.add("Computers");
        categoriesList.add("Appliances");
        categoriesList.add("Men's Fashion");
        categoriesList.add("Women's Fashion");
        categoriesList.add("Books");
        categoriesList.add("Others");

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesList);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    @SuppressLint("MissingInflatedId")
    private void showProductDetails(AdData adData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_product_details, null);
        builder.setView(dialogView);

        TextView titleTextView = dialogView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = dialogView.findViewById(R.id.descriptionTextView);
        TextView locationTextView = dialogView.findViewById(R.id.locationTextView);
        TextView conditionTextView = dialogView.findViewById(R.id.conditionTextView);
        TextView priceTextView = dialogView.findViewById(R.id.priceTextView);
        TextView emailTextView = dialogView.findViewById(R.id.emailTextView);
        TextView phoneTextView = dialogView.findViewById(R.id.phoneTextView);
        Button addToCartButton = dialogView.findViewById(R.id.addToCartButton);

        titleTextView.setText(adData.getTitle());
        descriptionTextView.setText(adData.getDescription());
        locationTextView.setText(adData.getLocation());
        conditionTextView.setText(adData.getCondition());
        priceTextView.setText(String.valueOf(adData.getPrice()));
        emailTextView.setText(String.valueOf(adData.getEmail()));
        phoneTextView.setText(String.valueOf(adData.getPhone()));

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add to cart logic
                cartProducts.add(adData);
                Toast.makeText(SearchActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();

                // Add the cart item to Firebase
                CartItem cartItem = new CartItem();
                cartItem.setTitle(adData.getTitle());
                cartItem.setDescription(adData.getDescription());
                cartItem.setLocation(adData.getLocation());
                cartItem.setCondition(adData.getCondition());
                cartItem.setPrice(adData.getPrice());
                cartItem.setEmail(adData.getEmail());
                cartItem.setPhone(adData.getPhone());

                // Generate a new key for the cart item
                String cartItemKey = cartRef.push().getKey();

                // Save the cart item to Firebase using the generated key
                cartRef.child(cartItemKey).setValue(cartItem);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public interface OnItemClickListener {
        void onItemClick(AdData adData);
    }
    public class CartItem {
        private String title;
        private String description;
        private String location;
        private String condition;


        private double price;

        private String email;

        private int phone;

        public CartItem() {
            // Default constructor required for Firebase
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }
    }

}

