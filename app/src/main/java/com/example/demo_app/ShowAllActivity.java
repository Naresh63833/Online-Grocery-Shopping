package com.example.demo_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.adapters.ShowAllAdapter;
import com.example.demo_app.adapters.ShowAllBreakfastAdapter;
import com.example.demo_app.models.ShowAllBreakfastModel;
import com.example.demo_app.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView,breakFastRec;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;

    ShowAllBreakfastAdapter showAllBreakfastAdapter;
    List<ShowAllBreakfastModel> showAllBreakfastModelList;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        progressBar = findViewById(R.id.progress_bar_show);
        progressBar.setVisibility(View.VISIBLE);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.show_all_rec);
        breakFastRec = findViewById(R.id.cereals_rec);

        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);


        firestore.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                showAllModelList.add(showAllModel);
                                showAllAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }
                        }
                    }
                });

        //Breakfast items

        breakFastRec.setVisibility(View.GONE);
        breakFastRec.setLayoutManager(new GridLayoutManager(this,2));
        showAllBreakfastModelList = new ArrayList<>();
        showAllBreakfastAdapter = new ShowAllBreakfastAdapter(ShowAllActivity.this,showAllBreakfastModelList);
        breakFastRec.setAdapter(showAllBreakfastAdapter);

        firestore.collection("CerealsandBreakFastFood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ShowAllBreakfastModel breakfastModel = document.toObject(ShowAllBreakfastModel.class);
                                showAllBreakfastModelList.add(breakfastModel);
                                showAllBreakfastAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                breakFastRec.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(ShowAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ShowAllActivity.this,home.class);
        startActivity(i);
        finish();
    }

    public void showallback(View view) {
        Intent i = new Intent(ShowAllActivity.this,home.class);
        startActivity(i);
        finish();
    }
}