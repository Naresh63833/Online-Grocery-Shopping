package com.example.demo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(about.this,home.class);
        startActivity(i);
        finish();
    }

    public void aboutback(View view) {
        Intent i = new Intent(about.this,home.class);
        startActivity(i);
        finish();
    }
}