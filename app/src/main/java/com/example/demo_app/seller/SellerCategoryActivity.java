package com.example.demo_app.seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;

public class SellerCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_category);
    }

    public void bakeryEggsandBread(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerBakeryActivity.class);
        startActivity(i);
        finish();
    }

    public void cerealsandBreakFastFood(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerBreakFastActivity.class);
        startActivity(i);
        finish();
    }

    public void drinks(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerDrinkActivity.class);
        startActivity(i);
        finish();
    }

    public void fruits(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerFruitActivity.class);
        startActivity(i);
        finish();
    }

    public void meatsandseafoods(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerMeatActivity.class);
        startActivity(i);
        finish();
    }

    public void vegetables(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerVegetableActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SellerCategoryActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }

    public void other(View view) {
        Intent i = new Intent(SellerCategoryActivity.this, SellerOtherProductsActivity.class);
        startActivity(i);
        finish();
    }
}