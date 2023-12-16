package com.example.demo_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.adapters.MyCartAdapter;
import com.example.demo_app.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class My_Cart_Activity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;

    int overAllTotalAmount;
    TextView overAllAmount;
    ProgressBar progressBar;
    Button buyNow1,buyNow2,orderdetails;

    MyCartModel myCartModel;

    EditText Name,Phone,Address,Productdetails,OverAllAmount;
    String name,phone,address,productdetails,overallAmount;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        Name = findViewById(R.id.cartName);
        Phone = findViewById(R.id.cartPhone);
        Address = findViewById(R.id.cartAddress);
        Productdetails = findViewById(R.id.cartDetails);
        OverAllAmount = findViewById(R.id.cartAmount);

        buyNow1 = findViewById(R.id.buy_now);
        buyNow2 = findViewById(R.id.cashOnDelivery);
        orderdetails = findViewById(R.id.orderdetails);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getApplicationContext(),cartModelList);
        recyclerView.setAdapter(cartAdapter);

        overAllAmount = findViewById(R.id.overAllAmount);

        progressBar =findViewById(R.id.progress_bar_cart);

        progressBar.setVisibility(View.VISIBLE);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();

                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                                cartModel.setDocumentId(documentId);

                                cartModelList.add(cartModel);
                                cartAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            calculateTotalAmount(cartModelList);
                        }
                    }
                });

    }

    private void calculateTotalAmount(List<MyCartModel> cartModelList) {
        double totalAmount = 0.0;
        for (MyCartModel myCartModel : cartModelList) {
            totalAmount += myCartModel.getTotalPrice();
        }
        overAllAmount.setText("Total Amount : " + totalAmount);
        //Buy Now

        orderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customOrderDetails();


                }
        });
        buyNow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customOrderonline();
            }
        });
        buyNow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customcashonDeliveryDialog();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(My_Cart_Activity.this,home.class);
        startActivity(i);
        finish();
    }
    public void cartback(View view) {
        Intent i = new Intent(My_Cart_Activity.this,home.class);
        startActivity(i);
        finish();
    }

    private void customcashonDeliveryDialog() {
        final Dialog dialog = new Dialog(My_Cart_Activity.this);

        dialog.setContentView(R.layout.custom_cashondelivery_dialogue);

        TextView dialogButtonYes =  dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = dialog.findViewById(R.id.textViewNo);

        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(My_Cart_Activity.this,PlacedOrderActivity.class);
                i.putExtra("itemList",(Serializable) cartModelList);
                dialog.dismiss();
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }

    private void customOrderonline() {
        final Dialog dialog = new Dialog(My_Cart_Activity.this);

        dialog.setContentView(R.layout.custom_onlinepay_dialogue);

        TextView dialogButtonYes =  dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = dialog.findViewById(R.id.textViewNo);

        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(My_Cart_Activity.this, AddressActivity.class);
                if (myCartModel != null) {
                    i.putExtra("item", myCartModel);
                }
                dialog.dismiss();
                startActivity(i);
            }
        });
        dialog.show();
    }
    private void customOrderDetails() {
        final Dialog dialog = new Dialog(My_Cart_Activity.this);

        dialog.setContentView(R.layout.custom_orderdetails_dialogue);

        TextView dialogButtonYes =  dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = dialog.findViewById(R.id.textViewNo);

        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Name.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                address = Address.getText().toString().trim();
                productdetails = Productdetails.getText().toString().trim();
                overallAmount = OverAllAmount.getText().toString().trim();

                String saveCurrentDate, saveCurrentTime;

                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM - dd - yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());

                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("name",Name.getText().toString().trim());
                cartMap.put("phone",Phone.getText().toString().trim());
                cartMap.put("address",Address.getText().toString().trim());
                cartMap.put("products",Productdetails.getText().toString().trim());
                cartMap.put("total",OverAllAmount.getText().toString().trim());
                cartMap.put("currentTime",saveCurrentTime);
                cartMap.put("currentDate",saveCurrentDate);

                db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrderDetails").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                dialog.dismiss();
                                Toast.makeText(My_Cart_Activity.this, "Order Details are added", Toast.LENGTH_SHORT).show();
                                customDetailsUploaded();
                            }
                        });
            }
        });
        dialog.show();
    }

    private void customDetailsUploaded() {
        final Dialog dialog = new Dialog(My_Cart_Activity.this);

        dialog.setContentView(R.layout.custom_orderdetails_success_dialogue);
        dialog.show();
    }
}