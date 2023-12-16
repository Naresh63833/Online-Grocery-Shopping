package com.example.demo_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.demo_app.models.ShowAllBreakfastModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ShowAllDetailed2Activity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg, addItem, removeItem;
    TextView price, rating, descriptipn,shop_name,shop_location;
    Button addToCart, buyNow;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ShowAllBreakfastModel showAllBreakfastModel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_detailed2);

        detailedImg = findViewById(R.id.image);
        addItem = findViewById(R.id.addItem);
        removeItem = findViewById(R.id.removeItem);
        price = findViewById(R.id.price_cat);
        rating = findViewById(R.id.detailed_rating);
        descriptipn = findViewById(R.id.detailed_description);
        quantity = findViewById(R.id.Quantity);
        shop_name = findViewById(R.id.shop_name);
        shop_location = findViewById(R.id.shop_location);

        addToCart = findViewById(R.id.addTocart);
        buyNow = findViewById(R.id.buyNow);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        final Object object = getIntent().getSerializableExtra("detailshowallbreak");
        if (object instanceof ShowAllBreakfastModel) {
            showAllBreakfastModel = (ShowAllBreakfastModel) object;
        }

        if (showAllBreakfastModel != null) {
            Glide.with(getApplicationContext()).load(showAllBreakfastModel.getImg_url()).into(detailedImg);
            rating.setText(showAllBreakfastModel.getRating());
            descriptipn.setText(showAllBreakfastModel.getDescription());
            price.setText("Price :₹" + showAllBreakfastModel.getPrice() + "/litre");
            quantity.setText(String.valueOf(totalQuantity));
            shop_name.setText(showAllBreakfastModel.getShop_name());
            shop_location.setText(showAllBreakfastModel.getShop_location());
            //price.setText(String.valueOf(navCategoryDetailedModel.getPrice()));

            totalPrice = showAllBreakfastModel.getPrice() * this.totalQuantity;

            if (showAllBreakfastModel.getType().equals("breakfast")) {
                price.setText("Price :$" + showAllBreakfastModel.getPrice() + "/kg");
                totalPrice = showAllBreakfastModel.getPrice() * totalQuantity;
            }
        }
        //Add to cart
        ShowAllBreakfastModel showAllBreakfastModel1 = showAllBreakfastModel ;
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveCurrentDate, saveCurrentTime;

                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM - dd - yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());

                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName",showAllBreakfastModel1.getName());
                cartMap.put("productPrice", price.getText().toString());
                cartMap.put("currentDate", saveCurrentDate);
                cartMap.put("currentTime", saveCurrentTime);
                cartMap.put("totalQuantity", quantity.getText().toString());
                cartMap.put("totalPrice", totalPrice);
                cartMap.put("shop_name", showAllBreakfastModel.getShop_name());
                cartMap.put("shop_location", showAllBreakfastModel.getShop_location());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(ShowAllDetailed2Activity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
    }
    public void additem(View view) {
        if (totalQuantity < 10) {
            totalQuantity++;
            quantity.setText(String.valueOf(totalQuantity));
            totalPrice = showAllBreakfastModel.getPrice() * totalQuantity;
        }
    }

    public void removeItem(View view) {
        if (totalQuantity > 1) {
            totalQuantity--;
            quantity.setText(String.valueOf(totalQuantity));
            totalPrice = showAllBreakfastModel.getPrice() * totalQuantity;
        }
    }
    }