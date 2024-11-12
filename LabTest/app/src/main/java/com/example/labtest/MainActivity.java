package com.example.labtest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private Spinner divisionsSpinner;
    private Button increaseButton, decreaseButton, submitButton;
    private TextView quantityTextView, priceValueTextView;
    private SeekBar priceSeekBar;

    private int quantity = 0;
    private int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        divisionsSpinner = findViewById(R.id.divisionsSpinner);
        decreaseButton = findViewById(R.id.decreaseButton);
        increaseButton = findViewById(R.id.increaseButton);
        submitButton = findViewById(R.id.submitButton);
        quantityTextView = findViewById(R.id.quantityTextView);
        priceValueTextView = findViewById(R.id.priceValueTextView);
        priceSeekBar = findViewById(R.id.priceSeekBar);

        // Populate Spinner with items from string array resource
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.divisions_array, // Defined in strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divisionsSpinner.setAdapter(adapter);

        // Set up SeekBar listener for price
        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                price = progress;
                priceValueTextView.setText("BDT " + price);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Set up button listeners for quantity
        decreaseButton.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        increaseButton.setOnClickListener(v -> {
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
        });


        submitButton.setOnClickListener(v -> handleSubmit());
    }


    private void handleSubmit() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String division = divisionsSpinner.getSelectedItem().toString();


        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {

            String summary = "Order Summary:\nName: " + name + "\nEmail: " + email +
                    "\nDivision: " + division + "\nQuantity: " + quantity + "\nPrice: BDT " + price;
            Toast.makeText(MainActivity.this, summary, Toast.LENGTH_LONG).show();
        }
    }
}
