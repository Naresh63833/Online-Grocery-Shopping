package com.example.demo_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.activities.welcome_activity;
import com.example.demo_app.adapters.Bakery1Adapter;
import com.example.demo_app.adapters.BakeryAdapter;
import com.example.demo_app.adapters.BreakFastAdapter;
import com.example.demo_app.adapters.DealsAdapter;
import com.example.demo_app.adapters.DrinkAdapter;
import com.example.demo_app.adapters.ExploreAdapter;
import com.example.demo_app.adapters.FruitAdapter;
import com.example.demo_app.adapters.HomeCategoryAdapter;
import com.example.demo_app.adapters.HomeShowAllAdapter;
import com.example.demo_app.adapters.MeatAdapter;
import com.example.demo_app.adapters.PopularAdapters;
import com.example.demo_app.adapters.RecommendedAdapter;
import com.example.demo_app.adapters.VegetableAdapter;
import com.example.demo_app.adapters.ViewAllAdapter;
import com.example.demo_app.models.Bakery1Model;
import com.example.demo_app.models.BakeryModel;
import com.example.demo_app.models.BreakfastModel;
import com.example.demo_app.models.DealsModel;
import com.example.demo_app.models.DrinkModel;
import com.example.demo_app.models.ExploreModel;
import com.example.demo_app.models.FruitModel;
import com.example.demo_app.models.HomeCategoryModel;
import com.example.demo_app.models.HomeShowAllModel;
import com.example.demo_app.models.MeatModel;
import com.example.demo_app.models.PopularModel;
import com.example.demo_app.models.RecommendedModel;
import com.example.demo_app.models.VegetableModel;
import com.example.demo_app.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity {

    LinearLayout navigation,navigation2;
    RecyclerView dealsRec,popularRec,exploreRec,recommendeRec,homecategoryRec,bakeryRec,bakeryRec1,brealFastRec,drinkRec,fruirRec,meatRec,vegetableRec,showAllHomeRec;
    FirebaseFirestore db;
    ScrollView scrollView;
    ProgressBar progressBar;

    TextView viewAllPopular,viewAllEXplore,viewAllRecommend;

    //Deals items
    List<DealsModel> dealsModelList;
    DealsAdapter dealsAdapter;

    //Popular items
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    // Search view
    EditText search_box;
    private  List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;

    //Explore items
    List<ExploreModel> exploreModelList;
    ExploreAdapter exploreAdapter;

    //Recommended items
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;
    //Category items
    List<HomeCategoryModel> homeCategoryModelList;
    HomeCategoryAdapter homeCategoryAdapter;
    //bakery items
    List<BakeryModel> bakeryModelList;
    BakeryAdapter bakeryAdapter;
    //bakery items
    List<Bakery1Model> bakery1ModelList;
    Bakery1Adapter bakery1Adapter;
    //breakfast items
    List<BreakfastModel> breakfastModelList;
    BreakFastAdapter breakFastAdapter;
    //Drink items
    List<DrinkModel> drinkModelList;
    DrinkAdapter drinkAdapter;
    //Fruit items
    List<FruitModel> fruitModelList;
    FruitAdapter fruitAdapter;
    //meat items
    List<MeatModel> meatModelList;
    MeatAdapter meatAdapter;
    //vegetable items
    List<VegetableModel> vegetableModelList;
    VegetableAdapter vegetableAdapter;

    //show All items
    List<HomeShowAllModel> homeShowAllModelList;
    HomeShowAllAdapter homeShowAllAdapter;


    //Header
    TextView headerName,headerEmail;
    CircleImageView headerImg;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        navigation = findViewById(R.id.navigation);
        navigation2 = findViewById(R.id.navigation2);


        dealsRec = findViewById(R.id.deals_rec);
        popularRec = findViewById(R.id.pop_rec);
        exploreRec = findViewById(R.id.explore_rec);
        recommendeRec = findViewById(R.id.recommended_rec);
        homecategoryRec = findViewById(R.id.home_category_rec);
        bakeryRec = findViewById(R.id.bakery_rec);
        bakeryRec1 = findViewById(R.id.bakery1_rec);
        brealFastRec = findViewById(R.id.breakfast_rec);
        drinkRec = findViewById(R.id.drink_rec);
        fruirRec = findViewById(R.id.fruit_rec);
        meatRec = findViewById(R.id.meat_rec);
        vegetableRec = findViewById(R.id.vegetable_rec);
        showAllHomeRec = findViewById(R.id.showall_home_rec);

        scrollView = findViewById(R.id.scroll_view);
        progressBar = findViewById(R.id.progress_bar);

        viewAllPopular = findViewById(R.id.view_all_popular);
        viewAllEXplore = findViewById(R.id.view_all_explore);
        viewAllRecommend = findViewById(R.id.view_all_recommended);

        viewAllPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, ShowAllActivity.class);
                startActivity(i);
                finish();
            }
        });
        viewAllEXplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, ShowAllActivity.class);
                startActivity(i);
                finish();
            }
        });
        viewAllRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, ShowAllActivity.class);
                startActivity(i);
                finish();
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        db = FirebaseFirestore.getInstance();

        //header
        headerEmail = findViewById(R.id.nav_header_email);

        String email = auth.getCurrentUser().getEmail();
        headerEmail.setText(email);


        //Deals items

        dealsRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        dealsModelList = new ArrayList<>();
        dealsAdapter = new DealsAdapter(home.this, dealsModelList);
        dealsRec.setAdapter(dealsAdapter);


        //Deals items

        db.collection("Deals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DealsModel dealsModel = document.toObject(DealsModel.class);
                                dealsModelList.add(dealsModel);
                                dealsAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Popular items

        popularRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(home.this, popularModelList);
        popularRec.setAdapter(popularAdapters);

        //Popular items

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Explore items

        exploreRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.HORIZONTAL, false));
        exploreModelList = new ArrayList<>();
        exploreAdapter = new ExploreAdapter(home.this, exploreModelList);
        exploreRec.setAdapter(exploreAdapter);


        //Explore items

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ExploreModel exploreModel = document.toObject(ExploreModel.class);
                                exploreModelList.add(exploreModel);
                                exploreAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Recommended items

        recommendeRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.HORIZONTAL, false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(home.this, recommendedModelList);
        recommendeRec.setAdapter(recommendedAdapter);


        //Recommended items

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //Category items

        homecategoryRec.setLayoutManager(new GridLayoutManager(this, 2));
        homeCategoryModelList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(home.this, homeCategoryModelList);
        homecategoryRec.setAdapter(homeCategoryAdapter);


        //Category items

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategoryModel homeCategoryModel = document.toObject(HomeCategoryModel.class);
                                homeCategoryModelList.add(homeCategoryModel);
                                homeCategoryAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Bakery items

        bakeryRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        bakeryModelList = new ArrayList<>();
        bakeryAdapter = new BakeryAdapter(home.this, bakeryModelList);
        bakeryRec.setAdapter(bakeryAdapter);

        db.collection("Bakery")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BakeryModel bakeryModel = document.toObject(BakeryModel.class);
                                bakeryModelList.add(bakeryModel);
                                bakeryAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Bakery1 items

        bakeryRec1.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        bakery1ModelList = new ArrayList<>();
        bakery1Adapter = new Bakery1Adapter(home.this, bakery1ModelList);
        bakeryRec1.setAdapter(bakery1Adapter);

        db.collection("Bakery")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Bakery1Model bakery1Model = document.toObject(Bakery1Model.class);
                                bakery1ModelList.add(bakery1Model);
                                bakery1Adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Breakfast items

        brealFastRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        breakfastModelList = new ArrayList<>();
        breakFastAdapter = new BreakFastAdapter(home.this, breakfastModelList);
        brealFastRec.setAdapter(breakFastAdapter);

        db.collection("Breakfast")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BreakfastModel breakfastModel = document.toObject(BreakfastModel.class);
                                breakfastModelList.add(breakfastModel);
                                breakFastAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Drink items

        drinkRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        drinkModelList = new ArrayList<>();
        drinkAdapter = new DrinkAdapter(home.this, drinkModelList);
        drinkRec.setAdapter(drinkAdapter);

        db.collection("Drinks1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DrinkModel drinkModel = document.toObject(DrinkModel.class);
                                drinkModelList.add(drinkModel);
                                drinkAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Fruit items

        fruirRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        fruitModelList = new ArrayList<>();
        fruitAdapter = new FruitAdapter(home.this, fruitModelList);
        fruirRec.setAdapter(fruitAdapter);

        db.collection("Fruits1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FruitModel fruitModel = document.toObject(FruitModel.class);
                                fruitModelList.add(fruitModel);
                                fruitAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Meat items

        meatRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        meatModelList = new ArrayList<>();
        meatAdapter = new MeatAdapter(home.this, meatModelList);
        meatRec.setAdapter(meatAdapter);

        db.collection("Meat")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MeatModel meatModel = document.toObject(MeatModel.class);
                                meatModelList.add(meatModel);
                                meatAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Vegetable items

        vegetableRec.setLayoutManager(new LinearLayoutManager(home.this, RecyclerView.VERTICAL, false));
        vegetableModelList = new ArrayList<>();
        vegetableAdapter = new VegetableAdapter(home.this, vegetableModelList);
        vegetableRec.setAdapter(vegetableAdapter);

        db.collection("Vegetables1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                VegetableModel vegetableModel = document.toObject(VegetableModel.class);
                                vegetableModelList.add(vegetableModel);
                                vegetableAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(home.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Show All
        showAllHomeRec.setLayoutManager(new GridLayoutManager(this, 2));

        homeShowAllModelList = new ArrayList<>();
        homeShowAllAdapter = new HomeShowAllAdapter(this, homeShowAllModelList);
        showAllHomeRec.setAdapter(homeShowAllAdapter);


        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                HomeShowAllModel homeShowAllModel = doc.toObject(HomeShowAllModel.class);
                                homeShowAllModelList.add(homeShowAllModel);
                                homeShowAllAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });


        //Search View
        recyclerViewSearch = findViewById(R.id.search_rec);
        search_box = findViewById(R.id.search_box);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getApplicationContext(), viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProducts(s.toString().trim());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProducts(s.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProducts(s.toString().trim());
                }

            }
        });
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.toString().trim().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString().trim());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchProducts(String name) {
        if (!name.isEmpty()){
            db.collection("AllProducts").whereEqualTo("name",name).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

    private void searchProduct(String type) {
        if (!type.isEmpty()){
            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

    public void navopen(View view) {
        navigation.setVisibility(View.VISIBLE);
    }

    public void navcls(View view) {
        navigation.setVisibility(View.GONE);
    }

    public void homenav(View view) {
        Intent i = new Intent(home.this,home.class);
        startActivity(i);
        finish();
    }

    public void profilenav(View view) {
        Intent i = new Intent(home.this,profile.class);
        startActivity(i);
        finish();
    }

    public void categorynav(View view) {
        Intent i = new Intent(home.this,category.class);
        startActivity(i);
        finish();
    }

    public void newproductnav(View view) {
        Intent i = new Intent(home.this,New_products.class);
        startActivity(i);
        finish();
    }

    public void mycartnav(View view) {
        Intent i = new Intent(home.this,My_Cart_Activity.class);
        startActivity(i);
        finish();
    }

    public void myorder(View view) {
        Intent i = new Intent(home.this,myorders.class);
        startActivity(i);
        finish();
    }

    public void home_bottom(View view) {
        Intent i = new Intent(home.this,home.class);
        startActivity(i);
        finish();
    }

    public void profile_bottom(View view) {
        Intent i = new Intent(home.this,profile.class);
        startActivity(i);
        finish();
    }

    public void cart_bottom(View view) {
        Intent i = new Intent(home.this,My_Cart_Activity.class);
        startActivity(i);
        finish();
    }

    public void nav2_open(View view) {
        navigation2.setVisibility(View.VISIBLE);
    }

    public void nav2_cls(View view) {
        navigation2.setVisibility(View.GONE);
    }

    public void settings(View view) {
        Intent i = new Intent(home.this,settings_activity.class);
        startActivity(i);
        finish();
    }

    public void close(View view) {
        navigation2.setVisibility(View.GONE);
    }

    public void close1(View view) {
        navigation.setVisibility(View.GONE);
    }



    public void contact(View view) {
        Intent i = new Intent(home.this,contact_support.class);
        startActivity(i);
        finish();
    }

    public void feedback(View view) {
        Intent i = new Intent(home.this,feedback.class);
        startActivity(i);
        finish();
    }

    public void about(View view) {
        Intent i = new Intent(home.this,about.class);
        startActivity(i);
        finish();
    }

    public void dashboard(View view) {
        Intent i = new Intent(this,myorders.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {
        customExitDialog();
    }

    private void customExitDialog() {
        final Dialog dialog = new Dialog(home.this);

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



    public void logout(View view) {
        customLogoutDialog();
    }
    private void customLogoutDialog() {
        final Dialog dialog = new Dialog(home.this);

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
                startActivity(new Intent(home.this,welcome_activity.class));
                Toast.makeText(home.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();

            }
        });
        dialog.show();
    }


    public void categoryviewall(View view) {
        Intent i = new Intent(this,category.class);
        startActivity(i);
        finish();
    }

    public void offersnav(View view) {
        Intent i = new Intent(this,OffersActivity.class);
        startActivity(i);
        finish();
    }
}