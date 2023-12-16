package com.example.demo_app.seller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_app.R;
import com.example.demo_app.activities.welcome_activity;
import com.google.firebase.auth.FirebaseAuth;

public class SellerHomeActivity extends AppCompatActivity {

    LinearLayout navigation;
    ImageView bakery,breakfast,drink,fruit,meat,vegetable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

        navigation = findViewById(R.id.sellernavigation);

        bakery = findViewById(R.id.bakeryEggsandBread);
        breakfast = findViewById(R.id.cerealsandBreakFastFood);
        drink = findViewById(R.id.drinks);
        fruit = findViewById(R.id.fruits);
        meat = findViewById(R.id.meatsandseafoods);
        vegetable = findViewById(R.id.vegetables);

        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHomeActivity.this, SellerBakeryActivity.class);
                startActivity(i);
                finish();
            }
        });
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHomeActivity.this, SellerBreakFastActivity.class);
                startActivity(i);
                finish();
            }
        });
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHomeActivity.this, SellerDrinkActivity.class);
                startActivity(i);
                finish();
            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHomeActivity.this, SellerFruitActivity.class);
                startActivity(i);
                finish();
            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHomeActivity.this, SellerMeatActivity.class);
                startActivity(i);
                finish();
            }
        });
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerHomeActivity.this, SellerVegetableActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void navigationopen(View view) {
        navigation.setVisibility(View.VISIBLE);
    }

    public void closenav(View view) {
        navigation.setVisibility(View.GONE);
    }

    public void navigationcls(View view) {
        navigation.setVisibility(View.GONE);
    }

    public void sellerhome(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerHomeActivity.class);
        startActivity(i);
        finish();
    }

    public void sellerprofile(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void sellersettings(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerSettingsActivity.class);
        startActivity(i);
        finish();
    }

    public void sellercontactus(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerContactSupportActivity.class);
        startActivity(i);
        finish();
    }

    public void sellerabout(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerAboutActivity.class);
        startActivity(i);
        finish();
    }

    public void sellerfeedback(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerFeedbackActivity.class);
        startActivity(i);
        finish();
    }
    public void sellercategory(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerCategoryActivity.class);
        startActivity(i);
        finish();
    }

    public void bakeryEggsandBread(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerBakeryActivity.class);
        startActivity(i);
        finish();
    }

    public void cerealsandBreakFastFood(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerBreakFastActivity.class);
        startActivity(i);
        finish();
    }

    public void drinks(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerDrinkActivity.class);
        startActivity(i);
        finish();
    }

    public void fruits(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerFruitActivity.class);
        startActivity(i);
        finish();
    }

    public void meatsandseafoods(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerMeatActivity.class);
        startActivity(i);
        finish();
    }

    public void vegetables(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerVegetableActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        customExitDialog();
    }
    private void customExitDialog() {
        final Dialog dialog = new Dialog(SellerHomeActivity.this);

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

    public void sellerlogout(View view) {
        customLogoutDialog();

    }

    private void customLogoutDialog() {
        final Dialog dialog = new Dialog(SellerHomeActivity.this);

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
                startActivity(new Intent(SellerHomeActivity.this, welcome_activity .class));
                Toast.makeText(SellerHomeActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();

            }
        });
        dialog.show();
    }

    public void othertype(View view) {
        Intent i = new Intent(SellerHomeActivity.this, SellerOtherProductsActivity.class);
        startActivity(i);
        finish();
    }
}