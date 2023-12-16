package com.example.demo_app.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_activity extends AppCompatActivity {

    EditText name, email, phone, password, type,shopname, shopaddress;
    String Name, Email, Phone, Password, ShopName, ShopAddress,Type;
    CheckBox isUser, isAdmin, isSeller;
    ProgressDialog progressDialog;
    boolean passwordvisible;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    FirebaseDatabase database;
    boolean valid = true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        shopname = findViewById(R.id.shopname);
        shopaddress = findViewById(R.id.shopaddress);
        type = findViewById(R.id.type);
        isUser = findViewById(R.id.usercheck);
        isAdmin = findViewById(R.id.admincheck);
        isSeller = findViewById(R.id.sellercheck);

        //check box logics
        isUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (buttonView.isChecked()) {
                    isAdmin.setChecked(false);
                    isSeller.setChecked(false);
                }
            }
        });

        isAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (buttonView.isChecked()) {
                    isUser.setChecked(false);
                    isSeller.setChecked(false);
                }
            }
        });

        isSeller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (buttonView.isChecked()) {
                    isUser.setChecked(false);
                    isAdmin.setChecked(false);
                }
            }
        });


        progressDialog = new ProgressDialog(register_activity.this);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordvisible) {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordvisible = false;
                        } else {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordvisible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void clickregister(View view) {
        Intent i = new Intent(register_activity.this, Login_Activity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(register_activity.this, welcome_activity.class);
        startActivity(i);
        finish();
    }

    public boolean checkField(EditText editText) {
        if (name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Name is Empty!", Toast.LENGTH_SHORT).show();
            name.setError("Please Enter Your Name");
            valid = false;
        } if (email.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            email.setError("Please Enter Your Email");
            valid = false;
        }if (phone.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Phone Number is Empty!", Toast.LENGTH_SHORT).show();
            phone.setError("Please Enter Your Phone Number");
            valid = false;
        }if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password Is Empty", Toast.LENGTH_SHORT).show();
            password.setError("Please Enter Your Password");
            valid = false;
        }if (password.length() < 4) {
            Toast.makeText(this, "Password Length must be greater than 4 letter", Toast.LENGTH_SHORT).show();
            password.setError("Password Length must be greater than 4 letter");
            valid = false;
        }if (type.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Type Is Empty", Toast.LENGTH_SHORT).show();
            password.setError("Please Enter Your Type");
            valid = false;
        }else {
            valid = true;
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        return valid;
    }

    public void signup(View view) {

        //createUser();


        checkField(name);
        checkField(email);
        checkField(password);
        checkField(phone);
        checkField(type);

        Name = name.getText().toString().trim();
        Email = email.getText().toString().trim();
        Phone = phone.getText().toString().trim();
        Password = password.getText().toString().trim();
        ShopName = shopname.getText().toString().trim();
        ShopAddress = shopaddress.getText().toString().trim();
        Type = type.getText().toString().trim();

        if (!(isUser.isChecked() || isAdmin.isChecked() || isSeller.isChecked())){
            Toast.makeText(this, "Select the type - user or admin or seller", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if (valid){
            auth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                   FirebaseUser user = auth.getCurrentUser();

                    Toast.makeText(register_activity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    DocumentReference df = fstore.collection("Users").document(user.getUid());
                    Map<String,Object> userinfo = new HashMap<>();
                    userinfo.put("Name",Name);
                    userinfo.put("Email",Email);
                    userinfo.put("Phone_Number",Phone);
                    userinfo.put("Password",Password);
                    userinfo.put("Shop_Name",ShopName);
                    userinfo.put("Shop_Address",ShopAddress);
                    userinfo.put("Type",Type);


                    if (isUser.isChecked()){
                        userinfo.put("isUser","User");
                    }if (isAdmin.isChecked()){
                        userinfo.put("isAdmin","Admin");
                    }if (isSeller.isChecked()){
                        userinfo.put("isSeller","Seller");
                    }
                    df.set(userinfo);

                    if (isUser.isChecked()){
                        startActivity(new Intent(register_activity.this,Login_Activity.class));
                        finish();
                    }
                    if (isAdmin.isChecked()) {
                        startActivity(new Intent(register_activity.this, Login_Activity.class));
                        finish();
                    }
                    if (isSeller.isChecked()){
                        startActivity(new Intent(register_activity.this,Login_Activity.class));
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(register_activity.this, "Failed To create account", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }



    }

   /* private void createUser() {
        Username = name.getText().toString().trim();
        Useremail = email.getText().toString().trim();
        Phone = phone.getText().toString().trim();
        Userpassword = password.getText().toString().trim();
        ShopName = shopname.getText().toString().trim();
        ShopAddress = shopaddress.getText().toString().trim();

        // progressDialog.setMessage("Loading....\nPlease wait");
        //   progressDialog.show();

        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(this, "Name is Empty!", Toast.LENGTH_SHORT).show();
            // progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(Useremail)) {
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            // progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(Userpassword)) {
            Toast.makeText(this, "Password Is Empty", Toast.LENGTH_SHORT).show();
            // progressDialog.dismiss();
            return;
        }
        if (Userpassword.length() < 4) {
            Toast.makeText(this, "Password Length must be greater than 4 letter", Toast.LENGTH_SHORT).show();
            //progressDialog.dismiss();
        } else {
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
        //Create user
        auth.createUserWithEmailAndPassword(Useremail, Userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserModel userModel = new UserModel(Username, Useremail, Userpassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);

                            progressDialog.dismiss();
                            Toast.makeText(register_activity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(register_activity.this, Login_Activity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(register_activity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }  */

}