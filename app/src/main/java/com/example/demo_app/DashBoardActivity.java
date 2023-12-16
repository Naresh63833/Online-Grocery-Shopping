package com.example.demo_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(DashBoardActivity.this,home.class);
        startActivity(i);
        finish();
    }
}