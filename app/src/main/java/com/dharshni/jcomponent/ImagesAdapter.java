package com.dharshni.jcomponent;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dharshni.jcomponent.R;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private List<Uri> imageUriList;

    public ImagesAdapter(List<Uri> imageUriList) {
        this.imageUriList = imageUriList;
    }

    public void setImageUriList(List<Uri> imageUriList) {
        this.imageUriList = imageUriList;
        notifyDataSetChanged();
    }

    public List<Uri> getImageUriList() {
        return imageUriList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_images_picked, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Uri imageUri = imageUriList.get(position);
        holder.imageView.setImageURI(imageUri);

        final int itemPosition = position; // Create a final copy of the position variable

        holder.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the image URI from the list
                imageUriList.remove(itemPosition);
                notifyDataSetChanged(); // Notify the adapter about the data change

                // Optionally, you can perform any additional actions here
            }
        });
    }


    @Override
    public int getItemCount() {
        return imageUriList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton closeBtn;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageIv);
            closeBtn = itemView.findViewById(R.id.closeBtn);
        }
    }
}
