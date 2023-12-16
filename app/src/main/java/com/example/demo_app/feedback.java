package com.example.demo_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class feedback extends AppCompatActivity {

    EditText etfeed;
    TextView tvfeed;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        etfeed = findViewById(R.id.feed_text);
        tvfeed = findViewById(R.id.textView);
        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating == 0){
                    tvfeed.setText("Very Dissatisfied");
                }
                else if(rating == 1){
                    tvfeed.setText("Dissatisfied");
                }
                else if(rating == 2 || rating == 3){
                    tvfeed.setText("Ok");
                }
                else if(rating == 4){
                    tvfeed.setText("Satisfied");
                }
                else if(rating == 5){
                    tvfeed.setText("Very Satisfied");
                }
                else{

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(feedback.this,home.class);
        startActivity(i);
        finish();
    }

    public void feedbutton(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/html");
        i.putExtra(Intent.EXTRA_EMAIL,new String("grocerysupport253@gmail.com"));
        i.putExtra(Intent.EXTRA_SUBJECT,"Feedback from Grocery app");
        i.putExtra(Intent.EXTRA_TEXT,"Feedback : " + etfeed.getText().toString().trim());
        try{
            startActivity(Intent.createChooser(i,"Please select Email"));
        }
        catch (android.content.ActivityNotFoundException exception)
        {
            Toast.makeText(this, "There are no Email clients", Toast.LENGTH_SHORT).show();
        }
    }
}