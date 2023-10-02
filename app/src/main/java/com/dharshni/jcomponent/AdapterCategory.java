package com.dharshni.jcomponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dharshni.jcomponent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private List<AdData> adsList;
    private OnItemClickListener itemClickListener;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;

    public AdapterCategory(List<AdData> adsList, OnItemClickListener itemClickListener) {
        this.adsList = adsList;
        this.itemClickListener = itemClickListener;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("dharshni").child("product");
        firebaseStorage = FirebaseStorage.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_ad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdData adData = adsList.get(position);
        holder.titleTextView.setText(adData.getTitle());
        holder.descriptionTextView.setText(adData.getDescription());
        holder.locationTextView.setText(adData.getLocation());
        holder.conditionTextView.setText(adData.getCondition());
        holder.priceTextView.setText(String.valueOf(adData.getPrice()));

        // Show a placeholder image while the actual image is being fetched
        Glide.with(holder.itemView.getContext())
                .load(R.drawable.back1) // Placeholder image (e.g., a loading spinner or a default image)
                .into(holder.imageImageView);

        // Retrieve the imageUrl from Firebase Realtime Database for each child of "product"
        databaseReference.child(adData.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageName = "image_0"; // The image name in Firebase Storage
                StorageReference imageRef = firebaseStorage.getReference().child("images").child("image_0");

                Glide.with(holder.itemView.getContext())
                        .load(imageRef)
                        .placeholder(R.drawable.imageflower) // Placeholder image (e.g., a loading spinner or a default image)
                        .error(R.drawable.imageflower) // Error image in case the image retrieval fails
                        .into(holder.imageImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error (if any)
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(adData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView locationTextView;
        TextView conditionTextView;
        TextView priceTextView;
        ImageView imageImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTv);
            descriptionTextView = itemView.findViewById(R.id.descriptionTv);
            locationTextView = itemView.findViewById(R.id.locationTv);
            conditionTextView = itemView.findViewById(R.id.conditionTv);
            priceTextView = itemView.findViewById(R.id.priceTv);
            imageImageView = itemView.findViewById(R.id.imageIv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AdData adData);
    }
}