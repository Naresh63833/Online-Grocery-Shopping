package com.example.demo_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.adapters.OfferAdapter;
import com.example.demo_app.adapters.OfferAdapter1;
import com.example.demo_app.models.OfferModel;
import com.example.demo_app.models.OfferModel1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OffersActivity extends AppCompatActivity {

    ImageView imageOffer;
    FirebaseFirestore db;
    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView popularRec,catRec;

    //Popular items
    List<OfferModel> offerModelList;
    OfferAdapter offerAdapter;
    //Category items
    List<OfferModel1> offerModel1List;
    OfferAdapter1 offerAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        db = FirebaseFirestore.getInstance();
        scrollView = findViewById(R.id.scroll_view);
        progressBar = findViewById(R.id.progress_bar);

        popularRec = findViewById(R.id.popOffers_rec);
        catRec = findViewById(R.id.catOffers_rec);

        imageOffer = findViewById(R.id.image_offer);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        imageOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OffersActivity.this,ShowAllActivity.class);
                startActivity(i);
                finish();
            }
        });


        //Popular items

        popularRec.setLayoutManager(new LinearLayoutManager(OffersActivity.this, RecyclerView.VERTICAL, false));
        offerModelList = new ArrayList<>();
        offerAdapter = new OfferAdapter(OffersActivity.this, offerModelList);
        popularRec.setAdapter(offerAdapter);

        //Popular items

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                OfferModel offerModel = document.toObject(OfferModel.class);
                                offerModelList.add(offerModel);
                                offerAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(OffersActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Category items
        catRec.setLayoutManager(new LinearLayoutManager(OffersActivity.this,RecyclerView.VERTICAL,false));
        offerModel1List = new ArrayList<>();
        offerAdapter1 = new OfferAdapter1(OffersActivity.this,offerModel1List);
        catRec.setAdapter(offerAdapter1);


        //Category items

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                OfferModel1 offerModel1 = document.toObject(OfferModel1.class);
                                offerModel1List.add(offerModel1);
                                offerAdapter1.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                catRec.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(OffersActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                            catRec.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,home.class);
        startActivity(i);
        finish();
    }

    public void offerback(View view) {
        Intent i = new Intent(this,home.class);
        startActivity(i);
        finish();
    }
}