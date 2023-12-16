package com.example.demo_app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.adapters.MyCartAdapter;
import com.example.demo_app.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    List<MyCartModel> cartModelList;
    MyCartAdapter myCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        TextView OrderPageBtn =findViewById(R.id.orderbtn);

        OrderPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlacedOrderActivity.this,myorders.class);
                startActivity(i);
                finish();
            }
        });


        cartModelList = (ArrayList<MyCartModel>)getIntent().getSerializableExtra("itemList");

        if (cartModelList != null && cartModelList.size() > 0){
            for (MyCartModel myCartModel : cartModelList){
                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName", myCartModel.getProductName());
                cartMap.put("productPrice", myCartModel.getProductPrice());
                cartMap.put("currentDate", myCartModel.getCurrentDate());
                cartMap.put("currentTime", myCartModel.getCurrentTime());
                cartMap.put("totalQuantity", myCartModel.getTotalQuantity());
                cartMap.put("totalPrice", myCartModel.getTotalPrice());
                cartMap.put("shop_name",myCartModel.getShop_name());
                cartMap.put("shop_location",myCartModel.getShop_location());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity.this, "Your Order has been Placed", Toast.LENGTH_SHORT).show();
                                customOrderPlaced();
                            }
                        });
            }
        }

    }
    private void customOrderPlaced() {
        final Dialog dialog = new Dialog(PlacedOrderActivity.this);

        dialog.setContentView(R.layout.custom_orderplaced_dialogue);

        TextView OrderPageBtn = dialog.findViewById(R.id.orderbtn);

        OrderPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlacedOrderActivity.this,myorders.class);
                dialog.dismiss();
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }
}
