package com.example.demo_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.models.MyCartModel;
import com.example.demo_app.models.ShowAllModel;
import com.example.demo_app.models.ViewAllModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    double amount = 0.0;
    TextView subTotal,discount,shipping,total;
    Button paymentBtn;
    List<MyCartModel> cartModelList;
    List<ViewAllModel> viewAllModelList;
    List<ShowAllModel> showAllModelList;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    List<MyCartModel> myCartModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        myCartModelList = (ArrayList<MyCartModel>)getIntent().getSerializableExtra("itemList");

        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.amount_dis);
        shipping = findViewById(R.id.amount_ship);
        total = findViewById(R.id.total_amt);
        paymentBtn = findViewById(R.id.pay_btn);

        amount = getIntent().getDoubleExtra("amount",0.0);

        subTotal.setText(amount + " ₹");
        discount.setText(5 + " ₹");
        shipping.setText(5 + " ₹");
        total.setText(amount - 5 + 5 +" ₹");


        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethod();
            }
        });

    }

    private void paymentMethod() {

        Checkout checkout = new Checkout();

        final Activity activity = PaymentActivity.this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "My Grocery Application");

            options.put("description", "Reference No. #123456");

            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");

            options.put("currency", "INR");

            amount = amount * 100;

            options.put("amount", amount);
            JSONObject preFill = new JSONObject();

            preFill.put("email", "developer.grocery@gmail.com");

            preFill.put("contact", "6383346626");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PaymentActivity.this,AddressActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
    }
}