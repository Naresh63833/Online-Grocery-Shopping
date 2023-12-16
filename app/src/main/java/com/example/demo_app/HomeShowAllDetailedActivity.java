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
import com.example.demo_app.models.HomeShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class HomeShowAllDetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg, addItem, removeItem;
    TextView price, rating, descriptipn,shop_name,shop_location;
    Button addToCart, buyNow;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    HomeShowAllModel homeShowAllModel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_show_all_detailed);

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



        final Object object = getIntent().getSerializableExtra("detailshowallhome");
        if (object instanceof HomeShowAllModel) {
            homeShowAllModel = (HomeShowAllModel) object;
        }

        if (homeShowAllModel != null) {
            Glide.with(getApplicationContext()).load(homeShowAllModel.getImg_url()).into(detailedImg);
            rating.setText(homeShowAllModel.getRating());
            descriptipn.setText(homeShowAllModel.getDescription());
            price.setText("Price :₹" + homeShowAllModel.getPrice() + "/litre");
            quantity.setText(String.valueOf(totalQuantity));
            shop_name.setText(homeShowAllModel.getShop_name());
            shop_location.setText(homeShowAllModel.getShop_location());
            //price.setText(String.valueOf(navCategoryDetailedModel.getPrice()));

            totalPrice = homeShowAllModel.getPrice() * this.totalQuantity;

            if (homeShowAllModel.getType().equals("biscuit")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/packet");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("bread")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/packet");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("egg")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/dozen");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("breakfast")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("juice")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/litre");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("soda")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/litre");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("apple")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("avocados")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("banana")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("citrus")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("melon")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("stonefruit")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("tropicalfruit")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("allium")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("cruciferous")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("edible")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("leafygreen")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/piece");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("gourd")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("root")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("apple")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("bakery")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/packet or dozen");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("fruit")) {
                price.setText("Price :$" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("drink")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/litre");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("vegetable")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("meat")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("fish")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("chicken")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
            if (homeShowAllModel.getType().equals("goat")) {
                price.setText("Price :₹" + homeShowAllModel.getPrice() + "/kg");
                totalPrice = homeShowAllModel.getPrice() * totalQuantity;
            }
        }
        //Add to cart
        HomeShowAllModel homeShowAllModel1 = homeShowAllModel ;
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
                cartMap.put("productName",homeShowAllModel1.getName());
                cartMap.put("productPrice", price.getText().toString());
                cartMap.put("currentDate", saveCurrentDate);
                cartMap.put("currentTime", saveCurrentTime);
                cartMap.put("totalQuantity", quantity.getText().toString());
                cartMap.put("totalPrice", totalPrice);
                cartMap.put("shop_name", homeShowAllModel.getShop_name());
                cartMap.put("shop_location", homeShowAllModel.getShop_location());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(HomeShowAllDetailedActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
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
            totalPrice = homeShowAllModel.getPrice() * totalQuantity;
        }
    }
    public void removeItem(View view) {
        if (totalQuantity > 1) {
            totalQuantity--;
            quantity.setText(String.valueOf(totalQuantity));
            totalPrice = homeShowAllModel.getPrice() * totalQuantity;
        }

    }
}