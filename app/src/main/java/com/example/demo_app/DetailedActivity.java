package com.example.demo_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.demo_app.models.MyCartModel;
import com.example.demo_app.models.ShowAllModel;
import com.example.demo_app.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg, addItem, removeItem;
    TextView price, rating, descriptipn,shop_name,shop_location;
    Button addToCart, buyNow;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;
    ShowAllModel showAllModel = null;
    MyCartModel myCartModel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        descriptipn = findViewById(R.id.detailed_description);
        quantity = findViewById(R.id.quantity);
        shop_name = findViewById(R.id.shop_name);
        shop_location = findViewById(R.id.shop_location);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_Now);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewAllModel != null) {
                    if (totalQuantity < 10) {
                        totalQuantity++;
                        quantity.setText(String.valueOf(totalQuantity));
                        totalPrice = viewAllModel.getPrice() * totalQuantity;
                    }if (showAllModel != null){
                        if (totalQuantity < 10) {
                            totalQuantity++;
                            quantity.setText(String.valueOf(totalQuantity));
                            totalPrice = showAllModel.getPrice() * totalQuantity;
                        }
                    }
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }
            }
        });

        
        // ViewAllModel viewAllModel = null ;

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        } else if (object instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) object;
        }

        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            descriptipn.setText(viewAllModel.getDescription());
            price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
            quantity.setText(String.valueOf(totalQuantity));
            shop_name.setText(viewAllModel.getShop_name());
            shop_location.setText(viewAllModel.getShop_location());
            //price.setText(String.valueOf(viewAllModel.getPrice()));

            totalPrice = viewAllModel.getPrice() * this.totalQuantity;

            if (viewAllModel.getType().equals("biscuit")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/packet");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("bread")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/packet");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("egg")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/dozen");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("breakfast")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("juice")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/litre");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("soda")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/litre");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("apple")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("avocados")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("banana")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("citrus")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("melon")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("stonefruit")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("tropicalfruit")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("allium")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("cruciferous")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("edible")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("leafygreen")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/piece");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("gourd")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("root")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("apple")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("bakery")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/packet or dozen");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("fruit")) {
                price.setText("Price :$" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("drink")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/litre");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("vegetable")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("meat")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("fish")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("chicken")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
            if (viewAllModel.getType().equals("goat")) {
                price.setText("Price :₹" + viewAllModel.getPrice() + "/kg");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
        }
        //Add to cart
        ViewAllModel finalViewAllModel = viewAllModel;
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (viewAllModel != null) {
                  String saveCurrentDate, saveCurrentTime;

                  Calendar calendar = Calendar.getInstance();

                  SimpleDateFormat currentDate = new SimpleDateFormat("MM - dd - yyyy");
                  saveCurrentDate = currentDate.format(calendar.getTime());

                  SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                  saveCurrentTime = currentTime.format(calendar.getTime());

                  final HashMap<String, Object> cartMap = new HashMap<>();
                  cartMap.put("productName", viewAllModel.getName());
                  cartMap.put("productPrice", price.getText().toString());
                  cartMap.put("currentDate", saveCurrentDate);
                  cartMap.put("currentTime", saveCurrentTime);
                  cartMap.put("totalQuantity", quantity.getText().toString());
                  cartMap.put("totalPrice", totalPrice);
                  cartMap.put("shop_name",viewAllModel.getShop_name());
                  cartMap.put("shop_location",viewAllModel.getShop_location());

                  firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                          .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                              @Override
                              public void onComplete(@NonNull Task<DocumentReference> task) {
                                  Toast.makeText(DetailedActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
                                  finish();
                              }
                          });
              } else if (showAllModel != null) {
                  String saveCurrentDate, saveCurrentTime;

                  Calendar calendar = Calendar.getInstance();

                  SimpleDateFormat currentDate = new SimpleDateFormat("MM - dd - yyyy");
                  saveCurrentDate = currentDate.format(calendar.getTime());

                  SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                  saveCurrentTime = currentTime.format(calendar.getTime());

                  final HashMap<String, Object> cartMap = new HashMap<>();
                  cartMap.put("productName", showAllModel.getName());
                  cartMap.put("productPrice", price.getText().toString());
                  cartMap.put("currentDate", saveCurrentDate);
                  cartMap.put("currentTime", saveCurrentTime);
                  cartMap.put("totalQuantity", quantity.getText().toString());
                  cartMap.put("totalPrice", totalPrice);
                  cartMap.put("shop_name",showAllModel.getShop_name());
                  cartMap.put("shop_location",showAllModel.getShop_location());

                  firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                          .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                              @Override
                              public void onComplete(@NonNull Task<DocumentReference> task) {
                                  Toast.makeText(DetailedActivity.this, "Added to a cart", Toast.LENGTH_SHORT).show();
                                  finish();
                              }
                          });
              }
            }
        });

        if (showAllModel != null) {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            rating.setText(showAllModel.getRating());
            descriptipn.setText(showAllModel.getDescription());
            price.setText("Price :$" + showAllModel.getPrice() + "/kg");
            quantity.setText(String.valueOf(totalQuantity));
            shop_name.setText(showAllModel.getShop_name());
            shop_location.setText(showAllModel.getShop_location());
            //price.setText(String.valueOf(viewAllModel.getPrice()));

            totalPrice = showAllModel.getPrice() * this.totalQuantity;

            if (showAllModel.getType().equals("egg")) {
                price.setText("Price :$" + showAllModel.getPrice() + "/dozen");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
            if (showAllModel.getType().equals("milk")) {
                price.setText("Price :$" + showAllModel.getPrice() + "/litre");
                totalPrice = showAllModel.getPrice() * totalQuantity;
            }
        }



        //Buy Now

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailedActivity.this, AddressActivity.class);

                if (viewAllModel != null) {
                    i.putExtra("item", viewAllModel);
                }if (showAllModel != null) {
                    i.putExtra("item", showAllModel);
                }
                startActivity(i);
            }
        });
    }
    public void detailback(View view) {
        Intent i = new Intent(this, viewAll.class);
        startActivity(i);
        finish();
    }
}