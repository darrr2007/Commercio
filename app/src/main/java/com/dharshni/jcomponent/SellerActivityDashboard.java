package com.dharshni.jcomponent;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dharshni.jcomponent.R;

public class SellerActivityDashboard extends AppCompatActivity {

    ImageButton add,history;
    Button back;
    TextView details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);

        add=findViewById(R.id.upload);
        history=findViewById(R.id.history);
        back=findViewById(R.id.back);
        details=findViewById(R.id.details);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivityDashboard.this,SellerActivity.class));
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivityDashboard.this, SellerHistoryActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivityDashboard.this, MainActivity.class));
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivityDashboard.this,SellerDetailsActivity.class));
            }
        });

    }
}
