package com.example.demo_app.admin;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.activities.welcome_activity;
import com.example.demo_app.adapters.SellerItemsAdapter;
import com.example.demo_app.adapters.UserItemsAdapter;
import com.example.demo_app.models.SellerItemsModel;
import com.example.demo_app.models.UserItemsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminHomeActivity extends AppCompatActivity {

    LinearLayout navigation,navigation1,navigation2,navigation3;
    Button arrowdown,arrowup,arrowdown1,arrowup1,arrowdown2,arrowup2;

    RecyclerView userItemsRec,sellerItemsRec;
    //User items
    List<UserItemsModel> userItemsModelList;
    UserItemsAdapter userItemsAdapter;
    //Seller items
    List<SellerItemsModel> sellerItemsModelList;
    SellerItemsAdapter sellerItemsAdapter;

    FirebaseAuth auth;
    FirebaseFirestore db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        navigation = findViewById(R.id.sellernavigation);
        navigation1 = findViewById(R.id.navigation1);
        navigation2 = findViewById(R.id.navigation2);
        navigation3 = findViewById(R.id.navigation3);
        arrowdown = findViewById(R.id.arrowdown);
        arrowup = findViewById(R.id.arrowup);
        arrowdown1 = findViewById(R.id.arrowdown1);
        arrowup1 = findViewById(R.id.arrowup1);
        arrowdown2 = findViewById(R.id.arrowdown2);
        arrowup2 = findViewById(R.id.arrowup2);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userItemsRec = findViewById(R.id.userItems_rec);
        sellerItemsRec = findViewById(R.id.sellerItems_rec);

        //User items

        userItemsRec.setLayoutManager(new LinearLayoutManager(AdminHomeActivity.this,RecyclerView.VERTICAL,false));
        userItemsModelList = new ArrayList<>();
        userItemsAdapter = new UserItemsAdapter(AdminHomeActivity.this,userItemsModelList);
        userItemsRec.setAdapter(userItemsAdapter);


        //User items

        db.collection("UserItems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                UserItemsModel userItemsModel = document.toObject(UserItemsModel.class);
                                userItemsModelList.add(userItemsModel);
                                userItemsAdapter.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(AdminHomeActivity .this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Seller items

        sellerItemsRec.setLayoutManager(new LinearLayoutManager(AdminHomeActivity.this,RecyclerView.VERTICAL,false));
        sellerItemsModelList = new ArrayList<>();
        sellerItemsAdapter = new SellerItemsAdapter(AdminHomeActivity.this,sellerItemsModelList);
        sellerItemsRec.setAdapter(sellerItemsAdapter);


        //Seller items

        db.collection("SellerItems")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SellerItemsModel sellerItemsModel = document.toObject(SellerItemsModel.class);
                                sellerItemsModelList.add(sellerItemsModel);
                                sellerItemsAdapter.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(AdminHomeActivity .this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void navigationopen(View view) {
        navigation.setVisibility(View.VISIBLE);
    }

    public void closenav(View view) {
        navigation.setVisibility(View.GONE);
    }

    public void navigationcls(View view) {
        navigation.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        customExitDialog();
    }
    private void customExitDialog() {
        final Dialog dialog = new Dialog(AdminHomeActivity.this);

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

    public void adminlogout(View view) {
        customLogoutDialog();

    }

    private void customLogoutDialog() {
        final Dialog dialog = new Dialog(AdminHomeActivity.this);

        dialog.setContentView(R.layout.custom_logout_dialodue);

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
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminHomeActivity.this, welcome_activity.class));
                Toast.makeText(AdminHomeActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();

            }
        });
        dialog.show();

    }

    public void adminhome(View view) {
        Intent i = new Intent(AdminHomeActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }

    public void adminprofile(View view) {
        Intent i = new Intent(AdminHomeActivity.this, AdminProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void adminsettings(View view) {
        Intent i = new Intent(AdminHomeActivity.this, AdminSettingsActivity.class);
        startActivity(i);
        finish();
    }

    public void adminabout(View view) {
        Intent i = new Intent(AdminHomeActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }

    public void usernavopen(View view) {
        navigation1.setVisibility(View.VISIBLE);
        arrowdown.setVisibility(View.GONE);
        arrowup.setVisibility(View.VISIBLE);
    }

    public void usernavcls(View view) {
        navigation1.setVisibility(View.GONE);
        arrowdown.setVisibility(View.VISIBLE);
        arrowup.setVisibility(View.GONE);
    }

    public void sellernavopen(View view) {
        navigation2.setVisibility(View.VISIBLE);
        arrowdown1.setVisibility(View.GONE);
        arrowup1.setVisibility(View.VISIBLE);
    }

    public void sellernavcls(View view) {
        navigation2.setVisibility(View.GONE);
        arrowdown1.setVisibility(View.VISIBLE);
        arrowup1.setVisibility(View.GONE);
    }

    public void bothnavopen(View view) {
        navigation3.setVisibility(View.VISIBLE);
        arrowdown2.setVisibility(View.GONE);
        arrowup2.setVisibility(View.VISIBLE);
    }

    public void bothnavcls(View view) {
        navigation3.setVisibility(View.GONE);
        arrowdown2.setVisibility(View.VISIBLE);
        arrowup2.setVisibility(View.GONE);
    }

    public void logindetails(View view) {
        Intent i = new Intent(AdminHomeActivity.this, AllAdminAccessDataActivity.class);
        startActivity(i);
        finish();
    }

    public void productdetails(View view) {
        Intent i = new Intent(AdminHomeActivity.this, ProductDetailsAdminActivity.class);
        startActivity(i);
        finish();
    }
}