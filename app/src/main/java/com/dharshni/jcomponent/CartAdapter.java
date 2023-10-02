package com.dharshni.jcomponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dharshni.jcomponent.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<AdData> cartProducts;
    //private List<AdData> likedProducts;
    private Context context;

    public CartAdapter(List<AdData> cartProducts, Context context) {
        this.cartProducts = cartProducts;
        //this.likedProducts = likedProducts;


        this.context = context;
    }

    public CartAdapter(ArrayList<AdData> checkoutProducts) {
        this.cartProducts=checkoutProducts;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdData adData = cartProducts.get(position);
        holder.titleTextView.setText(adData.getTitle());
        holder.priceTextView.setText(String.format("%.2f", adData.getPrice())); // Format the price to two decimal places
        holder.descriptionTextView.setText(adData.getDescription());
        holder.locationTextView.setText(adData.getLocation());
        holder.brandTextView.setText(adData.getBrand());
        holder.categoryTextView.setText(adData.getCategory());
        holder.conditionTextView.setText(adData.getCondition());
        holder.emailTextView.setText(adData.getEmail());
        holder.phoneTextView.setText(String.valueOf(adData.getPhone()));

    }




    // Check if the current product is liked
//        boolean isLiked = likedProducts.contains(adData);
//        if (isLiked) {
//            // Product is liked, set the filled heart icon
//            holder.favBtn.setImageResource(R.drawable.baseline_favorite_24_yes);
//        } else {
//            // Product is not liked, set the empty heart icon
//            holder.favBtn.setImageResource(R.drawable.baseline_favorite_border_24);
//        }

//        // Set the click listener for the favorite button
//        holder.favBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Toggle the liked status of the product
//                if (isLiked) {
//                    likedProducts.remove(adData);
//                    holder.favBtn.setImageResource(R.drawable.baseline_favorite_border_24);
//                } else {
//                    likedProducts.add(adData);
//                    holder.favBtn.setImageResource(R.drawable.baseline_favorite_24_yes);
//                }
//            }
//        });





    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageButton favBtn;
        private TextView titleTextView;
        private TextView priceTextView;
        private TextView descriptionTextView;
        private TextView locationTextView;
        private TextView brandTextView;
        private TextView categoryTextView;
        private TextView conditionTextView;

        private TextView emailTextView;

        private TextView phoneTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            conditionTextView = itemView.findViewById(R.id.conditionTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneTextView=itemView.findViewById(R.id.phoneTextView);

            //favBtn = itemView.findViewById(R.id.favBtn); // Initialize favBtn here


            }
        }
    }

