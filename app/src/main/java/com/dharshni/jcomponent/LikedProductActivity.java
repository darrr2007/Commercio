//package com.example.jcomponent;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class LikedProductActivity extends AppCompatActivity {
//
//    private RecyclerView likedRecyclerView;
//    private ArrayList<AdData> likedProducts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_liked_product);
//
//        likedRecyclerView = findViewById(R.id.likedRecyclerView);
//        likedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Retrieve the likedProducts list from the intent
//        likedProducts = getIntent().getParcelableArrayListExtra("likedProducts");
//
//        if (likedProducts != null && likedProducts.size() > 0) {
//            LikedProductAdapter likedProductAdapter = new LikedProductAdapter(likedProducts, this);
//            likedRecyclerView.setAdapter(likedProductAdapter);
//        } else {
//            Toast.makeText(this, "No liked products found", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
