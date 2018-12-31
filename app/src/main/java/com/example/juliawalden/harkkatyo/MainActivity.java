package com.example.juliawalden.harkkatyo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context = null;
    MyRecyclerViewAdapter adapter;
    int choice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        context = MainActivity.this;

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        final ArrayList<Review> reviews = (ArrayList<Review>) args.getSerializable("ARRAYLIST");



        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyRecyclerViewAdapter(context, reviews);
        //adapter.setClickListener();
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                choice = position;

                //Toast.makeText(context, "Valinta: " + adapter.getItem(choice), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, Main8Activity.class);
                i.putExtra("selected_review", adapter.getItem(choice));
                startActivity(i);

            }
        });




    }
}

