package com.example.demo_app.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.adapters.SellerDetailsAdapter;
import com.example.demo_app.models.SellerDetailsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SellerDetailsAdminActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    SellerDetailsAdapter sellerDetailsAdapter;
    List<SellerDetailsModel> sellerDetailsModelList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_details_admin);

        firestore = FirebaseFirestore.getInstance();
        String Type = getIntent().getStringExtra("Type");
        recyclerView = findViewById(R.id.sellerdetails_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sellerDetailsModelList = new ArrayList<>();
        sellerDetailsAdapter = new SellerDetailsAdapter(this,sellerDetailsModelList);
        recyclerView.setAdapter(sellerDetailsAdapter);

        //Getting Seller
        if(Type != null && Type.equalsIgnoreCase("Seller")){
            firestore.collection("Users").whereEqualTo("Type","Seller").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        SellerDetailsModel sellerDetailsModel = documentSnapshot.toObject(SellerDetailsModel.class);
                        sellerDetailsModelList.add(sellerDetailsModel);
                        sellerDetailsAdapter.notifyDataSetChanged();

                    }
                }
            });
        }
    }


    public void adminback(View view) {
        Intent i = new Intent(SellerDetailsAdminActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SellerDetailsAdminActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }
}