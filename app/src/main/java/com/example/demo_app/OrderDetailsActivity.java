package com.example.demo_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.models.OrderDetailsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailsActivity extends AppCompatActivity {

    private EditText employeeNameEdt, employeePhoneEdt, employeeAddressEdt;
    private Button sendDatabtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    OrderDetailsModel orderDetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // initializing our edittext and button
        employeeNameEdt = findViewById(R.id.name);
        employeePhoneEdt = findViewById(R.id.phoneNumber);
        employeeAddressEdt = findViewById(R.id.address);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");

        // initializing our object
        // class variable.
        orderDetailsModel = new OrderDetailsModel();

        sendDatabtn = findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String Name = employeeNameEdt.getText().toString().trim();
                String Phone = employeePhoneEdt.getText().toString().trim();
                String Address = employeeAddressEdt.getText().toString().trim();

                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(Phone) && TextUtils.isEmpty(Address)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(OrderDetailsActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(Name, Phone, Address);
                }
            }
        });
    }

    private void addDatatoFirebase(String name, String phone, String address) {
        // below 3 lines of code is used to set
        // data in our object class.
        orderDetailsModel.setName(name);
        orderDetailsModel.setPhone(phone);
        orderDetailsModel.setAddress(address);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(orderDetailsModel);

                // after adding this data we are showing toast message.
                Toast.makeText(OrderDetailsActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(OrderDetailsActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}