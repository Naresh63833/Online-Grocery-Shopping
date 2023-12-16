package com.example.demo_app.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;

public class SellerAboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_about);
    }

    public void selleraboutback(View view) {
        Intent i = new Intent(SellerAboutActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SellerAboutActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }
}