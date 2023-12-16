package com.example.demo_app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.adapters.UserDetailsAdapter;
import com.example.demo_app.models.UserDetailsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UsersDetailsAdminActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    UserDetailsAdapter userDetailsAdapter;
    List<UserDetailsModel> userDetailsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        firestore = FirebaseFirestore.getInstance();
        String Type = getIntent().getStringExtra("Type");
        recyclerView = findViewById(R.id.userdetails_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userDetailsModelList = new ArrayList<>();
        userDetailsAdapter = new UserDetailsAdapter(this,userDetailsModelList);
        recyclerView.setAdapter(userDetailsAdapter);

        //Getting User
        if(Type != null && Type.equalsIgnoreCase("User")){
            firestore.collection("Users").whereEqualTo("Type","User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        UserDetailsModel userDetailsModel = documentSnapshot.toObject(UserDetailsModel.class);
                        userDetailsModelList.add(userDetailsModel);
                        userDetailsAdapter.notifyDataSetChanged();

                    }
                }
            });
        }
    }


    public void adminback(View view) {
        Intent i = new Intent(UsersDetailsAdminActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UsersDetailsAdminActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }
}