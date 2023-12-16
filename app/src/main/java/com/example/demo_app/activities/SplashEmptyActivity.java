package com.example.demo_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;
import com.example.demo_app.admin.AdminSecureActivity;
import com.example.demo_app.home;
import com.example.demo_app.seller.SellerHomeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashEmptyActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_empty);

        auth = FirebaseAuth.getInstance();


        if (auth.getInstance().getCurrentUser() != null) {
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.getString("isUser") != null) {
                        startActivity(new Intent(getApplicationContext(), home.class));
                        Toast.makeText(SplashEmptyActivity.this, "Already You Logged In", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    if (documentSnapshot.getString("isAdmin") != null) {
                        startActivity(new Intent(getApplicationContext(), AdminSecureActivity.class));
                        Toast.makeText(SplashEmptyActivity.this, "Already You Logged In", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    if (documentSnapshot.getString("isSeller") != null) {
                        startActivity(new Intent(getApplicationContext(), SellerHomeActivity.class));
                        Toast.makeText(SplashEmptyActivity.this, "Already You Logged In", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), welcome_activity.class));
                    finish();
                }
            });

        }
    }

    public void skip(View view) {
        Intent i = new Intent(SplashEmptyActivity.this,welcome_activity.class);
        startActivity(i);
        finish();
    }
}