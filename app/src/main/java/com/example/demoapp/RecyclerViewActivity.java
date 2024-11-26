package com.example.demoapp;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new Item("Learn Java", "Start with the basics of Java programming."));
        itemList.add(new Item("Master Android", "Build amazing apps using Android Studio."));
        itemList.add(new Item("Explore Kotlin", "Discover the modern language for Android."));
        itemList.add(new Item("Design Patterns", "Understand software design principles."));
        itemList.add(new Item("UI/UX Design", "Create stunning and user-friendly apps."));

        adapter = new RecyclerViewAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
