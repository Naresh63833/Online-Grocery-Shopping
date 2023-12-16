package com.example.demo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class New_products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_products);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(New_products.this,home.class);
        startActivity(i);
        finish();
    }
}