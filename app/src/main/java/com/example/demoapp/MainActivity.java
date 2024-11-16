package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView myTextView;
    private ImageView myImageView;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.myTextView);
        myImageView = findViewById(R.id.myImageView);
        Button myButton = findViewById(R.id.myButton);
        Button openSecondActivityButton = findViewById(R.id.openSecondActivityButton);
        Button openFormActivityButton = findViewById(R.id.openFormActivityButton);
        Button openListViewActivity = findViewById(R.id.openListViewActivity);


        myButton.setOnClickListener(v -> {
            counter++;
            myTextView.setText("Counter: " + counter);
            Toast.makeText(MainActivity.this, "Counter: " + counter, Toast.LENGTH_SHORT).show();


            if (counter % 2 == 0) {
                myImageView.setImageResource(R.drawable.even);
            } else {
                myImageView.setImageResource(R.drawable.odd);
            }
        });

        openSecondActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });


        openFormActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });


        openListViewActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(intent);
        });
    }
}
