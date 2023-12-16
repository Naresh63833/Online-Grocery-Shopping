package com.example.demo_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo_app.R;
import com.google.firebase.auth.FirebaseAuth;

public class welcome_activity extends AppCompatActivity {

    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        auth = FirebaseAuth.getInstance();

   /*    if(auth.getCurrentUser() != null){
            Intent i = new Intent(welcome_activity.this,home.class);
            startActivity(i);
            Toast.makeText(this, "please wait you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        } */
      /*  if (auth.getInstance().getCurrentUser() != null){
               DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
               df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                   @Override
                   public void onSuccess(DocumentSnapshot documentSnapshot) {
                       if (documentSnapshot.getString("isUser") != null){
                           startActivity(new Intent(getApplicationContext(), home.class));
                           Toast.makeText(welcome_activity.this, "Already You Logged In", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                       if (documentSnapshot.getString("isAdmin") != null){
                           startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                           Toast.makeText(welcome_activity.this, "Already You Logged In", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                       if (documentSnapshot.getString("isSeller") != null){
                           startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                           Toast.makeText(welcome_activity.this, "Already You Logged In", Toast.LENGTH_SHORT).show();
                           finish();
                       }

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       FirebaseAuth.getInstance().signOut();
                       startActivity(new Intent(getApplicationContext(), SellerWelcomeActivity.class));
                       finish();
                   }
               });

           }   */
       }


    public void loginbtn(View view) {
        Intent i = new Intent(welcome_activity.this, Login_Activity.class);
        startActivity(i);
        finish();
    }

    public void signupbtn(View view) {
        Intent i = new Intent(welcome_activity.this, register_activity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        customExitDialog();
    }

    private void customExitDialog() {
        final Dialog dialog = new Dialog(welcome_activity.this);

        dialog.setContentView(R.layout.custom_exit_dialog);

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
                dialog.dismiss();
                finish();

            }
        });
        dialog.show();
    }
}