package com.dharshni.jcomponent;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dharshni.jcomponent.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class SellerActivity extends AppCompatActivity {
    private ImageButton toolbarAdImageBtn;

    private StorageReference storageReference;

    private EditText brand, price, description, title, phone, email;

    private ImagesAdapter imagesAdapter;
    private ArrayList<Uri> selectedImages = new ArrayList<>();

    private DatabaseReference adsRef;

    private MaterialButton postAdBtn;
    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_CODE_CAMERA = 2;
    private AutoCompleteTextView categoryAct, conditionAct, locationAct;

    private String[] categories = {"Electronics", "Mobiles", "Computers", "Appliances",
            "Men's Fashion", "Women's Fashion", "Books", "Others"};

    private String[] condition = {"Brand New", "New", "Used One Time", "Used", "Old"};

    private String[] location = {"Main Gate", "Outside B Block", "Outside C Block", "Outside A Block",
            "Outside D Block", "Gazebo", "North Square", "Library", "AB1", "AB2", "AB3"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        adsRef = database.getReference().child("users").child("dharshni").child("product");

        toolbarAdImageBtn = findViewById(R.id.toolbarAdImageBtn);
        postAdBtn = findViewById(R.id.postAdBtn);
        brand = findViewById(R.id.brandEt);
        title = findViewById(R.id.titleEt);
        description = findViewById(R.id.descriptionEt);
        price = findViewById(R.id.priceEt);
        imagesAdapter = new ImagesAdapter(selectedImages);
        RecyclerView imagesRv = findViewById(R.id.imagesRv);
        imagesRv.setAdapter(imagesAdapter);
        categoryAct = findViewById(R.id.categoryAct);
        conditionAct = findViewById(R.id.conditonAct);
        locationAct = findViewById(R.id.locationAct);
        phone=findViewById(R.id.phoneNumberEt);
        email=findViewById(R.id.emailEt);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, categories);
        categoryAct.setAdapter(adapter);

        categoryAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
            }
        });

        ArrayAdapter<String> adapt = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, condition);
        conditionAct.setAdapter(adapt);

        conditionAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
            }
        });

        ArrayAdapter<String> adap = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, location);
        locationAct.setAdapter(adap);

        locationAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
            }
        });

        toolbarAdImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToGallery();
            }
        });

        postAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImages.isEmpty()) {
                    Toast.makeText(SellerActivity.this, "Please select at least one image", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImagesToFirebase();
                }
            }
        });
    }

    private void navigateToGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    public void onToolbarCameraClick(View view) {
        navigateToCamera();
    }

    private void navigateToCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_GALLERY) {
                if (data != null) {
                    ClipData clipData = data.getClipData();
                    if (clipData != null) {
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri imageUri = clipData.getItemAt(i).getUri();
                            selectedImages.add(imageUri);
                        }
                    } else {
                        Uri imageUri = data.getData();
                        selectedImages.add(imageUri);
                    }
                    imagesAdapter.notifyDataSetChanged();
                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                Uri capturedImageUri = data.getData();
                selectedImages.add(capturedImageUri);
                imagesAdapter.notifyDataSetChanged();
            }
        }
    }

    private void uploadImagesToFirebase() {
        for (int i = 0; i < selectedImages.size(); i++) {
            Uri imageUri = selectedImages.get(i);
            String imageName = "image_" + i;

            StorageReference imageRef = storageReference.child("images").child(imageName);

            UploadTask uploadTask = imageRef.putFile(imageUri);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    saveAdDataToFirebase(imageUrl);
                });
            }).addOnFailureListener(e -> {
                Toast.makeText(SellerActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void saveAdDataToFirebase(String imageUrl) {
        String brandText = brand.getText().toString();
        String titleText = title.getText().toString();
        String descriptionText = description.getText().toString();
        double priceValue = Double.parseDouble(price.getText().toString());
        String categoryText = categoryAct.getText().toString();
        String conditionText = conditionAct.getText().toString();
        String locationText = locationAct.getText().toString();
        String emailText = email.getText().toString();
        int phoneText = Integer.parseInt(phone.getText().toString());

        AdData adData = new AdData(brandText, titleText, descriptionText, priceValue, categoryText, conditionText, locationText, imageUrl,emailText,phoneText);

        //String adId = adsRef.push().getKey();
        adsRef.child(titleText).setValue(adData);

        Toast.makeText(SellerActivity.this, "Product has been added Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SellerActivity.this, SellerActivityDashboard.class));
    }
}
