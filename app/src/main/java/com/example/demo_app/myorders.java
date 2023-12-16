package com.example.demo_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.adapters.MyOrdersAdapter;
import com.example.demo_app.adapters.MyOrdersDetailsAdapter;
import com.example.demo_app.models.MyOrderDetailsModel;
import com.example.demo_app.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class myorders extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView OrdersRec,DetailsRec,dummyRec;
    //Orders item
    MyOrdersAdapter myOrdersAdapter;
    List<MyOrdersModel> myOrdersModelList;
    //Orders details
    MyOrdersDetailsAdapter myOrdersDetailsAdapter;
    List<MyOrderDetailsModel> myOrderDetailsModelList;

    int overAllTotalAmount;
    TextView overAllAmount;
    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        dummyRec = findViewById(R.id.dummy_rec);
        OrdersRec = findViewById(R.id.orders_Rec);
        OrdersRec.setVisibility(View.GONE);
        OrdersRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DetailsRec = findViewById(R.id.orderDetail_Rec);

        myOrdersModelList = new ArrayList<>();
        myOrdersAdapter = new MyOrdersAdapter(getApplicationContext(),myOrdersModelList);
        OrdersRec.setAdapter(myOrdersAdapter);

        overAllAmount = findViewById(R.id.overAllAmount);

        progressBar =findViewById(R.id.progress_bar_cart);

        progressBar.setVisibility(View.VISIBLE);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();

                                MyOrdersModel myOrdersModel = documentSnapshot.toObject(MyOrdersModel.class);

                                myOrdersModel.setDocumentId(documentId);

                                myOrdersModelList.add(myOrdersModel);
                                myOrdersAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                OrdersRec.setVisibility(View.VISIBLE);
                            }
                            calculateTotalAmount(myOrdersModelList);
                        }
                    }
                });


        //details items

        DetailsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myOrderDetailsModelList = new ArrayList<>();
        myOrdersDetailsAdapter = new MyOrdersDetailsAdapter(getApplicationContext(),myOrderDetailsModelList);
        DetailsRec.setAdapter(myOrdersDetailsAdapter);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrderDetails").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult().getDocuments()){

                                String documentId = doc.getId();

                                MyOrderDetailsModel myOrderDetailsModel = doc.toObject(MyOrderDetailsModel.class);

                                myOrderDetailsModel.setDocumentId(documentId);
                                myOrderDetailsModelList.add(myOrderDetailsModel);
                                myOrdersDetailsAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
        //dummy items

        dummyRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myOrderDetailsModelList = new ArrayList<>();
        myOrdersDetailsAdapter = new MyOrdersDetailsAdapter(getApplicationContext(),myOrderDetailsModelList);
        dummyRec.setAdapter(myOrdersDetailsAdapter);

        db.collection("MyOrderDetails")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MyOrderDetailsModel myOrderDetailsModel = document.toObject(MyOrderDetailsModel.class);
                                myOrderDetailsModelList.add(myOrderDetailsModel);
                                myOrdersDetailsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(myorders.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void calculateTotalAmount(List<MyOrdersModel> myOrdersModelList) {
        double totalAmount = 0.0;
        for (MyOrdersModel myOrdersModel : myOrdersModelList) {
            totalAmount += myOrdersModel.getTotalPrice();
        }
        overAllAmount.setText("Total Amount : " + totalAmount);

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(myorders.this,home.class);
        startActivity(i);
        finish();
    }
    public void cartback(View view) {
        Intent i = new Intent(myorders.this,home.class);
        startActivity(i);
        finish();
    }
}