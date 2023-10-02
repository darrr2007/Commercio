package com.dharshni.jcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dharshni.jcomponent.R;

public class BuyerActivity extends AppCompatActivity {


    ImageButton search;
    TextView feedback;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        search=findViewById(R.id.history);
        feedback=findViewById(R.id.feedback);// Updated ID


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerActivity.this, SearchActivity.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerActivity.this,FeedbackActivity.class));
            }
        });
    }
}
