package com.example.demo_app.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.admin.AdminSecureActivity;
import com.example.demo_app.R;
import com.example.demo_app.home;
import com.example.demo_app.seller.SellerHomeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_Activity extends AppCompatActivity {

    EditText email, password;
    String Email, Password;
    ProgressDialog progressDialog;
    boolean valid = true;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    boolean passwordvisible;
    Button forgotPassword;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPassword);
        progressDialog = new ProgressDialog(Login_Activity.this);

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
        Intent i = new Intent(Login_Activity.this, register_activity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Login_Activity.this, welcome_activity.class);
        startActivity(i);
        finish();
    }

    public boolean checkField(EditText textField) {
        if (email.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            email.setError("Please Enter Your Email");
            progressDialog.dismiss();
            valid = false;
        }if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password is Empty!", Toast.LENGTH_SHORT).show();
            email.setError("Please Enter Your Password");
            progressDialog.dismiss();
            valid = false;
        } else {
            valid = true;
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        return valid;
    }

    public void signin(View view) {
        //loginUser();

        checkField(email);
        checkField(password);

        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();

      //  progressDialog.setMessage("Loading...\nPlease wait");
       // progressDialog.show();

        if (valid){
            auth.signInWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(Login_Activity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    checkUserAccessLevel(authResult.getUser().getUid());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Login_Activity.this, "Logged in Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fstore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("tag","onsuccess" +documentSnapshot.getData());
                if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(Login_Activity.this, home.class));
                    finish();
                }else if(documentSnapshot.getString("isAdmin") != null){
                    startActivity(new Intent(Login_Activity.this, AdminSecureActivity.class));
                    finish();
                }else if(documentSnapshot.getString("isSeller") != null){
                    startActivity(new Intent(Login_Activity.this, SellerHomeActivity.class));
                    finish();
                }
            }
        });
    }

  /*  private void loginUser() {
        Useremail = email.getText().toString().trim();
        Userpassword = password.getText().toString().trim();

        progressDialog.setMessage("Loading...\nPlease wait");
        progressDialog.show();

        if(TextUtils.isEmpty(Useremail)){
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        if(TextUtils.isEmpty(Userpassword)){
            Toast.makeText(this, "Password Is Empty", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        if(Userpassword.length() < 4){
            Toast.makeText(this, "Password Length must be greater than 4 letter", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        //Login user
        auth.signInWithEmailAndPassword(Useremail,Userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_Activity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login_Activity.this, welcome_activity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(Login_Activity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
} */


    public void forgotPassword(View view) {
        Intent i = new Intent(Login_Activity.this, ForgotPasswordActivity.class);
        startActivity(i);
        finish();
    }

}