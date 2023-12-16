package com.example.demo_app.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.adapters.AdminBakeryAdapter;
import com.example.demo_app.adapters.AdminBreakFastAdapter;
import com.example.demo_app.adapters.AdminDrinkAdapter;
import com.example.demo_app.adapters.AdminFruitAdapter;
import com.example.demo_app.adapters.AdminMeatAdapter;
import com.example.demo_app.adapters.AdminVegAdapter;
import com.example.demo_app.models.AdminBakeryModel;
import com.example.demo_app.models.AdminBreakFastModel;
import com.example.demo_app.models.AdminDrinkModel;
import com.example.demo_app.models.AdminFruitModel;
import com.example.demo_app.models.AdminMeatModel;
import com.example.demo_app.models.AdminVegetableModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsAdminActivity extends AppCompatActivity {

    FirebaseFirestore db;
    //bakery items
    List<AdminBakeryModel> adminBakeryModelList;
    AdminBakeryAdapter adminBakeryAdapter;
    //breakfast items
    List<AdminBreakFastModel> adminBreakFastModelList;
    AdminBreakFastAdapter adminBreakFastAdapter;
    //drink items
    List<AdminDrinkModel> adminDrinkModelList;
    AdminDrinkAdapter adminDrinkAdapter;
    //meat items
    List<AdminMeatModel> adminMeatModelList;
    AdminMeatAdapter adminMeatAdapter;
    //fruit items
    List<AdminFruitModel> adminFruitModelList;
    AdminFruitAdapter adminFruitAdapter;
    //vegetable items
    List<AdminVegetableModel> adminVegetableModelList;
    AdminVegAdapter adminVegAdapter;
    RecyclerView bakeryItemsRec,breakfastItems_rec,drinkItems_rec,fruitItems_rec,meatItems_rec,vegetableItems_rec;

    LinearLayout navigation,navigation1,navigation2,navigation3,navigation4,navigation5;
    Button arrowdown,arrowup,arrowdown1,arrowup1,arrowdown2,arrowup2,arrowdown3,arrowup3,arrowdown4,arrowup4,arrowdown5,arrowup5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_admin);

        navigation = findViewById(R.id.navigation);
        navigation1 = findViewById(R.id.navigation1);
        navigation2 = findViewById(R.id.navigation2);
        navigation3 = findViewById(R.id.navigation3);
        navigation4 = findViewById(R.id.navigation4);
        navigation5 = findViewById(R.id.navigation5);
        arrowdown = findViewById(R.id.arrowdown);
        arrowup = findViewById(R.id.arrowup);
        arrowdown1 = findViewById(R.id.arrowdown1);
        arrowup1 = findViewById(R.id.arrowup1);
        arrowdown2 = findViewById(R.id.arrowdown2);
        arrowup2 = findViewById(R.id.arrowup2);
        arrowdown3 = findViewById(R.id.arrowdown3);
        arrowup3 = findViewById(R.id.arrowup3);
        arrowdown4 = findViewById(R.id.arrowdown4);
        arrowup4 = findViewById(R.id.arrowup4);
        arrowdown5 = findViewById(R.id.arrowdown5);
        arrowup5 = findViewById(R.id.arrowup5);

        db = FirebaseFirestore.getInstance();
        bakeryItemsRec = findViewById(R.id.bakeryItems_rec);
        breakfastItems_rec = findViewById(R.id.breakfastItems_rec);
        drinkItems_rec = findViewById(R.id.drinkItems_rec);
        fruitItems_rec = findViewById(R.id.fruitItems_rec);
        meatItems_rec = findViewById(R.id.meatItems_rec);
        vegetableItems_rec = findViewById(R.id.vegetableItems_rec);

        //bakery items
        bakeryItemsRec.setLayoutManager(new LinearLayoutManager(ProductDetailsAdminActivity.this,RecyclerView.VERTICAL,false));
        adminBakeryModelList = new ArrayList<>();
        adminBakeryAdapter = new AdminBakeryAdapter(ProductDetailsAdminActivity.this,adminBakeryModelList);
        bakeryItemsRec.setAdapter(adminBakeryAdapter);


        db.collection("BakeryEggsandBread")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminBakeryModel adminBakeryModel = document.toObject(AdminBakeryModel.class);
                                adminBakeryModelList.add(adminBakeryModel);
                                adminBakeryAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(ProductDetailsAdminActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //breakfast items
        breakfastItems_rec.setLayoutManager(new LinearLayoutManager(ProductDetailsAdminActivity.this,RecyclerView.VERTICAL,false));
        adminBreakFastModelList = new ArrayList<>();
        adminBreakFastAdapter = new AdminBreakFastAdapter(ProductDetailsAdminActivity.this,adminBreakFastModelList);
        breakfastItems_rec.setAdapter(adminBreakFastAdapter);


        db.collection("CerealsandBreakFastFood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminBreakFastModel adminBreakFastModel = document.toObject(AdminBreakFastModel.class);
                                adminBreakFastModelList.add(adminBreakFastModel);
                                adminBreakFastAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(ProductDetailsAdminActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //drink items
        drinkItems_rec.setLayoutManager(new LinearLayoutManager(ProductDetailsAdminActivity.this,RecyclerView.VERTICAL,false));
        adminDrinkModelList = new ArrayList<>();
        adminDrinkAdapter = new AdminDrinkAdapter(ProductDetailsAdminActivity.this,adminDrinkModelList);
        drinkItems_rec.setAdapter(adminDrinkAdapter);

        db.collection("Drinks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminDrinkModel adminDrinkModel = document.toObject(AdminDrinkModel.class);
                                adminDrinkModelList.add(adminDrinkModel);
                                adminDrinkAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(ProductDetailsAdminActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //fruit items
        fruitItems_rec.setLayoutManager(new LinearLayoutManager(ProductDetailsAdminActivity.this,RecyclerView.VERTICAL,false));
        adminFruitModelList = new ArrayList<>();
        adminFruitAdapter = new AdminFruitAdapter(ProductDetailsAdminActivity.this,adminFruitModelList);
        fruitItems_rec.setAdapter(adminFruitAdapter);

        db.collection("Fruits")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminFruitModel adminFruitModel = document.toObject(AdminFruitModel.class);
                                adminFruitModelList.add(adminFruitModel);
                                adminFruitAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(ProductDetailsAdminActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //meat items
        meatItems_rec.setLayoutManager(new LinearLayoutManager(ProductDetailsAdminActivity.this,RecyclerView.VERTICAL,false));
        adminMeatModelList = new ArrayList<>();
        adminMeatAdapter = new AdminMeatAdapter(ProductDetailsAdminActivity.this,adminMeatModelList);
        meatItems_rec.setAdapter(adminMeatAdapter);

        db.collection("MeatandSeaFoods")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminMeatModel adminMeatModel = document.toObject(AdminMeatModel.class);
                                adminMeatModelList.add(adminMeatModel);
                                adminMeatAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(ProductDetailsAdminActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //vegetable items
        vegetableItems_rec.setLayoutManager(new LinearLayoutManager(ProductDetailsAdminActivity.this,RecyclerView.VERTICAL,false));
        adminVegetableModelList = new ArrayList<>();
        adminVegAdapter = new AdminVegAdapter(ProductDetailsAdminActivity.this,adminVegetableModelList);
        vegetableItems_rec.setAdapter(adminVegAdapter);

        db.collection("Vegetables")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AdminVegetableModel adminVegetableModel = document.toObject(AdminVegetableModel.class);
                                adminVegetableModelList.add(adminVegetableModel);
                                adminVegAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(ProductDetailsAdminActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ProductDetailsAdminActivity.this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }

    public void bakeryopen(View view) {
        navigation.setVisibility(View.VISIBLE);
        arrowdown.setVisibility(View.GONE);
        arrowup.setVisibility(View.VISIBLE);
    }

    public void bakerycls(View view) {
        navigation.setVisibility(View.GONE);
        arrowdown.setVisibility(View.VISIBLE);
        arrowup.setVisibility(View.GONE);
    }

    public void breakfastopen(View view) {
        navigation1.setVisibility(View.VISIBLE);
        arrowdown1.setVisibility(View.GONE);
        arrowup1.setVisibility(View.VISIBLE);
    }

    public void breakfastcls(View view) {
        navigation1.setVisibility(View.GONE);
        arrowdown1.setVisibility(View.VISIBLE);
        arrowup1.setVisibility(View.GONE);
    }

    public void drinkopen(View view) {
        navigation2.setVisibility(View.VISIBLE);
        arrowdown2.setVisibility(View.GONE);
        arrowup2.setVisibility(View.VISIBLE);
    }

    public void drinkcls(View view) {
        navigation2.setVisibility(View.GONE);
        arrowdown2.setVisibility(View.VISIBLE);
        arrowup2.setVisibility(View.GONE);
    }

    public void fruitopen(View view) {
        navigation3.setVisibility(View.VISIBLE);
        arrowdown3.setVisibility(View.GONE);
        arrowup3.setVisibility(View.VISIBLE);
    }

    public void fruitcls(View view) {
        navigation3.setVisibility(View.GONE);
        arrowdown3.setVisibility(View.VISIBLE);
        arrowup3.setVisibility(View.GONE);
    }

    public void meatopen(View view) {
        navigation4.setVisibility(View.VISIBLE);
        arrowdown4.setVisibility(View.GONE);
        arrowup4.setVisibility(View.VISIBLE);
    }

    public void meatcls(View view) {
        navigation4.setVisibility(View.GONE);
        arrowdown4.setVisibility(View.VISIBLE);
        arrowup4.setVisibility(View.GONE);
    }

    public void vegopen(View view) {
        navigation5.setVisibility(View.VISIBLE);
        arrowdown5.setVisibility(View.GONE);
        arrowup5.setVisibility(View.VISIBLE);
    }

    public void vegcls(View view) {
        navigation5.setVisibility(View.GONE);
        arrowdown5.setVisibility(View.VISIBLE);
        arrowup5.setVisibility(View.GONE);
    }
}