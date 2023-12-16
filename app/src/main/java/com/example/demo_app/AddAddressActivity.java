package com.example.demo_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText name,address,city,postalCode,phoneNumber;
    String userName,userAddress,userCity,userCode,userNumber;
    Button addAddressbtn;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        postalCode = findViewById(R.id.ad_code);
        phoneNumber = findViewById(R.id.ad_phone);
        addAddressbtn = findViewById(R.id.ad_add_address);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        addAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = name.getText().toString().trim();
                userAddress = address.getText().toString().trim();
                userCity = city.getText().toString().trim();
                userCode = postalCode.getText().toString().trim();
                userNumber = phoneNumber.getText().toString().trim();

                String final_address = "";

                if (!userName.isEmpty()){
                    final_address += userName+", ";
                }
                if (!userAddress.isEmpty()){
                    final_address += userAddress+", ";
                }
                if (!userCity.isEmpty()){
                    final_address += userCity+", ";
                }
                if (!userCode.isEmpty()){
                    final_address += userCode+", ";
                }
                if (!userNumber.isEmpty()){
                    final_address += userNumber+". ";
                }
                if (!userName.isEmpty() && !userAddress.isEmpty() && !userCity.isEmpty() && !userCode.isEmpty() && !userNumber.isEmpty()){

                    Map<String,String> map = new HashMap<>();
                    map.put("userAddress",final_address);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AddAddressActivity.this,DetailedActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(AddAddressActivity.this, "Kindly Fill All Field", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(AddAddressActivity.this,AddressActivity.class);
        startActivity(i);
        finish();
    }
}