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
import com.example.demo_app.models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedCategoryActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg, addItem, removeItem;
    TextView price, rating, descriptipn,shop_name,shop_location;
    Button addToCart, buyNow;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    NavCategoryDetailedModel navCategoryDetailedModel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_category);

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



        final Object object = getIntent().getSerializableExtra("detailcat");
        if (object instanceof NavCategoryDetailedModel) {
            navCategoryDetailedModel = (NavCategoryDetailedModel) object;
        }

        if (navCategoryDetailedModel != null) {
            Glide.with(getApplicationContext()).load(navCategoryDetailedModel.getImg_url()).into(detailedImg);
            rating.setText(navCategoryDetailedModel.getRating());
            descriptipn.setText(navCategoryDetailedModel.getDescription());
            price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/litre");
            quantity.setText(String.valueOf(totalQuantity));
            shop_name.setText(navCategoryDetailedModel.getShop_name());
            shop_location.setText(navCategoryDetailedModel.getShop_location());
            //price.setText(String.valueOf(navCategoryDetailedModel.getPrice()));

            totalPrice = navCategoryDetailedModel.getPrice() * this.totalQuantity;

            if (navCategoryDetailedModel.getType().equals("biscuit")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/packet");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("bread")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/packet");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("egg")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/dozen");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("breakfast")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("juice")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/litre");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("soda")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/litre");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("apple")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("avocados")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("banana")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("citrus")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("melon")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("stonefruit")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("tropicalfruit")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("allium")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("cruciferous")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("edible")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("leafygreen")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/piece");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("gourd")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("root")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("apple")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("bakery")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/packet or dozen");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("fruit")) {
                price.setText("Price :$" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("drink")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/litre");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("vegetable")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("meat")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("fish")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("chicken")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }
            if (navCategoryDetailedModel.getType().equals("goat")) {
                price.setText("Price :₹" + navCategoryDetailedModel.getPrice() + "/kg");
                totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
            }

        }
        //Add to cart
        NavCategoryDetailedModel navCategoryDetailedModel1 = navCategoryDetailedModel ;
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
                    cartMap.put("productName",navCategoryDetailedModel.getName());
                    cartMap.put("productPrice", price.getText().toString());
                    cartMap.put("currentDate", saveCurrentDate);
                    cartMap.put("currentTime", saveCurrentTime);
                    cartMap.put("totalQuantity", quantity.getText().toString());
                    cartMap.put("totalPrice", totalPrice);
                    cartMap.put("shop_name", navCategoryDetailedModel.getShop_name());
                    cartMap.put("shop_location", navCategoryDetailedModel.getShop_location());

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Toast.makeText(DetailedCategoryActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
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
           totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
        }
    }

    public void removeItem(View view) {
        if (totalQuantity > 1) {
            totalQuantity--;
            quantity.setText(String.valueOf(totalQuantity));
           totalPrice = navCategoryDetailedModel.getPrice() * totalQuantity;
        }
    }
}