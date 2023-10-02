//package com.example.jcomponent;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class LikedProductAdapter extends RecyclerView.Adapter<LikedProductAdapter.ViewHolder> {
//
//    private List<AdData> likedProducts;
//    private Context context;
//
//    public LikedProductAdapter(List<AdData> likedProducts, Context context) {
//        this.likedProducts = likedProducts;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liked_item_layout, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        AdData likedProduct = likedProducts.get(position);
//        // Bind the liked product details to the ViewHolder views (e.g., TextViews)
//        holder.titleTextView.setText(likedProduct.getTitle());
//        holder.priceTextView.setText(String.valueOf(likedProduct.getPrice()));
//        holder.descriptionTextView.setText(likedProduct.getDescription());
//        holder.locationTextView.setText(likedProduct.getLocation());
//        holder.brandTextView.setText(likedProduct.getBrand());
//        holder.categoryTextView.setText(likedProduct.getCategory());
//        holder.conditionTextView.setText(likedProduct.getCondition());
//        // Bind other details as needed
//    }
//
//    @Override
//    public int getItemCount() {
//        return likedProducts.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView titleTextView;
//        private TextView priceTextView;
//        private TextView descriptionTextView;
//        private TextView locationTextView;
//        private TextView brandTextView;
//        private TextView categoryTextView;
//        private TextView conditionTextView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            priceTextView = itemView.findViewById(R.id.priceTextView);
//            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
//            locationTextView = itemView.findViewById(R.id.locationTextView);
//            brandTextView = itemView.findViewById(R.id.brandTextView);
//            categoryTextView = itemView.findViewById(R.id.categoryTextView);
//            conditionTextView = itemView.findViewById(R.id.conditionTextView);
//        }
//    }
//}
