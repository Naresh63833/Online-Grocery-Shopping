package com.example.demo_app.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.adapters.AdminAccessAdapter;
import com.example.demo_app.models.AdminAccessModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllAdminAccessDataActivity extends AppCompatActivity {

    FirebaseFirestore db;
    List<AdminAccessModel> adminAccessModelList;
    AdminAccessAdapter adminAccessAdapter;
    RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_admin_access_data);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.adminaccess_rec);



        recyclerView.setLayoutManager(new LinearLayoutManager(AllAdminAccessDataActivity.this,RecyclerView.VERTICAL,false));
        adminAccessModelList = new ArrayList<>();
        adminAccessAdapter = new AdminAccessAdapter(AllAdminAccessDataActivity.this,adminAccessModelList);
        recyclerView.setAdapter(adminAccessAdapter);


        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminAccessModel adminAccessModel = document.toObject(AdminAccessModel.class);
                                adminAccessModelList.add(adminAccessModel);
                                adminAccessAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(AllAdminAccessDataActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void adminback(View view) {
        Intent i = new Intent(AllAdminAccessDataActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AllAdminAccessDataActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }
}