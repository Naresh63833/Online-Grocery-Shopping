package com.example.demo_app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;

public class AdminSecureActivity extends AppCompatActivity {

    EditText name,key;
    String Name,Key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_secure);

        name = findViewById(R.id.editTextTextPersonName);
        key = findViewById(R.id.editTextTextPassword);
    }

    public void activatebtn(View view) {

        Name = name.getText().toString().trim();
        Key = key.getText().toString().trim();
        if (TextUtils.isEmpty(Name)) {
            name.setError("Please Enter Admin Name");
        } if (TextUtils.isEmpty(Key)) {
            key.setError("Please Enter Key");
        }
        else {
            activate();
        }
    }

    private void activate() {
         Name = name.getText().toString().trim();
         Key = key.getText().toString().trim();
        if(Name.equals("admin") && Key.equals("12345")) {
            Toast.makeText(this, "Successfully Activate", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AdminSecureActivity.this, AdminHomeActivity.class);
            startActivity(i);
            finish();
        }else {
            Toast.makeText(this, "Admin name and key does not match", Toast.LENGTH_SHORT).show();
        }
    }
}