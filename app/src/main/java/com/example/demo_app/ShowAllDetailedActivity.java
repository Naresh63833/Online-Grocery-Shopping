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
import com.example.demo_app.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ShowAllDetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg, addItem, removeItem;
    TextView price, rating, descriptipn,shop_name,shop_location;
    Button addToCart, buyNow;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ShowAllModel showAllModel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_detailed);

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



        final Object object = getIntent().getSerializableExtra("detailshowall");
        if (object instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) object;
        }

        if (showAllModel != null) {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            rating.setText(showAllModel.getRating());
            descriptipn.setText(showAllModel.getDescription());
            price.setText("Price :₹" + showAllModel.getPrice() + "/litre");
            quantity.setText(String.valueOf(totalQuantity));
            shop_name.setText(showAllModel.getShop_name());
            shop_location.setText(showAllModel.getShop_location());
            //price.setText(String.valueOf(navCategoryDetailedModel.getPrice()));

            totalPrice = showAllModel.getPrice() * this.totalQuantity;

            if (showAllModel.getType().equals("biscuit")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/packet");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("bread")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/packet");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("egg")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/dozen");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("breakfast")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("juice")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/litre");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("soda")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/litre");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("apple")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("avocados")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("banana")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("citrus")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("melon")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("stonefruit")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("tropicalfruit")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("allium")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("cruciferous")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("edible")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("leafygreen")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/piece");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("gourd")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("root")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("apple")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("bakery")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/packet or dozen");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("fruit")) {
                price.setText("Price :$" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("drink")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/litre");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("vegetable")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("meat")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("fish")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("chicken")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("goat")) {
                price.setText("Price :₹" + showAllModel.getPrice() + "/kg");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
        }
        //Add to cart
        ShowAllModel showAllModel1 = showAllModel ;
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
                cartMap.put("productName",showAllModel1.getName());
                cartMap.put("productPrice", price.getText().toString());
                cartMap.put("currentDate", saveCurrentDate);
                cartMap.put("currentTime", saveCurrentTime);
                cartMap.put("totalQuantity", quantity.getText().toString());
                cartMap.put("totalPrice", totalPrice);
                cartMap.put("shop_name", showAllModel.getShop_name());
                cartMap.put("shop_location", showAllModel.getShop_location());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(ShowAllDetailedActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
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
            totalPrice = showAllModel.getPrice() * totalQuantity;
        }
    }

    public void removeItem(View view) {
        if (totalQuantity > 1) {
            totalQuantity--;
            quantity.setText(String.valueOf(totalQuantity));
            totalPrice = showAllModel.getPrice() * totalQuantity;
        }
    }
}