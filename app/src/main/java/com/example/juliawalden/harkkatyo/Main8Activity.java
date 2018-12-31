package com.example.juliawalden.harkkatyo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main8Activity extends AppCompatActivity {

    String fullRating;
    TextView textFullRating;
    TextView textViewq;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        textFullRating = findViewById(R.id.textViewFullReview);
        textViewq = findViewById(R.id.textViewQ);
        context = Main8Activity.this;

        textViewq.setText("Oliko arvostelu hyödyllinen?");

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        fullRating = extras.getString("selected_review");

        textFullRating = findViewById(R.id.textViewFullReview);

        textFullRating.setText(fullRating);
    }

    public void upvoteButton(View v) {
        Toast.makeText(context, "Ääni annettu!", Toast.LENGTH_SHORT).show();
    }

    public void downvoteButton(View v) {
        Toast.makeText(context, "Ääni annettu!", Toast.LENGTH_SHORT).show();
    }

}
