package com.example.demo_app.seller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;

public class SellerProfileActivity extends AppCompatActivity {

    private EditText nameEditText,phone,address;
    private EditText emailEditText;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        nameEditText = findViewById(R.id.sellername);
        emailEditText = findViewById(R.id.selleremail);
        phone = findViewById(R.id.sellerphone);
        address = findViewById(R.id.selleraddress);
        saveButton = findViewById(R.id.saveButton);

        // Get shared preferences
        sharedPreferences = getSharedPreferences("sellerprofile", MODE_PRIVATE);

        // Set text and image
        nameEditText.setText(sharedPreferences.getString("name", ""));
        emailEditText.setText(sharedPreferences.getString("email", ""));
        phone.setText(sharedPreferences.getString("phone", ""));
        address.setText(sharedPreferences.getString("address", ""));

        // Set save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save changes to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name", nameEditText.getText().toString().trim());
                editor.putString("email", emailEditText.getText().toString().trim());
                editor.putString("phone", phone.getText().toString().trim());
                editor.putString("address", address.getText().toString().trim());
                editor.apply();
                Toast.makeText(SellerProfileActivity.this, "Save Changes Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SellerProfileActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }
}