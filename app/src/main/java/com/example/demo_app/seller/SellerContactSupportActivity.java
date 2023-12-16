package com.example.demo_app.seller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerContactSupportActivity extends AppCompatActivity {

    EditText nameField,emailField,messageField,phoneField,typeField,dateField;
    String name,email,message,phone,type,date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_contact_support);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference messageRef = database.getReference("messages");

         Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameField = findViewById(R.id.name_field);
                emailField = findViewById(R.id.email_field);
                messageField = findViewById(R.id.message_field);
                phoneField = findViewById(R.id.phone_field);
                typeField = findViewById(R.id.type_field);
                dateField = findViewById(R.id.date_field);

                name = nameField.getText().toString().trim();
                email = emailField.getText().toString().trim();
                message = messageField.getText().toString().trim();
                phone = phoneField.getText().toString().trim();
                type = typeField.getText().toString().trim();
                date = dateField.getText().toString().trim();


                if (!name.isEmpty() && !email.isEmpty() && !message.isEmpty() && !phone.isEmpty() && !type.isEmpty() && !date.isEmpty()) {
                    // Create a new message object
                    Message messageObject = new Message();
                    // Push the message to Firebase
                    messageRef.push().setValue(messageObject);
                    // Clear the input fields
                    nameField.setText("");
                    emailField.setText("");
                    messageField.setText("");
                    phoneField.setText("");
                    typeField.setText("");
                    dateField.setText("");
                    Toast.makeText(SellerContactSupportActivity.this, "Your message sent successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SellerContactSupportActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void sellcontactback(View view) {
        Intent i = new Intent(SellerContactSupportActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SellerContactSupportActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }
    public void emailclick(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");
        i.putExtra(Intent.EXTRA_EMAIL,"grocerysupport253@gmail.com");
        try{
            startActivity(Intent.createChooser(i,"Choose an email client"));
        }
        catch (Exception e){
            Toast.makeText(this, "There are no email clients", Toast.LENGTH_SHORT).show();
        }
    }

    public void phoneclick(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:9876543210"));
        startActivity(i);
    }
}