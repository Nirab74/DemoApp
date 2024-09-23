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
    private Button myButton;
    private Button openSecondActivityButton;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        myTextView = findViewById(R.id.myTextView);
        myImageView = findViewById(R.id.myImageView);
        myButton = findViewById(R.id.myButton);
        openSecondActivityButton = findViewById(R.id.openSecondActivityButton);

        // Handle button click for counter
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                myTextView.setText("Counter: " + counter);
                Toast.makeText(MainActivity.this, "Counter: " + counter, Toast.LENGTH_SHORT).show();

                if (counter % 2 == 0) {
                    myImageView.setImageResource(R.drawable.even);
                } else {
                    myImageView.setImageResource(R.drawable.odd);
                }
            }
        });

        // Navigate to SecondActivity when the button is clicked
        openSecondActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}
